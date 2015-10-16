package notifications;

public class DisplayMazeExistsNotification implements ObservableNotification
{
    @Override
    public void print()
    {
        System.out.println(ObservableNotificationNames.DisplayMazeExistsName);
    }
}
