package ambilight;

import javax.swing.*;

//Widok kart
public class Tabbed_View extends JTabbedPane {

	Ambi_Option_Panel panel;
	
	Tabbed_View()
	{
		panel=new Ambi_Option_Panel();
		this.addTab("Ambilight options", panel);
		repaint();
	}
}
