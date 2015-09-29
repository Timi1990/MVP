package notifications;

/**
 * Created by Timi on 9/28/2015.
 */
public class FileSizeNotification extends ObservableNotification {

    private long size;
    private String mazeName;

    public FileSizeNotification(long size, String mazeName) {
        this.size = size;
        this.mazeName = mazeName;
    }

    @Override
    public void print() {
        System.out.println("The file size of maze: "+mazeName+" is "+size);
    }
}
