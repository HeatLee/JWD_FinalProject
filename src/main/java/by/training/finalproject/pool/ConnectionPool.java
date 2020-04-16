package by.training.finalproject.pool;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public enum ConnectionPool {
    INSTANCE;
    private static final int DEFAULT_POOL_SIZE = 16;
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static final String DATABASE_PROPERTIES_FILENAME = "db.properties";
    private static final String DATABASE_DRIVER = "db.driver";
    private static final String DATABASE_URL = "db.url";
    private static final String DATABASE_USER = "db.user";
    private static final String DATABASE_PASSWORD = "db.password";

    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> usedConnections;
    private AtomicBoolean isPoolInitiated = new AtomicBoolean(false);

    ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        usedConnections = new ArrayDeque<>();
    }

    public void initConnectionPool() {
        if (!isPoolInitiated.get()) {
            Properties properties = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DATABASE_PROPERTIES_FILENAME);
            try {
                properties.load(Objects.requireNonNull(inputStream));
                String user = properties.getProperty(DATABASE_USER);
                char[] password = properties.getProperty(DATABASE_PASSWORD).toCharArray();
                String url = properties.getProperty(DATABASE_URL);
                String driver = properties.getProperty(DATABASE_DRIVER);
                Class.forName(driver);
                for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                    freeConnections.add(new ProxyConnection(DriverManager.getConnection(url, user, new String(password))));
                }
                isPoolInitiated.set(true);
            } catch (ClassNotFoundException | IOException | SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            LOGGER.error(e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection proxyConnection) {
        if (proxyConnection != null) {
            usedConnections.remove(proxyConnection);
            boolean tmp = freeConnections.add(proxyConnection);
            if (!tmp) {
                LOGGER.info("Can't add connection");
            }
        }
    }

    public void destroyPool() {
        if (isPoolInitiated.get()) {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                try {
                    freeConnections.take().closeInPool();
                } catch (InterruptedException e) {
                    LOGGER.error(e);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
