package notifications;

//todo check for needed?
public class LoadMazeObservableNotification implements ObservableNotification
{
    private String filePath;
    private String mazeName;

    public LoadMazeObservableNotification(String filePath, String mazeName)
    {
        this.mazeName = mazeName;
        this.filePath = filePath;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public String getMazeName()
    {
        return mazeName;
    }

    @Override
    public void print()
    {

    }
}
