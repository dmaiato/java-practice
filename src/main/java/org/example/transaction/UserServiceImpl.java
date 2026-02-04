package org.example.transaction;

public class UserServiceImpl implements UserService {

    @Override
    @Transaction
    public void createUser(String name) {
        System.out.println("Criando usuário: " + name);
        // Simulando lógica de banco de dados
    }

    @Override
    @Transaction
    public void deleteUser(String name) {
        System.out.println("Tentando deletar usuário: " + name);
        throw new RuntimeException("Erro de conexão com o banco");
    }
}
