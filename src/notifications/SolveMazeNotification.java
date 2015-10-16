package notifications;

public class SolveMazeNotification implements ObservableNotification
{
    private final String algorithm;
    private final String mazeName;

    public SolveMazeNotification(String algorithm, String mazeName)
    {
        this.algorithm = algorithm;
        this.mazeName = mazeName;
    }

    @Override
    public void print()
    {
        System.out.println("Maze: " + mazeName + " solved with " + algorithm + " algorithm");
    }
}
