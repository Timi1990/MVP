package notifications;

import model.IModel;

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
    public void apply() {

    }

    @Override
    public void print()
    {

    }

    @Override
    public void init(IModel model) {

    }

    @Override
    public Object getData()
    {
        return null;
    }

    @Override
    public void setData(Object data)
    {

    }
}
