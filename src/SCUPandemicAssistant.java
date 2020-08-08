import Controller.LoginController;
import View.LoginView;

public class SCUPandemicAssistant {
    public static void main(String[] args) {
        // Initialized two LoginView View
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView);

        LoginView anotherLoginView = new LoginView();
        LoginController anotherLoginController = new LoginController(anotherLoginView);
    }
}
