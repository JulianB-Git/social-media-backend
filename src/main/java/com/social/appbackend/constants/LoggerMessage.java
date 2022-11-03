package com.social.appbackend.constants;

public interface LoggerMessage {

    String LOGIN_SUCCESS = "SUCCESS: Logged in user with username - ";
    String LOGIN_ERROR_USERNAME = "Invalid username - ";

    String SIGNUP_SUCCESS = "SUCCESS: Successfully created user - ";
    String SIGNUP_ERROR_INVALID_USERNAME = "This username already exists";
    String SIGNUP_ERROR_PASSWORD_LENGTH = "Password length must be more that 8 characters";
    String SIGNUP_ERROR_PASSWORD_MATCH = "The entered passwords do not match";

    String USER_NOT_FOUND = "User not found";

    String ITEM_NOT_FOUND = "Item not found";
    String ORDER_SUCCESS = "SUCCESS: Order successfully submitted for user: ";

    String CART_IS_EMPTY = "Cannot submit order because the users cart is empty";
    String ADD_TO_CART_SUCCESS = "SUCCESS: Successfully added to cart for user: ";

    String TOKEN_EXPIRED = "The token used in the request has expired";

}
