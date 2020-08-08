package Model;

public abstract class UserFactory {
    public abstract User createUser(String email, String password, String name);
}
