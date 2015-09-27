package presenter;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

import controller.MazeDoesntExistsException;
import model.IModel;
import notifications.*;
import view.IView;

import javax.management.Notification;

public class Presenter implements Observer {
	
	private IModel model;
	private IView view;
	
	public Presenter(IModel model,IView view) {
		this.model=model;
		this.view=view;
	}
	@Override
	public void update(Observable observable, Object obj) {
		ObservableNotification notification = (ObservableNotification)obj;
		if(observable==model)
		{
			switch (notification.getNotificationName()) {
				case ObservableNotificationNames.LoadMazeNotificationName:
					LoadMazeObservableNotification loadMazeNotification = (LoadMazeObservableNotification)notification;
					view.onMazeLoaded(loadMazeNotification.getMazeName());
					break;
				case ObservableNotificationNames.GenerateMazeNotificationName:
					GenerateMazeNotification generateMazeNotification = (GenerateMazeNotification)notification;
					view.onMazeGenerated(generateMazeNotification.getMazeName());
					break;
				case ObservableNotificationNames.DirNotificationName:
					DirNotification dirNotification= (DirNotification) notification;
					view.displayDir(dirNotification.getFileNames());
					System.out.println("came back to observer from model");
					break;

			}
		}
		else if(observable==view)
		{
			switch (notification.getNotificationName()) {
				case ObservableNotificationNames.LoadMazeNotificationName:
					LoadMazeObservableNotification loadMazeNotification = (LoadMazeObservableNotification)notification;
					try {
						model.loadMaze(loadMazeNotification);
					} catch (MazeDoesntExistsException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case ObservableNotificationNames.GenerateMazeNotificationName:
					GenerateMazeNotification generateMazeNotification = (GenerateMazeNotification)notification;
					try {
						model.generateMaze(generateMazeNotification);
					} catch (ExecutionException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;

				case ObservableNotificationNames.DirNotificationName:
					DirNotification dirNotification= (DirNotification) notification;
					model.dir(dirNotification);
					break;

			}
		}
		
	}

}
