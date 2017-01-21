package CUSTOMCOLORS;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Color_Choosing_Panel extends JPanel{

	private JLabel htext,stext,btext;
	private JSlider hslider,sslider,bslider;
	private Color color;
	public Color_Choosing_Panel() {
		setBorder(new TitledBorder("Choose the color"));
		
		htext=new JLabel("Hue:");
		stext=new JLabel("Saturation:");
		btext=new JLabel("Brightness:");
		
		hslider=new JSlider(0,360,180);
		sslider=new JSlider(0,100,50);
		bslider=new JSlider(0,100,50);
		hslider.addChangeListener(new Listener());
		sslider.addChangeListener(new Listener());
		bslider.addChangeListener(new Listener());
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.weightx=1.0;
		c.weighty=0;
		//c.fill=GridBagConstraints.HORIZONTAL;
		c.anchor=GridBagConstraints.EAST;
		
		c.gridx=0;
		c.gridy=0;
		add(htext,c);
		c.gridy++;
		add(stext,c);
		c.gridy++;
		add(btext,c);
		c.gridx=1;
		c.gridy=0;
		c.anchor=GridBagConstraints.WEST;
		add(hslider,c);
		c.gridy++;
		add(sslider,c);
		c.gridy++;
		add(bslider,c);
		
		c.gridheight=5;
		c.gridy++;
		c.weightx=1.0;
		c.weighty=1.0;
		add(new JLabel(""),c);
		setBorder(new TitledBorder("Color selector"));
		color=Color.BLUE;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		g.setColor(color);
		g.fillRect(50, 50, 200, 200);
	}
	
	class Listener implements ChangeListener
	{

		@Override
		public void stateChanged(ChangeEvent e) {
			float hue=map((float)(hslider.getValue()),0.0f,360.0f,0.0f,1.0f);
			float sat=map((float)(sslider.getValue()),0.0f,100.0f,0.0f,1.0f);
			float bri=map(bslider.getValue(),0,100,0,1);
			color=new Color(Color.HSBtoRGB(hue, sat, bri));
			System.out.println(color);
			repaint();
		}
		
		private float map(float x,float in_min,float in_max,float out_min,float out_max)
		{
			 return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
			
			
		}
		
	}
}