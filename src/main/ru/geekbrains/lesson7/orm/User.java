package ru.geekbrains.lesson7.orm;

@Table(tableName = "USERS")
public class User {

    @PrimaryKey
    @AutoIncrement
    @Field(name = "id")
    private int id;

    @Unique
    @NotNull
    @Field(name = "login")
    private String login;

    @NotNull
    @Field(name = "password")
    private String password;

    @Field(name = "address")
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
