package notifications;

import model.IModel;

public interface ObservableNotification<T>
{
    void apply();

    void print();

    void init(IModel model);

    T getData();

    void setData(T data);
}
