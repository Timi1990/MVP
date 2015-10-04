package view;

import notifications.ObservableNotification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.*;
import presenter.Command;

import java.util.HashMap;

/**
 * Created by Timi on 10/1/2015.
 */
public class MazeGUIView extends BasicWindow implements IView{

    private Label label;

    private Menu menuBar,fileMenu,helpMenu,openSubMenu;

    private MenuItem fileMenuHeader,helpMenuHeader;

    private MenuItem fileExitItem,fileSaveItem,helpGetHelpItem,openItem,openProperties,runMazeItem;

    private boolean initialized = false;

    private Button button;



    public MazeGUIView(int width, int height) {
        super(width,height);

    }

    @Override
    public void init() {

        menuBar = new Menu(shell,SWT.BAR);

        fileMenuHeader = new MenuItem(menuBar,SWT.CASCADE);  //cascade??
        fileMenuHeader.setText("File");  //??

        fileMenu = new Menu(shell,SWT.DROP_DOWN);
        fileMenuHeader.setMenu(fileMenu);

        openItem = new MenuItem(fileMenu,SWT.CASCADE);
        openItem.setText("Open");


        openSubMenu = new Menu(shell,SWT.DROP_DOWN);
        openItem.setMenu(openSubMenu);

        openProperties = new MenuItem(openSubMenu,SWT.PUSH);
        openProperties.setText("Load Properties\t(CTRL+O)");
        openProperties.setAccelerator(SWT.CTRL + 'O');

        runMazeItem = new MenuItem(fileMenu,SWT.PUSH);
        runMazeItem.setText("Start game\t(CTRL+P)");
        runMazeItem.setAccelerator(SWT.CTRL+'P');

        fileSaveItem = new MenuItem(fileMenu,SWT.PUSH);
        fileSaveItem.setText("Save\t(CTRL+S)");
        fileSaveItem.setAccelerator(SWT.CTRL + 'S');

        fileExitItem = new MenuItem(fileMenu,SWT.PUSH);
        fileExitItem.setText("Exit");

        helpMenuHeader = new MenuItem(menuBar,SWT.CASCADE);
        helpMenuHeader.setText("Help");

        helpMenu = new Menu(shell,SWT.DROP_DOWN);
        helpMenuHeader.setMenu(helpMenu);

        helpGetHelpItem = new MenuItem(helpMenu,SWT.PUSH);
        helpGetHelpItem.setText("Get help");

        button=new Button(shell,SWT.PUSH|SWT.BORDER);
        button.setText("bla");
        shell.setMenuBar(menuBar);
        button.pack();




        runMazeItem.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if (initialized == false) {
                    MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
                    messageBox.setText("Error");
                    messageBox.setMessage("You must load game properties first..");
                    display.beep();
                    messageBox.open();
                }
                else if(initialized ==true)
                {
                    Shell startGameShell = new Shell(display,SWT.APPLICATION_MODAL
                            | SWT.DIALOG_TRIM);
                    startGameShell.open();
                }

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });
        fileExitItem.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
                messageBox.setMessage("Are you sure you want to exit?");
                messageBox.setText("Exit");
                display.beep();
                int answer = messageBox.open();
                if (answer == SWT.YES) {
                    shell.dispose();
                    display.dispose();
                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });
        fileSaveItem.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                FileDialog fileDialog = new FileDialog(shell,SWT.SAVE);
                fileDialog.setText("Save");
                fileDialog.setFilterPath("C:/");
                fileDialog.open();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });
        helpGetHelpItem.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                MessageBox messageBox = new MessageBox(shell,SWT.ICON_QUESTION);
                messageBox.setText("Information");
                messageBox.setMessage("This menu is a general menu for activating different games" +
                        ", please open your xml properties file to start.");
                messageBox.open();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });
        openProperties.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                setChanged();
                FileDialog fileDialog = new FileDialog(shell,SWT.OPEN);
                fileDialog.setText("Open");
                fileDialog.setFilterPath("C:/");
                String[] filters = {"*.xml"};
                fileDialog.setFilterExtensions(filters);
                String properties = fileDialog.open();
                if(properties!=null){
                filePath = properties;
                initialized=true;
                notifyObservers(filePath);}
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });

    }

    @Override
    public void start(String fileInput, String fileOutput, HashMap<String, Command> stringToCommand) {

    }

    @Override
    public void notifyFromReader(String notify) {

    }

    @Override
    public void handleCommandNotFound() {

    }

    @Override
    public void displayData(ObservableNotification observableNotification) {

    }

    @Override
    public void exitFromGui() {
        MessageBox messageBox = new MessageBox(shell,SWT.ICON_WARNING|SWT.OK);
        messageBox.setMessage("Warning");
        messageBox.setMessage("You will have to restart...");
        display.beep();
        messageBox.open();
        shell.dispose();
        display.dispose();
    }
}
