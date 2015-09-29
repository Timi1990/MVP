package view;

import notifications.ObservableNotification;
import presenter.Command;

import java.util.HashMap;

public interface IView {

    void start(String fileInput, String fileOutput, HashMap<String, Command> stringToCommand);

    void notifyFromReader(String notify);

    void handleCommandNotFound();

    void displayData(ObservableNotification observableNotification);


	
}
