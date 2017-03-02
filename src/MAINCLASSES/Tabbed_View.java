package MAINCLASSES;


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
	int lastLedAmmount[];
	Tabbed_View()
	{
		initView();
		addChangeListener(new TabChangeListener());		
		lastLedAmmount=new int[]{-1,-1,-1};
	}
	
	private void initView()
	{
		ambi=new Ambi_Option_Panel(SerialController.getPortNames());
		custom=new Custom_Color_Panel();
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
				ambi.isTimerActive(false);
				lastTab=1;
				}
				if(getSelectedIndex()==0)
				{
					ambi.isTimerActive(true);
					lastTab=0;
				}
			}
		}
		private void updateLEDAmmount()
		{		
			if(isLEDAmmountDifferent())
			{
				custom.updateLEDAmmount(ambi.getLEDAmmount());
			}
		}
		
		private boolean isLEDAmmountDifferent()
		{
			int currentLEDAmmount[]=ambi.getLEDAmmount();
			for(int i=0;i<lastLedAmmount.length;i++)
			{
				if(lastLedAmmount[i]!=currentLEDAmmount[i])
				{
					return true;
				}
			}
			return false;
		}	
	}
	
		
}
