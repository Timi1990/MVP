package notifications;

/**
 * Created by Timi on 9/26/2015.
 */


public class LoadMazeObservableNotification extends ObservableNotification {

    private String filePath;
    private String mazeName;

    public LoadMazeObservableNotification(String filePath, String mazeName) {
        super(ObservableNotificationNames.LoadMazeNotificationName);
        this.mazeName = mazeName;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getMazeName() {
        return mazeName;
    }
}
