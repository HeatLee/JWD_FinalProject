package by.training.finalproject.dao;

public enum SQLStatement {
    ;

    private String query;

    SQLStatement(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
