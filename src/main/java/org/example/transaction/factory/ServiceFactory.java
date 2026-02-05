package org.example.transaction.factory;

import org.example.transaction.service.UserService;

public interface ServiceFactory {
    UserService createUserService();
}
