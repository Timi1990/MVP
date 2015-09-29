package view;

import presenter.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by Timi on 9/28/2015.
 */
public class ReaderFileRunnable implements Runnable
{
    private final BufferedReader in;
    private final PrintWriter out;
    private final HashMap<String, Command> commandHashMap;
    private final IView view;

    public ReaderFileRunnable(BufferedReader in, PrintWriter out, HashMap<String, Command> commandHashMap,IView view)
    {
        this.in = in;
        this.out = out;
        this.commandHashMap = commandHashMap;
        this.view = view;
    }

    /**
     * Reads from input stream, and matches requests to various commands, according
     * to given command protocol.
     * Also in charge of closing the streams and runs as thread.
     */

    @Override
    public void run()
    {
        String currentLine;
        try
        {
            while ((currentLine = in.readLine()) != null)
            {
                view.notifyFromReader(currentLine);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.close();
//            System.exit(1);
        }
    }










}
