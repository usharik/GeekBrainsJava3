package ru.geekbrains.lesson7.orm;

@Table(tableName = "USERS")
public class User {

    @PrimaryKey
    @AutoIncrement
    @DbField(name = "id")
    private int id;

    @Unique
    @DbField(name = "login")
    private String login;

    @DbField(name = "password")
    private String password;

    @DbField(name = "address")
    private String address;

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
