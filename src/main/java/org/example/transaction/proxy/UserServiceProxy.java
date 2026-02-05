package org.example.transaction.proxy;

import org.example.transaction.Transaction;
import org.example.transaction.UserService;

import java.lang.reflect.Method;

public class UserServiceProxy implements UserService {

    private final UserService target;

    public UserServiceProxy(UserService target) {
        this.target = target;
    }

    @Override
    public void createUser(String name) {
        executeTransactionalLogic(() -> target.createUser(name), "createUser", String.class);
    }

    @Override
    public void deleteUser(String name) {
        executeTransactionalLogic(() -> target.deleteUser(name), "deleteUser", String.class);
    }

    private void executeTransactionalLogic(Runnable action, String methodName, Class<?>... paramTypes) {
        String fullMethodName = target.getClass().getSimpleName() + "." + methodName;
        try {
            Method originalMethod = target.getClass().getMethod(methodName, paramTypes);
            if (originalMethod.isAnnotationPresent(Transaction.class)) {
                System.out.println("[Proxy Estático] Iniciando execução do método " + fullMethodName);
                action.run();
                System.out.println("[Proxy Estático] Finalizando execução do método " + fullMethodName + " com sucesso");
            } else {
                action.run();
            }
        } catch (Exception e) {
            System.out.println("[Proxy Estático] Finalizando execução do método " + fullMethodName + " com erro: " + (e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
            throw new RuntimeException(e);
        }
    }
}
