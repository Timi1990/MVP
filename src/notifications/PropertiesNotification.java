package notifications;

import model.IModel;
import presenter.Properties;

public class PropertiesNotification implements ObservableNotification
{
    private final String filePath;
    private IModel model;


    public PropertiesNotification(String filePath)
    {
        this.filePath = filePath;
    }


    @Override
    public void apply() {
        model.setProperties(filePath);
    }

    @Override
    public void print()
    {

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
