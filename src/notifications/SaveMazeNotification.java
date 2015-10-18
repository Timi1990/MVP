package notifications;

import model.IModel;

public class SaveMazeNotification implements ObservableNotification {

    private final String mazeName;
    private IModel model;

    public SaveMazeNotification(String mazeName) {
        this.mazeName = mazeName;
    }

    @Override
    public void apply() {

    }

    @Override
    public void print() {
        System.out.println("Maze "+mazeName+" Saved");
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
