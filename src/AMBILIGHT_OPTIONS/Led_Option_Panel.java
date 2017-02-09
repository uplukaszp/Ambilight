package AMBILIGHT_OPTIONS;


import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

//Panel do ustalania ilosci, wraz z etykiet¹
public class Led_Option_Panel extends JPanel{


	JSpinner left_led,right_led,top_led;
	JLabel l1,l2,l3;
	
	Led_Option_Panel()
	{
		
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.weightx=100;
		c.weighty=100;
		c.anchor=GridBagConstraints.EAST;
		c.gridx=0;
		c.gridy=0;
		
		left_led=CreateSpinner(10);
		right_led= CreateSpinner(10);
		top_led= CreateSpinner(16);
		
		
		l1=new JLabel("Left");
		l2=new JLabel("Top");
		l3=new JLabel("Right");
		
		c.gridx=0;
		c.gridy=0;
		add(l1, c);

		c.gridy++;
		add(l2,c);
		
		c.gridy++;
		add(l3,c);
		
		c.anchor=GridBagConstraints.CENTER;
		c.gridy=0;
		c.gridx=1;
		add(left_led,c);
		
		c.gridy++;
		add(top_led,c);
		
		c.gridy++;
		add(right_led,c);
		
		
		this.setBorder(new TitledBorder("LED options"));
	}
	JSpinner CreateSpinner(int value)
	{
		JSpinner j=new JSpinner();
		j.setValue(value);
		j.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {if((int)((JSpinner)arg0.getSource()).getValue()<0)
				((JSpinner)arg0.getSource()).setValue(0);}});
		
		Dimension d=j.getPreferredSize();
		d.setSize(d.getWidth()+20,d.getHeight());
		j.setPreferredSize(d);
		
		return j;
	}
	public int getLeftLed()
	{
		return (int)left_led.getValue();
	}
	public int getRightLed()
	{
		return (int)right_led.getValue();
	}
	public int getTopLed()
	{
		return (int)top_led.getValue();
	}
	
}
