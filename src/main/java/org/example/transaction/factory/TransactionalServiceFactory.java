package org.example.transaction.factory;

import org.example.transaction.TransactionProxy;
import org.example.transaction.UserService;
import org.example.transaction.UserServiceImpl;

public class TransactionalServiceFactory implements ServiceFactory {

    @Override
    public UserService createUserService() {
        UserService realService = new UserServiceImpl();
        return (UserService) TransactionProxy.newInstance(realService);
    }
}
