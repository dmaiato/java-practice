package org.example.transaction.proxy;

import org.example.transaction.Transaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionProxy implements InvocationHandler {

    private final Object target;

    private TransactionProxy(Object target) {
        this.target = target;
    }

    public static Object newInstance(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new TransactionProxy(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method originalMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        if (!originalMethod.isAnnotationPresent(Transaction.class)) {
            return method.invoke(target, args);
        }

        String methodName = target.getClass().getSimpleName() + "." + method.getName();
        System.out.println("[Proxy Dinâmico] Iniciando execução do método " + methodName);

        try {
            Object result = method.invoke(target, args);
            System.out.println("[Proxy Dinâmico] Finalizando execução do método " + methodName + " com sucesso");
            return result;
        } catch (Exception e) {
            System.out.println("[Proxy Dinâmico] Finalizando execução do método " + methodName + " com erro: " + e.getCause().getMessage());
            throw e;
        }
    }
}
