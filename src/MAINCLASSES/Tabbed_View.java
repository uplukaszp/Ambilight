package MAINCLASSES;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import AMBILIGHT_OPTIONS.Ambi_Option_Panel;
import CUSTOMCOLORS.Custom_Color_Panel;
//Widok kart
public class Tabbed_View extends JTabbedPane {

	int lastTab=0;
	Ambi_Option_Panel ambi;
	Custom_Color_Panel custom;
	Tabbed_View()
	{
		SerialController.initialize();
		ambi=new Ambi_Option_Panel(SerialController.getPortNames());
		custom=new Custom_Color_Panel();
		addChangeListener(new TabChangeListener());
		addTab("Ambilight options", ambi);
		addTab("Custom colors",custom);
		repaint();
		
	}

	
	
	private class TabChangeListener implements ChangeListener
	{

		@Override
		public void stateChanged(ChangeEvent e) {
			if(lastTab!=getSelectedIndex())
			{	
				if(getSelectedIndex()==1)
				{
				updateLEDAmmount();
				ambi.setTimer(false);
				lastTab=1;
				}
				if(getSelectedIndex()==0)
				{
					ambi.setTimer(true);
					lastTab=0;
				}
			}
		}
		
	}
	private void updateLEDAmmount()
	{
		int tab[]=ambi.getLedAmmount();
		custom.updateLedAmmount(tab[0], tab[1], tab[2]);
	}
	
}
