package presenter;

import java.util.Observable;
import java.util.Observer;

import model.IModel;
import view.IView;

public class Presenter implements Observer {
	
	private IModel model;
	private IView view;
	
	public Presenter(IModel model,IView view) {
		this.model=model;
		this.view=view;
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}

}
