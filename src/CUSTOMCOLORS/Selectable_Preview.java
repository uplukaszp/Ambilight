package CUSTOMCOLORS;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.sun.xml.internal.ws.api.Component;

import AMBILIGHT_OPTIONS.Prev_Panel;

public class Selectable_Preview extends JPanel {
	static final int pos=10;
	private LED_Rectangle prev_selected;
	JPanel parent;
	ArrayList<ArrayList<LED_Rectangle>> LEDs;
	int left,top,right;
	JButton updateButton;
	JButton saveButton;
	Selectable_Preview(JPanel parent,int left,int right,int top) {
		super();
		this.parent=parent;
		setBorder(new TitledBorder("Preview"));
		setLayout(new BorderLayout());
		this.left=left;
		this.right=right;
		this.top=top;
		LEDs=new ArrayList<ArrayList<LED_Rectangle>>();
		LEDs.add(new ArrayList<LED_Rectangle>(0));
		LEDs.add(new ArrayList<LED_Rectangle>(0));
		LEDs.add(new ArrayList<LED_Rectangle>(0));
		
		updateButton=new JButton("Update");
		saveButton=new JButton("Save");
		JPanel temppanel=new JPanel();
		temppanel.add(updateButton);
		temppanel.add(saveButton);
		add(temppanel,BorderLayout.SOUTH);
		
	}
	void initLED(Color c)
	{
		Rectangle area=new Rectangle();
		double reduction=0.15;
		Dimension d=this.getSize();
		area.x=(int) (d.getWidth()*reduction);
		area.y=(int) (d.getHeight()*reduction);
		area.width=(int) (d.getWidth()-2*area.x);
		area.height=(int) (d.getHeight()-2*area.y);
		LEDs.get(0).clear();
		LEDs.get(1).clear();
		LEDs.get(2).clear();
		for(int i=0;i<left;i++)
		{
			LEDs.get(0).add(new LED_Rectangle((int)(area.getX()),(int) (area.getY()+i*area.getHeight()/left),(int) (area.getWidth()/pos),(int)(area.getHeight()/left),false,c));
		}
		for(int i=0;i<right;i++)
		{
			LEDs.get(1).add(new LED_Rectangle((int)(area.getX()+area.getWidth()*(1.0-(1.0/pos))),(int) (area.getY()+i*area.getHeight()/right),(int) ((area.getWidth())/pos),(int)(area.getHeight()/right),false,c));

		}
		for(int i=0;i<top;i++)
		{
			LEDs.get(2).add(new LED_Rectangle((int)((area.getX()+area.getWidth()/pos)+((area.getWidth()-2*area.getWidth()/pos)/top)*i),(int) (area.getY()),(int)((area.getWidth()-2*area.getWidth()/pos)/top),(int)(area.getHeight()/pos),false,c));

		}
		prev_selected=new LED_Rectangle(0, 0, 0, 0, false, new Color(0));
	}
	public void addListenerToButtons(ActionListener updateListener,ActionListener saveListener)
	{
		updateButton.addActionListener(updateListener);
		saveButton.addActionListener(saveListener);
	}
	
	public void setAllSelection(boolean isSelected)
	{
		for(int i=0;i<LEDs.size();i++)
		{
			for(int j=0;j<LEDs.get(i).size();j++)
			{
				LEDs.get(i).get(j).setSelection(isSelected);
			}
		}
	}
	public void updateColor(Color c)
	{
		for(int i=0;i<LEDs.size();i++)
		{
			for(int j=0;j<LEDs.get(i).size();j++)
			{
				if(LEDs.get(i).get(j).isSelected)
				{
					LEDs.get(i).get(j).setColor(c);
				}
			}
		}
	}
	
	
	public Dimension getPreferredSize()
	{
		Dimension d=parent.getSize();
		return d;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2=(Graphics2D)(g);
		for(int i=0;i<LEDs.size();i++)
		{
			for(int j=0;j<LEDs.get(i).size();j++)
			{
					if(LEDs.get(i).get(j).isSelected)
					{
						g2.setStroke(new BasicStroke(5));
					}else
					{
						g2.setStroke(new BasicStroke(2));
					}
						g2.setColor(Color.BLACK);
						g2.draw(LEDs.get(i).get(j).getBounds2D());
					
					g2.setColor(LEDs.get(i).get(j).getColor());
					g2.fill(LEDs.get(i).get(j).getBounds2D());
				
			}
		}
	}
	void changeLEDAtPosState(Point pos)
	{
		for(int i=0;i<LEDs.size();i++)
		{
			for(int j=0;j<LEDs.get(i).size();j++)
			{
				if(LEDs.get(i).get(j).contains(pos.getX(),pos.getY())&&!LEDs.get(i).get(j).equals(prev_selected))
				{
					LEDs.get(i).get(j).setSelection(!LEDs.get(i).get(j).getSelection());
					prev_selected=LEDs.get(i).get(j);
				}
			}
		}
	}
	
	ArrayList<ArrayList<Color>> getColors()
	{
		ArrayList<ArrayList<Color>>  array=new ArrayList<ArrayList<Color>>();
		for(int i=0;i<LEDs.size();i++)
		{
			array.add(new ArrayList<Color>());
			for(int j=0;j<LEDs.get(i).size();j++)
			{
				array.get(i).add(LEDs.get(i).get(j).getColor());
			}
		}
		return array;
	}
	public void setLedAmmount(int left, int right, int top, Color color) {
		this.left=left;
		this.top=top;
		this.right=right;
		initLED(color);
	}
}
