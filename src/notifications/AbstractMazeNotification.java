package notifications;

public abstract class AbstractMazeNotification implements ObservableNotification
{
    private final String mazeName;

    protected AbstractMazeNotification(String mazeName)
    {
        this.mazeName = mazeName;
    }

    @Override
    public void print()
    {
        System.out.println("Maze " + mazeName + " is loaded");
    }
}
