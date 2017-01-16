package CUSTOMCOLORS;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Color_Type_Of_Change_Panel extends JPanel{

	private JRadioButton rand,singl,cust;
	ButtonGroup group;
	public Color_Type_Of_Change_Panel() {
		// TODO Auto-generated constructor stub
		rand=new JRadioButton("Random colors");
		singl=new JRadioButton("Single color");
		cust=new JRadioButton("Custom color");
		group=new ButtonGroup();
		
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.weightx=1;
		c.weighty=1;
		c.anchor=GridBagConstraints.WEST;
		c.gridx=1;
		c.gridy=0;
		
		add(rand,c);
		group.add(rand);
		c.gridy++;
		add(singl,c);
		group.add(singl);
		c.gridy++;
		add(cust,c);
		group.add(cust);
		
		setBorder(new TitledBorder("Type change"));
	}
	
	
}
