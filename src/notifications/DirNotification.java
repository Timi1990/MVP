package notifications;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timi on 9/27/2015.
 */
public class DirNotification extends ObservableNotification {

    private String filePath;
    private List<String> fileNames ;

    public DirNotification(String filePath) {
        super(ObservableNotificationNames.DirNotificationName);
        this.filePath = filePath;
        fileNames=null;
    }

    public String getFilePath() {
        return filePath;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames)
    {
        this.fileNames = fileNames;
    }
}
