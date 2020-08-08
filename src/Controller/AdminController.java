package Controller;

import Model.*;
import View.*;

public class AdminController {
    // Create a Abstract Class including Template Method
    // Use Template Method Pattern to Use Different Concrete Class ( UserViewInitiator -> (AdminViewInitiator, NormalUserViewInitiator) )
    private UserViewInitiator userViewInitiator;

    public AdminController(User admin, AdminView adminView) {
        userViewInitiator = new AdminViewInitiator(admin, adminView);

        userViewInitiator.init();
    }
}
