package org.example.transaction.factory;

import org.example.transaction.UserService;

public interface ServiceFactory {
    UserService createUserService();
}
