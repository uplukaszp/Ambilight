package CUSTOMCOLORS;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class Custom_Color_Panel extends JPanel {
	Color_Choosing_Panel colorch;
	Color_Type_Of_Change_Panel typeofch;
	Ext_Prev_Panel prev;
	Random_Settings rand;
	
	public Custom_Color_Panel() {
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.weightx=100;
		c.weighty=100;
		c.fill=GridBagConstraints.BOTH;
		c.gridx=0;
		c.gridy=0;
		
		colorch=new Color_Choosing_Panel();
		typeofch=new Color_Type_Of_Change_Panel();
		prev=new Ext_Prev_Panel();
		rand=new Random_Settings();
		
		c.gridwidth=1;
		c.gridheight=2;
		add(typeofch,c);
		
		c.gridy=2;
		//add(colorch,c);
		add(rand,c);
		c.gridwidth=2;
		c.gridheight=3;
		c.gridx=1;
		c.gridy=0;
		
		add(prev,c);
	}
}
