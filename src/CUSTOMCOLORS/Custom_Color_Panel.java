package CUSTOMCOLORS;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Custom_Color_Panel extends JPanel {
	Color_Choosing_Panel colorch;
	Ext_Prev_Panel prev;
	
	public Custom_Color_Panel() {
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		c.weightx=0.15;
		c.weighty=100;
		c.fill=GridBagConstraints.BOTH;
		colorch=new Color_Choosing_Panel();
		colorch.addListener(new ColorChooseListener());
		prev=new Ext_Prev_Panel(this);
		
		add(colorch,c);
		c.weightx=0.75;
		c.gridx++;
		add(prev,c);
	}
	
	class ColorChooseListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent e) {
			
			colorch.setColor(new Color(Color.HSBtoRGB(colorch.getHUE(), colorch.getSaturation(), colorch.getBrightness())));
			repaint();
		}
	}
	
}
