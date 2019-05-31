package ru.geekbrains.lesson7.orm;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository<T> {

    private final Connection conn;
    private Class<T> clazz;

    public Repository(Connection conn, Class<T> clazz) throws SQLException {
        this.conn = conn;
        this.clazz = clazz;
        createTableIfNotExists(conn);
    }

    public void insert(User user) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into users(login, password) values (?, ?);")) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.execute();
        }
    }

    public User findByLogin(String login) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, login, password from users where login = ?")) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        }
        return new User(-1, "", "");
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, login, password from users");

            while (rs.next()) {
                res.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists users (\n" +
                    "\tid int auto_increment primary key,\n" +
                    "    login varchar(25),\n" +
                    "    password varchar(25),\n" +
                    "    unique index uq_login(login)\n" +
                    ");");
        }
    }

    public String buildCreateTableStatement() {
        StringBuilder sb = new StringBuilder();
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalStateException("No Table annotation");
        }
        String tableName = clazz.getAnnotation(Table.class).tableName();
        tableName = tableName.isEmpty() ? clazz.getSimpleName() : tableName;
        sb.append("create table if not exists " + tableName + "(");

        for (Field fld : clazz.getDeclaredFields()) {
            if (!fld.isAnnotationPresent(ru.geekbrains.lesson7.orm.Field.class)) {
                continue;
            }
            ru.geekbrains.lesson7.orm.Field fldAnnotation = fld.getAnnotation(ru.geekbrains.lesson7.orm.Field.class);
            String fieldName = fldAnnotation.name().isEmpty() ? fld.getName() : fldAnnotation.name();
            String fieldType = null;
            Class<?> type = fld.getType();
            if (type == int.class) {
                fieldType = "int";
            } else if (type == String.class) {
                fieldType = "varchar(25)";
            }
            boolean isPrimaryKey = fld.isAnnotationPresent(PrimaryKey.class);

            sb.append(fieldName + " " + fieldType + " " + (isPrimaryKey ? "primary key" : "") + ",");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append(");");
        return sb.toString();
    }
}
