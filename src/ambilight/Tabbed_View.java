package ambilight;

import javax.swing.*;

//Widok kart
public class Tabbed_View extends JTabbedPane {

	Option_Pane panel;
	
	Tabbed_View()
	{
		panel=new Option_Pane();
		this.addTab("Options", panel);
		repaint();
	}
}
