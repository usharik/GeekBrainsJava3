package ru.geekbrains.lesson1;

public class Container<T> {
    private T obj;

    public Container(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
