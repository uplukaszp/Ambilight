package MAINCLASSES;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

//G³ówne okno 
public class Main_Frame extends JFrame {
		
	Tabbed_View view;
	
	Main_Frame()
	{
				
		view=new Tabbed_View();
		
		this.setPreferredSize(calculateSizeOfWindow());
		initalizeWindowView();
		add(view);		
		pack();
	}
	
	 private Dimension calculateSizeOfWindow()
	{
		Dimension sizeOfWindow = Toolkit.getDefaultToolkit().getScreenSize();		
		sizeOfWindow.setSize(sizeOfWindow.getWidth()/3, sizeOfWindow.getHeight()/3);
		return sizeOfWindow;
	}
	
	private void initalizeWindowView()
	{
		this.setPreferredSize(calculateSizeOfWindow());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(view);
		setVisible(true);
		setTitle("Ambilight by pilorz");
		repaint();
		
	}
}
