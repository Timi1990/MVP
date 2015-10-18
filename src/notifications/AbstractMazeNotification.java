package notifications;

import model.IModel;

public abstract class AbstractMazeNotification implements ObservableNotification
{
    private final String mazeName;

    protected AbstractMazeNotification(String mazeName)
    {
        this.mazeName = mazeName;
    }

    @Override
    public void apply() {

    }

    @Override
    public void print()
    {
        System.out.println("Maze " + mazeName + " is loaded");
    }

    @Override
    public void init(IModel model) {

    }

    @Override
    public <T> T getData() {
        return null;
    }
}
