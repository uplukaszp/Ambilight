package ambilight;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

//G³ówne okno 
public class Main_Frame extends JFrame {
		
	Tabbed_View view;
	
	Main_Frame()
	{
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		
		size.setSize(size.getWidth()/3, size.getHeight()/3);
		
		view=new Tabbed_View();
		
		this.setPreferredSize(size);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(view);
		setVisible(true);
		repaint();
		pack();
	}
}
