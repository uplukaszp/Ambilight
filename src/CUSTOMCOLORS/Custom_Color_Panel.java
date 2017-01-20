package CUSTOMCOLORS;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

public class Custom_Color_Panel extends JPanel {
	Color_Choosing_Panel colorch;
	Color_Type_Of_Change_Panel typeofch;
	Ext_Prev_Panel prev;
	Random_Settings rand;
	
	public Custom_Color_Panel() {
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.weightx=0;
		c.weighty=0;
		//c.fill=GridBagConstraints.BOTH;
		c.anchor=GridBagConstraints.WEST;
		c.gridx=0;
		c.gridy=0;
		
		colorch=new Color_Choosing_Panel();
		typeofch=new Color_Type_Of_Change_Panel();
		prev=new Ext_Prev_Panel(this);
		rand=new Random_Settings();
		
		c.gridwidth=2;
		c.gridheight=2;
		add(typeofch,c);
		c.fill=GridBagConstraints.BOTH;
		c.weightx=100;
		c.weighty=100;
		c.gridx+=2;
		c.gridx-=2;
		c.gridy=2;
		add(colorch,c);
		add(rand,c);
		rand.setVisible(false);
		
		c.gridwidth=4;
		c.gridheight=4;
		c.gridx=4;
		c.gridy=0;
		
	
		add(prev,c);
		colorch.setSize(typeofch.getSize());
	}
}
