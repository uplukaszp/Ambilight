package AMBILIGHT_OPTIONS;

import java.awt.*;


import javax.swing.*;
import javax.swing.border.TitledBorder;


//Panel opcji wyboru urz¹dzenia
public class Devices_Panel extends JPanel {
	private JLabel l1,l2;
	private JComboBox<String> devices_box;
	private JComboBox<String> ports_box;
	
	
	Devices_Panel(String ports[],String displays[])
	{
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.weightx=100;
		c.weighty=100;
		c.anchor=GridBagConstraints.EAST;
		c.gridx=0;
		c.gridy=0;
		
		l1 =new JLabel("Screen");
		l2=new JLabel("Port");
		
		
		devices_box=new JComboBox<String>();
		for(int i=0;i<displays.length;i++)
		{
			devices_box.addItem(displays[i]);
		}
		
		ports_box=new JComboBox<String>();
		for(String s:ports)
		{
			ports_box.addItem(s);
		}
		ports_box.setPreferredSize(devices_box.getPreferredSize());
		
		c.gridx=0;
		c.gridy=0;
		add(l1, c);

		c.gridy++;
		add(l2,c);
		
		c.anchor=GridBagConstraints.CENTER;
		c.gridy=0;
		c.gridx=1;
		add(devices_box,c);
				
		c.gridy++;
		add(ports_box,c);
		devices_box.setSelectedIndex(0);
		this.setBorder(new TitledBorder("Devices options"));
	}
	public String getPort()
	{
		return (String) ports_box.getSelectedItem();
	}
	public int getDev()
	{
		return devices_box.getSelectedIndex();
	}

}
