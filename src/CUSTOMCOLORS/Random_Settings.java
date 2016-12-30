package CUSTOMCOLORS;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Random_Settings extends JPanel{
		JLabel speedl;
		JRadioButton fab,feob,fttbb,fltrb;
		JCheckBox revb1,revb2;
		JSlider speeds;
		ButtonGroup group;
	public Random_Settings() {
		// TODO Auto-generated constructor stub
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.weightx=100;
		c.weighty=100;
		c.anchor=GridBagConstraints.WEST;
		c.gridx=0;
		c.gridy=0;
		
		fab=new JRadioButton("One for all");
		fab.addActionListener(new ButtonListener());
		feob=new JRadioButton("For each other");
		feob.addActionListener(new ButtonListener());
		fttbb=new JRadioButton("From top to bottom");
		fttbb.addActionListener(new ButtonListener());
		fltrb=new JRadioButton("From left to right");
		fltrb.addActionListener(new ButtonListener());
		revb1=new JCheckBox("Reverse");
		revb2=new JCheckBox("Reverse");
		group=new ButtonGroup();
		group.add(fab);
		group.add(feob);
		group.add(fltrb);
		group.add(fttbb);
		speedl=new JLabel("Speed");
		speeds=new JSlider(1,100,50);
		speeds.addChangeListener(new SliderListener());
		Hashtable<Integer, JLabel> t=new Hashtable<>();
		t.put(new Integer(1), new JLabel("Slow"));
		t.put(new Integer(100), new JLabel("Fast"));
		speeds.setLabelTable(t);
		speeds.setPaintLabels(true);
		
		add(fab,c);
		c.gridy++;
		add(feob,c);
		c.gridy++;
		add(fttbb,c);
		c.gridy++;
		add(fltrb,c);
		c.gridx=1;
		c.gridy=2;
		add(revb1,c);
		c.gridy++;
		add(revb2,c);
		c.gridx=0;
		c.gridy++;
		add(speedl,c);
		c.gridy++;
		c.gridwidth=2;
		add(speeds,c);
	}
	class SliderListener implements ChangeListener
	{

		@Override
		public void stateChanged(ChangeEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
