package ru.geekbrains.lesson7.orm;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository<T> {

    private final Connection conn;
    private Class<T> clazz;
    private String primaryKeyField;
    private String insertFields;
    private String selectFields;
    private String createTableStatement;

    public Repository(Connection conn, Class<T> clazz) throws SQLException {
        this.conn = conn;
        this.clazz = clazz;
        buildStatements();
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
            stmt.execute(createTableStatement);
        }
    }

    private void buildStatements() {
        StringBuilder allStatementSb = new StringBuilder();
        StringBuilder uniqueFieldsSb = new StringBuilder();
        StringBuilder insertFieldsSb = new StringBuilder();

        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalStateException("No Table annotation");
        }
        String tableName = clazz.getAnnotation(Table.class).tableName();
        tableName = tableName.isEmpty() ? clazz.getSimpleName() : tableName;
        allStatementSb.append("create table if not exists " + tableName + "(");

        for (Field fld : clazz.getDeclaredFields()) {
            if (!fld.isAnnotationPresent(DbField.class)) {
                continue;
            }
            DbField fldAnnotation = fld.getAnnotation(DbField.class);
            String fieldName = fldAnnotation.name().isEmpty() ? fld.getName() : fldAnnotation.name();
            String fieldType = null;
            Class<?> type = fld.getType();
            if (type == int.class || type == Integer.class) {
                fieldType = "int";
            } else if (type == String.class) {
                fieldType = "varchar(255)";
            }
            boolean isPrimaryKey = fld.isAnnotationPresent(PrimaryKey.class);

            if (isPrimaryKey) {
                if (primaryKeyField != null) {
                    throw new IllegalStateException("More then one primary key");
                }
                primaryKeyField = fieldName;
            } else {
                insertFieldsSb.append(fieldName + ",");
            }

            boolean isAutoIncrement = fld.isAnnotationPresent(AutoIncrement.class);

            if (fld.isAnnotationPresent(Unique.class)) {
                uniqueFieldsSb.append("unique index ui_" + fieldName + "(" + fieldName + "),");
            }

            allStatementSb.append(fieldName + " " +
                    fieldType + " " +
                    (isPrimaryKey ? "primary key" : "")  + " " +
                    (isAutoIncrement ? "auto_increment" : "") + ",");
        }

        if (uniqueFieldsSb.length() > 0) {
            allStatementSb.append(uniqueFieldsSb);
        }
        allStatementSb.deleteCharAt(allStatementSb.length() - 1);
        allStatementSb.append(");");

        createTableStatement = allStatementSb.toString();

        insertFieldsSb.deleteCharAt(insertFieldsSb.length() - 1);
        insertFields = insertFieldsSb.toString();

        selectFields = primaryKeyField + "," + insertFields;
    }

    public String getInsertFields() {
        return insertFields;
    }

    public String getSelectFields() {
        return selectFields;
    }

    public String getCreateTableStatement() {
        return createTableStatement;
    }
}
