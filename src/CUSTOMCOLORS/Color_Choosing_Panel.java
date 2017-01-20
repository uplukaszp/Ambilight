package CUSTOMCOLORS;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Color_Choosing_Panel extends JPanel{

	JLabel rlab,glab,blab;
	JTextArea rtext,gtext,btext;
	JPanel colours;
	Color c;
	public Color_Choosing_Panel() {
		setBorder(new TitledBorder("Choose the color"));
		rlab=new JLabel("R:");
		glab=new JLabel("G:");
		blab=new JLabel("B:");
		rtext=new JTextArea(1, 4);
		gtext=new JTextArea(1,4);
		btext=new JTextArea(1,4);
		colours=new JPanel();
		colours.add(new JLabel(""));
		
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.weightx=0;
		c.weighty=0;
		c.fill=GridBagConstraints.BOTH;
		c.gridx=0;
		c.gridy=0;
		add(rlab,c);
		c.gridx++;
		add(rtext,c);
		c.gridx++;
		add(glab,c);
		c.gridx++;
		add(gtext,c);
		c.gridx++;
		add(blab,c);
		c.gridx++;
		add(btext,c);
		//
		c.weightx=100;
		c.weighty=100;
		c.gridx=1;
		c.gridy+=2;
		c.gridwidth=4;
		c.gridheight=1;
		add(colours,c);
		colours.setBackground(Color.BLACK);
		setBorder(new TitledBorder("Color selector"));
	}

	public void paintComponent(Graphics g)
	{
		
	}
}