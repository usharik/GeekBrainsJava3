package ru.geekbrains.lesson3;

import java.io.Serializable;

public class DataClass implements Serializable {

    public int intValue;

    public String strValue;

    public double doubleValue;

    // transient поля не сохраняются при сериализации
    public transient int transIntValue;

    public DataClass(int intValue, String strValue, double doubleValue, int transIntValue) {
        this.intValue = intValue;
        this.strValue = strValue;
        this.doubleValue = doubleValue;
        this.transIntValue = transIntValue;
    }

    @Override
    public String toString() {
        return "DataClass{" +
                "intValue=" + intValue +
                ", strValue='" + strValue + '\'' +
                ", doubleValue=" + doubleValue +
                ", transIntValue=" + transIntValue +
                '}';
    }
}
