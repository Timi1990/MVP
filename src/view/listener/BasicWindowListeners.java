package view.listener;

import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintListener;

public interface BasicWindowListeners
{
    void addKeyListener(KeyListener keyListener);

    void addPaintListener(PaintListener paintListener);
}
