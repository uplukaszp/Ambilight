package AMBILIGHT_OPTIONS;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import MAINCLASSES.SerialController;


//125cm wokol ekranu

//Panel w karcie opcji
public class Ambi_Option_Panel extends JPanel {
	
		
		Led_Option_Panel led_pane;
		Devices_Panel dev_pane;
		Render_Option_Panel opt_pane;
		Prev_Panel pr_pane;
		Color_Calculator calc;
		Timer t;
		long begint,currt;
	public Ambi_Option_Panel(String ports[])
	{
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.weightx=100;
		c.weighty=100;
		c.fill=GridBagConstraints.BOTH;
		c.gridx=0;
		c.gridy=0;
		
		opt_pane=new Render_Option_Panel();
		led_pane=new Led_Option_Panel();
		calc=new Color_Calculator(led_pane.getLeftLed(),led_pane.getTopLed(),led_pane.getRightLed(),opt_pane.getSmooth());
		
		dev_pane=new Devices_Panel(ports,calc.getDevices());
		pr_pane=new Prev_Panel(calc.calculateColours());
		
		addActionListenerToAll(led_pane,  new LedChange(), JSpinner.class);
		addActionListenerToAll(dev_pane,  new DevChange(), JComboBox.class);
		addActionListenerToAll(opt_pane,new OptChange(),JSlider.class);
		
		c.gridwidth=3;
		c.gridheight=2;
		add(dev_pane,c);
		
		c.gridy=2;
		c.gridwidth=2;
		add(led_pane,c);
		
		c.gridx=2;
		c.gridheight=2;
		c.gridwidth=4;
		add(opt_pane,c);
		
		c.gridy=0;
		c.gridx=3;
		c.gridwidth=3;
		c.gridheight=2;
		add(pr_pane,c);
		pr_pane.repaint();
		
		t=new Timer();
		t.schedule(new Trigger(),0, (int)(1000/opt_pane.getSpeed()));
		
	}
	public void setTimer(boolean isActive)
	{
		if(!isActive)
		{
			t.cancel();
		}
		else
		{
			t=new Timer();
			t.schedule(new Trigger(),0, (int)(1000/opt_pane.getSpeed()));
		}
	}
	public int[] getLedAmmount()
	{
		int tab[]=new int[3];
		tab[0]=led_pane.getLeftLed();
		tab[1]=led_pane.getRightLed();
		tab[2]=led_pane.getTopLed();
		return tab;
		
	}
	public void addActionListenerToAll(Container parent,EventListener listener,Class cl)
	{
		for(Component c:parent.getComponents())
		{
			if(c.getClass()==cl)
			{
				if(cl==JSpinner.class)
				{
				JSpinner temp=(JSpinner)(c);
				temp.addChangeListener((ChangeListener)(listener));
				}
				if(cl==JComboBox.class)
				{
					JComboBox<?> temp=(JComboBox<?>)(c);
					temp.addItemListener((ItemListener) listener);
				}
				if(cl==JSlider.class)
				{
					JSlider temp=(JSlider)(c);
					temp.addChangeListener((ChangeListener) listener);
				}
			}
		}
	}	
	class LedChange implements  ChangeListener
	{			
		@Override
		public void stateChanged(ChangeEvent e) {
			t.cancel();
			calc.setLedAmmount(led_pane.getLeftLed(),led_pane.getRightLed(),led_pane.getTopLed());
			repaint();
			t=new Timer();
			t.schedule(new Trigger(),0, (int)(1000/opt_pane.getSpeed()));
			
		}		
	}
	class DevChange implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			
			
			t.cancel();
			calc.setDevice(dev_pane.getDev());
			SerialController.closeConnection();
			SerialController.connectToPort(dev_pane.getPort());
			t=new Timer();
			t.schedule(new Trigger(),0, (int)(1000/opt_pane.getSpeed()));
			
		}
		
	}
	class OptChange implements ChangeListener
	{

		@Override
		public void stateChanged(ChangeEvent e) {
				t.cancel();
				calc.setSmoothing(opt_pane.getSmooth());
				t=new Timer();
				t.schedule(new Trigger(),0, (int)(1000/opt_pane.getSpeed()));
		}
		
	}
	class Trigger extends TimerTask
	{
		@Override
		public void run() {
			pr_pane.updateColours(calc.calculateColours());
			repaint();
			SerialController.sendColors(pr_pane.colours);
			
		}
		
	}
	
}
