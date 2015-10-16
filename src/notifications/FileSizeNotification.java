package notifications;

public class FileSizeNotification implements ObservableNotification
{
    private final long size;
    private final String mazeName;

    public FileSizeNotification(long size, String mazeName)
    {
        this.size = size;
        this.mazeName = mazeName;
    }

    @Override
    public void print()
    {
        System.out.println("The file size of maze: " + mazeName + " is " + size);
    }
}
