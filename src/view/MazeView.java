package view;

import notifications.ObservableNotification;

import java.util.Observable;

public class MazeView extends Observable implements IView
{

    private final CLIFactory cliFactory;

    public MazeView()
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


}
