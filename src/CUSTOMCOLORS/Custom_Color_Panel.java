package CUSTOMCOLORS;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Custom_Color_Panel extends JPanel {
	Color_Choosing_Panel colorch;
	Selectable_Preview prev;
	
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
		prev=new Selectable_Preview(this,10,10,10);
		prev.addComponentListener(new ResizeListener());
		
		add(colorch,c);
		c.weightx=0.75;
		c.gridx++;
		add(prev,c);
		
		
		prev.initLED(colorch.getColor());
		
		repaint();
		
	}
	
	class ColorChooseListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent e) {
			
			colorch.setColor(new Color(Color.HSBtoRGB(colorch.getHUE(), colorch.getSaturation(), colorch.getBrightness())));
			prev.updateColor(colorch.getColor());
			repaint();
		}
	}
	class ResizeListener implements ComponentListener
	{
			@Override
		public void componentHidden(ComponentEvent arg0) {
			// TODO Auto-generated method stub
				prev.initLED(colorch.getColor());
	
		}
	
		@Override
		public void componentMoved(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			prev.initLED(colorch.getColor());
	
		}
	
		@Override
		public void componentResized(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			prev.initLED(colorch.getColor());
	
		}
	
		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			prev.initLED(colorch.getColor());
	
			
		}
	}
	
}
