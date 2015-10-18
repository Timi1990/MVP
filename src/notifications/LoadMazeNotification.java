package notifications;

import model.IModel;

public class LoadMazeNotification extends AbstractMazeNotification
{

    private IModel model;


    public LoadMazeNotification(String mazeName)
    {
        super(mazeName);
    }


    @Override
    public void apply() {

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
