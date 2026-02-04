package org.example.notification;

import java.util.Random;

public interface UseCaseNotification {
    void notifyEveryHour(String customerId, PresenterNotification presenter);
}
