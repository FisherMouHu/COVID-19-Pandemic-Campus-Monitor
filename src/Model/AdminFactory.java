package Model;

public class AdminFactory extends UserFactory {
    @Override
    public User createUser(String email, String password, String name) {
        return new Admin(email, password, name);
    }
}
