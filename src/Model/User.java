package Model;

public abstract class User {
    private String email;
    private String password;
    private String name;

    public abstract String getEmail();

    public abstract String getPassword();

    public abstract String getName();
}
