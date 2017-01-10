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
		c.weightx=100;
		c.weighty=100;
		c.anchor=GridBagConstraints.CENTER;
		c.gridx=0;
		c.gridy=0;
		add(rlab,c);
		c.gridx++;
		add(rtext,c);
		c.gridx+=2;
		add(glab,c);
		c.gridx++;
		add(gtext,c);
		c.gridx+=2;
		add(blab,c);
		c.gridx++;
		add(btext,c);
		c.gridx=0;
		c.gridwidth=8;
		c.gridheight=4;
		add(colours,c);
	}
}