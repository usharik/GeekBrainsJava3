package ru.geekbrains.lesson7;


import ru.geekbrains.lesson7.orm.Repository;
import ru.geekbrains.lesson7.orm.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ReflectionDemo {

    public int intValue;

    public String strValue;

    protected Object objValue;

    private boolean privateBoolValue;

    public Class<?> getClassDemo() {
        return this.getClass();
    }

    public void setIntValue(int value) {
        intValue = value;
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = ReflectionDemo.class;
        System.out.println(clazz.getSimpleName());

        clazz = new ReflectionDemo().getClass();
        System.out.println(clazz.getName());

        try {
            clazz = Class.forName("ru.geekbrains.lesson7.ReflectionDemo");
            System.out.println(clazz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ReflectionDemo rd = new ReflectionDemo();
        for (Field fld : clazz.getDeclaredFields()) {
            System.out.printf("DbField %s value %s modifiers %s%n", fld.getName(), fld.get(rd),
                    Integer.toBinaryString(fld.getModifiers()));
            if (Modifier.isProtected(fld.getModifiers())) {
                System.out.println("PROTECTED");
            }
        }

        Field strValueField = rd.getClass().getField("strValue");
        strValueField.set(rd, "Hello from reflection");
        System.out.println(rd.strValue);

        Field privateBoolValueField = rd.getClass().getDeclaredField("privateBoolValue");
        privateBoolValueField.set(rd, true);
        System.out.println(privateBoolValueField.get(rd));

        SomeClass someClass = new SomeClass();
        Field boolValue = someClass.getClass().getDeclaredField("boolValue");
        boolValue.setAccessible(true);
        boolValue.set(someClass, true);
        System.out.println(boolValue.get(someClass));

        Method isBoolValueMethod = someClass.getClass().getMethod("isBoolValue");
        System.out.println(isBoolValueMethod.invoke(someClass));

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/network_chat",
                    "root", "root");
            Repository<User> userRepository = new Repository<>(conn, User.class);
            System.out.println(userRepository.getCreateTableStatement());
            System.out.println(userRepository.getInsertFields());
            System.out.println(userRepository.getSelectFields());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
