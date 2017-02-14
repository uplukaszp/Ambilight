package AMBILIGHT_OPTIONS;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Accept_Panel extends JPanel {

	JButton acceptButton;
	public Accept_Panel() {
		acceptButton=new JButton("Accept Changes");
		add(acceptButton);
	}
	
	public void addListener(ActionListener listener)
	{
		acceptButton.addActionListener(listener);
	}
}
