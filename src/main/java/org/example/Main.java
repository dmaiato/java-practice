package org.example;

import org.example.transaction.UserService;
import org.example.transaction.proxy.ProxyType;
import org.example.transaction.factory.ServiceFactory;
import org.example.transaction.factory.TransactionalServiceFactory;

public class Main {
    public static void main(String[] args) {
        testWithProxy(ProxyType.STATIC);
        System.out.println("\n========================================\n");
        testWithProxy(ProxyType.DYNAMIC);
    }

    private static void testWithProxy(ProxyType type) {
        // Injeta a implementação desejada via construtor da Factory
        ServiceFactory factory = new TransactionalServiceFactory(type);
        UserService userService = factory.createUserService();

        System.out.println("Testando createUser:");
        userService.createUser("Alice");

        System.out.println("\nTestando deleteUser (erro esperado):");
        try {
            userService.deleteUser("Bob");
        } catch (Exception e) {
            // Ignora erro esperado
        }
    }
}
