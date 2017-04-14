package AMBILIGHT_OPTIONS;


import javax.swing.JButton;
import javax.swing.JPanel;

import AMBILIGHT_OPTIONS.Ambi_Option_Panel.AcceptListener;

public class Accept_Panel extends JPanel {

	JButton acceptButton;
	public Accept_Panel() {
		acceptButton=new JButton("Accept Changes");
		add(acceptButton);
	}
	
	public void addListener(AcceptListener acceptListener)
	{
		acceptButton.addActionListener(acceptListener);
	}
	
}
