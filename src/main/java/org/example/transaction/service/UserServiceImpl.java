package org.example.transaction.service;

import org.example.transaction.Transaction;

public class UserServiceImpl implements UserService {

    @Override
    @Transaction
    public void createUser(String name) {
        // simulando lógica de banco de dados
        System.out.println(">> criando usuário: " + name);
    }

    @Override
    @Transaction
    public void deleteUser(String name) {
        System.out.println(">> tentando deletar usuário: " + name);
        throw new RuntimeException("erro de conexão com o banco");
    }
}
