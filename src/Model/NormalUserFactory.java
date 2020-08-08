package Model;

public class NormalUserFactory extends UserFactory {
    @Override
    public User createUser(String email, String password, String name) {
        return new NormalUser(email, password, name);
    }
}
