package notifications;

import model.IModel;

public class DisplayMazeExistsNotification implements ObservableNotification
{

    @Override
    public void apply() {

    }

    @Override
    public void print()
    {
        System.out.println(ObservableNotificationNames.DisplayMazeExistsName);
    }

    @Override
    public void init(IModel model) {

    }

    @Override
    public <T> T getData() {
        return null;
    }
}
