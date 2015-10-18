package notifications;

import model.IModel;

public class SolveMazeNotification implements ObservableNotification
{
    private final String algorithm;
    private final String mazeName;
    private IModel model;

    public SolveMazeNotification(String algorithm, String mazeName)
    {
        this.algorithm = algorithm;
        this.mazeName = mazeName;
    }


    @Override
    public void apply() {
        try {
            model.solve(mazeName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void print()
    {
        System.out.println("Maze: " + mazeName + " solved with " + algorithm + " algorithm");
    }

    @Override
    public void init(IModel model) {
        this.model = model;
    }

    @Override
    public <T> T getData() {
        return null;
    }
}
