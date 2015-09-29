package notifications;

/**
 * Created by Timi on 9/28/2015.
 */
public class LoadMazeNotification extends ObservableNotification {

    private String mazeName;

    public LoadMazeNotification( String mazeName) {
        super(ObservableNotificationNames.LoadMazeNotificationName);
        this.mazeName = mazeName;
    }

    @Override
    public void print() {
        System.out.println("Maze "+mazeName+" is loaded");
    }
}
