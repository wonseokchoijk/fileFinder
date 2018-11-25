package views;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

import com.cloudgarden.resource.SWTResourceManager;

import util.Const;


public class Execute {
	public static void main(String[] args) {

		Display display = Display.getDefault();
		Shell shell = new Shell(display, SWT.CLOSE | SWT.MIN);	// 최소화와 닫기만 가능.

		Main inst = new Main(shell, SWT.NULL);
		inst.initGUI();
		shell.setText(Const.TITLE);
		
		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
		shell.layout();
		if(size.x == 0 && size.y == 0) {
			inst.pack();
			shell.pack();
		} else {
			Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
			shell.setSize(shellBounds.width, shellBounds.height);
		}
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
	    Rectangle rect = shell.getBounds();
	    
	    int x = (bounds.width / 2) - (rect.width /2);
	    int y = (bounds.height / 2) - (rect.height /2);
	    
	    
	    shell.setLocation(x, y);
	    
		shell.setImage(SWTResourceManager.getImage("icon.jpg"));
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
}
