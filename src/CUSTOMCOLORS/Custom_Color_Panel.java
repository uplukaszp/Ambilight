package CUSTOMCOLORS;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
		c.anchor=GridBagConstraints.WEST;
		c.gridx=0;
		c.gridy=0;
		
		colorch=new Color_Choosing_Panel();
		colorch.addListener(new ColorChooseListener());
		
		typeofch=new Color_Type_Of_Change_Panel();
		typeofch.addListener(new TypeOfChangeListener());
		prev=new Ext_Prev_Panel(this);
		rand=new Random_Settings();
		
		
		c.fill=GridBagConstraints.BOTH;
		c.gridwidth=4;
		c.gridheight=2;
		add(typeofch,c);
		
		
		c.weighty=100;
		c.gridx+=2;
		c.gridx-=2;
		c.gridy=2;
		add(colorch,c);
		add(rand,c);
		colorch.setVisible(false);
		
		c.weightx=100;
		c.gridwidth=4;
		c.gridheight=4;
		c.gridx=4;
		c.gridy=0;
		
	
		add(prev,c);
		colorch.setSize(typeofch.getSize());
	}
	
	class ColorChooseListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent e) {
			
			colorch.setColor(new Color(Color.HSBtoRGB(colorch.getHUE(), colorch.getSaturation(), colorch.getBrightness())));
			repaint();
		}
		
		
	}
	class TypeOfChangeListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JRadioButton source=(JRadioButton)(arg0.getSource());
			if(source.getText().contains("Random"))
			{
				colorch.setVisible(false);
				rand.setVisible(true);
				
			}
			if(source.getText().contains("Single")||source.getText().contains("Custom"))
			{
				colorch.setVisible(true);
				rand.setVisible(false);
			}
			colorch.setSize(rand.getSize());
			repaint();
			
		}

		
	}
	
}
