package notifications;

import model.IModel;

public class FileSizeNotification implements ObservableNotification<Long>
{
    private final long size;
    private final String mazeName;

    public FileSizeNotification(long size, String mazeName)
    {
        this.size = size;
        this.mazeName = mazeName;
    }


    @Override
    public void apply() {

    }

    @Override
    public void print()
    {
        System.out.println("The file size of maze: " + mazeName + " is " + size);
    }

    @Override
    public void init(IModel model) {

    }

    @Override
    public Long getData()
    {
        return size;
    }
}
