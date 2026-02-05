package org.example.transaction.factory;

import org.example.transaction.proxy.TransactionProxy;
import org.example.transaction.UserService;
import org.example.transaction.UserServiceImpl;
import org.example.transaction.proxy.ProxyType;
import org.example.transaction.proxy.UserServiceProxy;

public class TransactionalServiceFactory implements ServiceFactory {

    private final ProxyType proxyType;

    public TransactionalServiceFactory(ProxyType proxyType) {
        this.proxyType = proxyType;
    }

    @Override
    public UserService createUserService() {
        UserService realService = new UserServiceImpl();

        return switch (proxyType) {
            case DYNAMIC -> {
                System.out.println("--- Usando Proxy Dinâmico ---");
                yield (UserService) TransactionProxy.newInstance(realService);
            }
            case STATIC -> {
                System.out.println("--- Usando Proxy Estático ---");
                yield new UserServiceProxy(realService);
            }
            default -> throw new IllegalArgumentException("Tipo de proxy não suportado: " + proxyType);
        };
    }
}
