package model;

import boot.GlobalApplicationPool;
import com.sun.corba.se.impl.orbutil.closure.Future;
import controller.MazeDoesntExistsException;
import notifications.DirNotification;
import notifications.GenerateMazeNotification;
import notifications.LoadMazeObservableNotification;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.ExecutionException;

public class MazeModel extends Observable implements IModel{
	MyModel model=new MyModel();


	@Override
	public void loadMaze(LoadMazeObservableNotification loadMazeObservableNotification) throws ExecutionException, InterruptedException {
		this.setChanged();

		MazeLoaderThread mazeLoaderThread = new MazeLoaderThread(this, model, loadMazeObservableNotification);
		GlobalApplicationPool.getInstance().runThread(mazeLoaderThread);

	}

	@Override
	public void generateMaze(GenerateMazeNotification generateMazeNotification) throws ExecutionException, InterruptedException {
		this.setChanged();

		GenerateMazeThread generateMazeThread=new GenerateMazeThread(generateMazeNotification,this,model);
		GlobalApplicationPool.getInstance().runThread(generateMazeThread);
	}

	@Override
	public void dir(DirNotification dirNotification) {
		this.setChanged();

		DirMazeThread dirMazeThread=new DirMazeThread(dirNotification,this);

		try {
			GlobalApplicationPool.getInstance().runThread(dirMazeThread);
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
