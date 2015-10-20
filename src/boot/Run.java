package boot;

import model.MazeModel;
import presenter.Presenter;
import view.MazeMenu;

public class Run
{
    public static void main(String[] args) throws Exception
    {
        MazeModel mazeModel = new MazeModel();
        MazeMenu mazeMenu = new MazeMenu(500, 500);

        Presenter presenter = new Presenter(mazeModel,mazeMenu);
        mazeModel.addObserver(presenter);
        mazeMenu.addObserver(presenter);

        mazeMenu.run();


    }
}


