package org.example;

import org.example.notification.PoolingUseCaseNotification;
import org.example.notification.PresenterNotification;
import org.example.notification.UseCaseNotification;
import org.example.transaction.TransactionProxy;
import org.example.transaction.UserService;
import org.example.transaction.UserServiceImpl;
import org.example.transaction.factory.ServiceFactory;
import org.example.transaction.factory.TransactionalServiceFactory;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
//        ScheduledExecutorService controller = Executors.newSingleThreadScheduledExecutor();
//        var notificationUseCase = new PoolingUseCaseNotification();
//        PresenterNotification emailPresenter = (message) -> System.out.printf("email %s", message);
//        PresenterNotification whatsAppPresenter = (message) -> System.out.printf("whatApp %s", message);
//        PresenterNotification smsPresenter = (message) -> System.out.printf("sms %s", message);
//        PresenterNotification[] notifications = {emailPresenter, whatsAppPresenter, smsPresenter};
//        controller.scheduleAtFixedRate(() -> {
//            var nextPos = Math.abs(new Random().nextInt()) % 3;
//            notificationUseCase.notifyEveryHour(UUID.randomUUID().toString(), notifications[nextPos]);
//            System.out.println();
//        }, 1, 1, TimeUnit.SECONDS);

        // o cliente usa a Factory para obter o serviço
        ServiceFactory factory = new TransactionalServiceFactory();
        UserService userService = factory.createUserService();

        // chama o método que deve executar com sucesso
        System.out.println("\n\n--- chamando createUser (esperado sucesso) ---");
        userService.createUser("Michael Schumacher");
        System.out.println();

        // chama o método que deve lançar uma exceção
        System.out.println("--- chamando deleteUser (esperado erro) ---");
        try {
            userService.deleteUser("Fernando Alonso");
        } catch (Exception e) {
            // a exceção é esperada
        }
    }


}
