package Controller;

import Model.*;
import View.*;

public class NormalUserController {
    // Create a Abstract Class including Template Method
    // Use Template Method Pattern to Use Different Concrete Class ( UserViewInitiator -> (AdminViewInitiator, NormalUserViewInitiator) )
    private UserViewInitiator userViewInitiator;

    public NormalUserController(User user, NormalUserView normalUserView) {
        userViewInitiator = new NormalUserViewInitiator(user, normalUserView);

        userViewInitiator.init();
    }
}
