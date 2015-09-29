package notifications;

/**
 * Created by Timi on 9/26/2015.
 */
public abstract class ObservableNotification {
    private String notificationName;

    public ObservableNotification() {
    }

    public String getNotificationName() {
        return notificationName;
    }

    public ObservableNotification(String notificationName) {
        this.notificationName = notificationName;
    }

    abstract public void print();


}
