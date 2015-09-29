package notifications;

/**
 * Created by Timi on 9/28/2015.
 */
public class SaveMazeNotification extends ObservableNotification {

    private String mazeName;

    public SaveMazeNotification(String mazeName) {
        this.mazeName = mazeName;
    }

    @Override
    public void print() {
        System.out.println("Maze "+mazeName+" Saved");
    }
}
