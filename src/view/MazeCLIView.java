package view;

import notifications.ObservableNotification;
import presenter.Command;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;

public class MazeCLIView extends Observable implements IView {

    private final CLIFactory cliFactory;

    public MazeCLIView()
    {
        cliFactory = new CLIFactory();
    }


    @Override
    public void start(String fileInput, String fileOutput, HashMap<String, Command> stringToCommand)
    {
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(fileInput));
            PrintWriter out = new PrintWriter(fileOutput);

            CLI cli = cliFactory.createFrom(in, out, stringToCommand,this);
            cli.start();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyFromReader(String notify) {

        setChanged();

        notifyObservers(notify);
    }

    @Override
    public void handleCommandNotFound() {

        System.out.println("command not found");

    }

    @Override
    public void displayData(ObservableNotification observableNotification) {
        observableNotification.print();
    }

    @Override
    public void exitFromGui() {

    }


}
