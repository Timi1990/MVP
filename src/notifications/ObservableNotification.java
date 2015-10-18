package notifications;

import model.IModel;

public interface ObservableNotification
{
    void apply();

    void print();

    void init(IModel model);

    <T> T getData();
}
