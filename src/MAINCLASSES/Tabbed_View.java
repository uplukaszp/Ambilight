package MAINCLASSES;

import javax.swing.*;

import AMBILIGHT_OPTIONS.Ambi_Option_Panel;
import CUSTOMCOLORS.Custom_Color_Panel;
//Widok kart
public class Tabbed_View extends JTabbedPane {

	
	
	Tabbed_View()
	{
		
		addTab("Ambilight options", new Ambi_Option_Panel());
		addTab("Custom colors",new Custom_Color_Panel());
		repaint();
	}
}
