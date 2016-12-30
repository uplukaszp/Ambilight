
package AMBILIGHT_OPTIONS;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Render_Option_Panel extends JPanel {

	JLabel l1,l2;
	JSlider speed_slider,smoothing_slider;
	
	Render_Option_Panel()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.weightx=100;
		c.weighty=100;
		c.anchor=GridBagConstraints.EAST;
		c.gridx=0;
		c.gridy=0;
		
		l1=new JLabel("Speed(FPS)");
		l2=new JLabel("Color smoothing");
		
		speed_slider=new JSlider(JSlider.HORIZONTAL,1,60,1);
		Hashtable<Integer, JLabel> t=new Hashtable<>();
		t.put(new Integer(1), new JLabel("1"));
		t.put(new Integer(60), new JLabel("60"));
		speed_slider.setLabelTable(t);
		speed_slider.setPaintLabels(true);

		
		
		smoothing_slider=new JSlider(JSlider.HORIZONTAL,1,10,5);
		Hashtable<Integer, JLabel> t2=new Hashtable<>();
		t2.put(new Integer(1), new JLabel("High"));
		t2.put(new Integer(10), new JLabel("Low"));
		smoothing_slider.setLabelTable(t2);
		smoothing_slider.setPaintLabels(true);
	

		c.gridx=0;
		c.gridy=0;
		add(l1, c);

		c.gridy++;
		add(l2,c);
		
		c.anchor=GridBagConstraints.CENTER;
		c.gridy=0;
		c.gridx=1;
		add(speed_slider,c);
				
		c.gridy++;
		add(smoothing_slider,c);
		
		this.setBorder(new TitledBorder("Rendering options"));
	}
	public int getSpeed()
	{
		return speed_slider.getValue();
	}
	public float getSmooth()
	{
		float f=(float)(smoothing_slider.getValue()/10.0f);
		return f;
	}
}
