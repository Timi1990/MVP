package view;

import notifications.DirNotification;
import notifications.GenerateMazeNotification;
import notifications.LoadMazeObservableNotification;

import java.util.List;
import java.util.Observable;

public class MazeView extends Observable implements IView {

    public void loadMaze()
    {
        LoadMazeObservableNotification loadMazeObservableNotification=new LoadMazeObservableNotification("C:\\Users\\Timi\\Desktop\\test.txt","First" );
        this.setChanged();
        this.notifyObservers(loadMazeObservableNotification);
    }
    public void generate(String mazeName,int dimension,int rows,int columns)
    {
        GenerateMazeNotification generateMazeNotification= new GenerateMazeNotification(mazeName,dimension,rows,columns);
        this.setChanged();
        this.notifyObservers(generateMazeNotification);
    }

    public void dir(String pathName)
    {
        DirNotification dirNotification=new DirNotification(pathName);
        this.setChanged();
        this.notifyObservers(dirNotification);
    }

    @Override
    public void onMazeLoaded(String mazeName) {
        System.out.println("Maze " +mazeName +" is loaded" );
    }

    @Override
    public void onMazeGenerated(String mazeName) {
        System.out.println("Maze "+mazeName+ " is ready");
    }

    @Override
    public void displayDir(List<String> fileNames) {
        fileNames.forEach(System.out::println);
    }


}
