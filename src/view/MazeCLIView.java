package view;

import notifications.ObservableNotification;

import java.util.Observable;

public class MazeCLIView extends Observable implements IView
{

    private final CLIFactory cliFactory;

    public MazeCLIView()
    {
        cliFactory = new CLIFactory();
    }


    @Override
    public void notifyFromReader(String notify)
    {
        setChanged();

        notifyObservers(notify);
    }

    @Override
    public void handleCommandNotFound()
    {

        System.out.println("command not found");
    }

    @Override
    public void displayData(ObservableNotification observableNotification)
    {
        observableNotification.print();
    }

    @Override
    public <T> T handleData(ObservableNotification<T> observableNotification) {
        return null;
    }


}
