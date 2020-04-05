package by.training.finalproject.builder;

public abstract class AbstractBuilder<T>{
    protected T businessEntity;

    public T build() {
        return businessEntity;
    }
}
