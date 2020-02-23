package com.facebook.ui;

import com.facebook.ui.validator.EmailValidator;
import com.facebook.ui.handlers.InputPopUps;
import com.facebook.ui.handlers.PrintUI;
import com.facebook.exception.FacebookException;
import com.facebook.service.UserService;

import static com.facebook.ui.handlers.InputPopUps.CANCELLED;


class SignUpUI {

    private EmailValidator emailValidator = new EmailValidator();
    private UserService userService = new UserService();

    void displaySignUp() throws FacebookException {

        String email = InputPopUps.input("Please enter your email address:");
        String password = InputPopUps.input("Please enter desired password:");

        if (!email.matches(CANCELLED) && !password.matches(CANCELLED)) {
            if (emailValidator.isEmailValid(email)) {
                if (userService.doesUserExist(email)) {
                    PrintUI.printBox("User exists, please login.");
                } else {
                    userService.signUp(password, email);
                }
            } else {
                PrintUI.printBox("Please enter a valid email address:");
            }
        } else {
            PrintUI.printBox("User canceled operation.");
        }
    }
}