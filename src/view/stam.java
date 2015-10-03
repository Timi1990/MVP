package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

/**
 * Created by Timi on 10/3/2015.
 */
public class stam extends BasicWindow {

    Button b;

    public stam(int width, int height) {
        super(width, height);
    }

    @Override
    public void init() {
        b = new Button(shell, SWT.RADIO);


    }
}
