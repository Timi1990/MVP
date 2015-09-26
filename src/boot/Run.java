package boot;

import model.IModel;
import model.MazeModel;
import presenter.Presenter;
import view.IView;
import view.MazeView;

public class Run {

	public static void main(String[] args) {
		MazeView view=new MazeView();
		MazeModel model=new MazeModel();
		Presenter presenter=new Presenter(model, view);
		model.addObserver(presenter);
		view.addObserver(presenter);
		

	}

}
