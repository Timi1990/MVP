package view;

import model.MazeModel;
import notifications.ObservableNotification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import presenter.Presenter;
import view.listener.GeneratorMazeWindowListenerFactory;

public class MazePropertiesWindow extends BasicWindow implements IView
{
    MazePropertiesWindow(int width, int height)
    {
        super(width, height);
    }

    @Override
    public void init()
    {
        Presenter presenter = new Presenter(new MazeModel(), this);
        addObserver(presenter);

        getShell().setText("Maze properties");
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 4;
        getShell().setLayout(gridLayout);

        Label nameLabel = new Label(getShell(), SWT.BORDER);
        nameLabel.setText("Maze name");

        Label dimensionLabel = new Label(getShell(), SWT.BORDER);
        dimensionLabel.setText("Dimension");
        Label rowLabel = new Label(getShell(), SWT.BORDER);
        rowLabel.setText("Rows");
        Label columnsLabel = new Label(getShell(), SWT.BORDER);
        columnsLabel.setText("Columns");
        Text t1 = new Text(getShell(), SWT.BORDER);
        Text t2 = new Text(getShell(), SWT.SINGLE | SWT.BORDER);
        Text t3 = new Text(getShell(), SWT.SINGLE | SWT.BORDER);
        Text t4 = new Text(getShell(), SWT.SINGLE | SWT.BORDER);

        Button button = new Button(getShell(), SWT.PUSH);
        button.setText("Generate");

        button.pack();

        GeneratorMazeWindowListenerFactory generatorMazeWindowListenerFactory = new GeneratorMazeWindowListenerFactory(this);


        button.addSelectionListener(generatorMazeWindowListenerFactory.createFrom(t1,t2,t3,t4));
    }

    public void setChange()
    {
        setChanged();
    }

    @Override
    public Canvas getCanvas()
    {
        return null;
    }

    @Override
    public void setBackGround(Color backGround)
    {

    }

    @Override
    public void notifyFromReader(String notify)
    {

    }

    @Override
    public void handleCommandNotFound()
    {

    }

    @Override
    public void displayData(ObservableNotification observableNotification)
    {

    }

    @Override
    public <T> T handleData(ObservableNotification<T> observableNotification)
    {
       return observableNotification.getData();
    }
}
