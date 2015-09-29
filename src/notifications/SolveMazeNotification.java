package notifications;

/**
 * Created by Timi on 9/28/2015.
 */
public class SolveMazeNotification extends ObservableNotification {

    private String algorithm;
    private String mazeName;

    public SolveMazeNotification(String algorithm, String mazeName) {
        this.algorithm = algorithm;
        this.mazeName = mazeName;
    }

    @Override
    public void print() {
        System.out.println("Maze: "+ mazeName+" solved with "+algorithm+" algorithm");
    }
}
