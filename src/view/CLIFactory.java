package view;

import presenter.Command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by Timi on 9/28/2015.
 */
public class CLIFactory {

    public CLI createFrom(BufferedReader in, PrintWriter out, HashMap<String, Command> commandHashMap,IView view)
    {
        ReaderFileRunnable readerFileRunnable = new ReaderFileRunnable(in, out, commandHashMap,view);

        return new CLI(readerFileRunnable);
    }
}
