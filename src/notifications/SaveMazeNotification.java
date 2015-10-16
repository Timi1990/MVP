package notifications;

public class SaveMazeNotification implements ObservableNotification {

    private final String mazeName;

    public SaveMazeNotification(String mazeName) {
        this.mazeName = mazeName;
    }

    @Override
    public void print() {
        System.out.println("Maze "+mazeName+" Saved");
    }
}
