package boot;

import model.IModel;
import model.MazeModel;
import presenter.Presenter;
import sun.security.util.Cache;
import view.IView;
import view.MazeView;

public class Run {

	public static void main(String[] args) {
		MazeView view=new MazeView();
		MazeModel model=new MazeModel();
		Presenter presenter=new Presenter(model, view);
		model.addObserver(presenter);
		view.addObserver(presenter);
		view.loadMaze();
		view.generate("second", 4,10,9);
		view.dir("C:\\Users\\Timi\\Desktop");
		
		System.exit(1);
		//C:\Users\Timi\Desktop\test.txt
	}

}
