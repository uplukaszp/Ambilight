package CUSTOMCOLORS;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Color_Choosing_Panel extends JPanel{

	private JLabel htext,stext,btext;
	private JSlider hslider,sslider,bslider;
	private Color c;
	public Color_Choosing_Panel() {
		setBorder(new TitledBorder("Choose the color"));
		
		htext=new JLabel("Hue:");
		stext=new JLabel("Saturation:");
		btext=new JLabel("Brightness:");
		
		hslider=new JSlider(0,360,180);
		sslider=new JSlider(0,100,50);
		bslider=new JSlider(0,100,50);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.weightx=1.0;
		c.weighty=1.0;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.anchor=GridBagConstraints.NORTHWEST;
		c.gridx=0;
		c.gridy=0;
		add(htext,c);
		c.gridx++;
		add(hslider,c);
		
		c=new GridBagConstraints();
		c.weightx=1.0;
		c.weighty=1.0;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.anchor=GridBagConstraints.NORTHWEST;
		c.gridx=0;
		c.gridy=1;
		add(stext,c);
		c.gridx++;
		add(sslider,c);
		
		c=new GridBagConstraints();
		c.weightx=1.0;
		c.weighty=1.0;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.anchor=GridBagConstraints.NORTHWEST;
		c.gridx=0;
		c.gridy=2;
		add(btext,c);
		c.gridx++;
		add(bslider,c);
		
		c.gridheight=5;
		c.gridy++;
		add(Box.createVerticalBox(),c);
		setBorder(new TitledBorder("Color selector"));
	}
	
	public void paintComponent(Graphics g)
	{
		
	}
}