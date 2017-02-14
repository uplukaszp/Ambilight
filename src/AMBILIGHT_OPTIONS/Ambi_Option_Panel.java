package AMBILIGHT_OPTIONS;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


import MAINCLASSES.SerialController;



//Panel w karcie opcji
public class Ambi_Option_Panel extends JPanel {
	
		
		Led_Option_Panel led_pane;
		Devices_Panel dev_pane;
		Render_Option_Panel opt_pane;
		Prev_Panel pr_pane;
		Color_Calculator calc;
		Accept_Panel acc_pane;
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
		acc_pane=new Accept_Panel();
		
		
		acc_pane.addListener(new AcceptListener());
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
		
		c.gridx=5;
		c.gridy=6;
		c.gridwidth=1;
		c.gridheight=1;
		c.weightx=0;
		c.weighty=0;
		c.fill=0;
		c.anchor=GridBagConstraints.EAST;
		add(acc_pane,c);
		t=new Timer((int)(1000/opt_pane.getSpeed()), new TimerAction());
		
	}
	public void setTimer(boolean isActive)
	{
		if(!isActive)
		{
			t.stop();
		}
		else
		{
			t.start();
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
	
	class AcceptListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(t.isRunning())t.stop();
			calc.setLedAmmount(led_pane.getLeftLed(),led_pane.getRightLed(),led_pane.getTopLed());
			calc.setDevice(dev_pane.getDev());
			SerialController.closeConnection();
			SerialController.connectToPort(dev_pane.getPort());
			calc.setSmoothing(opt_pane.getSmooth());
			t.setDelay((int)(1000/opt_pane.getSpeed()));
			t.start();
		}		
	}
	
	class TimerAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			pr_pane.updateColours(calc.calculateColours());
			repaint();
			SerialController.sendColors(pr_pane.colours);			
		}		
	}	
}
