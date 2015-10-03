package notifications;

/**
 * Created by Timi on 9/30/2015.
 */
public class DisplayMazeExistsNotification extends ObservableNotification {

    public DisplayMazeExistsNotification() {
        super(ObservableNotificationNames.DisplayMazeExistsName);
    }

    @Override
    public void print() {
        System.out.println(ObservableNotificationNames.DisplayMazeExistsName);
    }
}
