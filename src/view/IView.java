package view;

import notifications.ObservableNotification;

public interface IView {

    void notifyFromReader(String notify);

    void handleCommandNotFound();

    void displayData(ObservableNotification observableNotification);

    <T> T handleData(ObservableNotification observableNotification);



	
}
