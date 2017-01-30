package CUSTOMCOLORS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.sun.xml.internal.ws.api.Component;

import AMBILIGHT_OPTIONS.Prev_Panel;

public class Selectable_Preview extends JPanel {
	static final int pos=10;
	JPanel parent;
	ArrayList<ArrayList<LED_Rectangle>> LEDs;
	int left,top,right;
	Selectable_Preview(JPanel parent,int left,int right,int top) {
		super();
		this.parent=parent;
		this.addMouseMotionListener(new DragListener());
		setBorder(new TitledBorder("Preview"));
		this.left=left;
		this.right=right;
		this.top=top;
		LEDs=new ArrayList<ArrayList<LED_Rectangle>>();
		LEDs.add(new ArrayList<LED_Rectangle>(0));
		LEDs.add(new ArrayList<LED_Rectangle>(0));
		LEDs.add(new ArrayList<LED_Rectangle>(0));
		
		
	}
	void initLED(Color c)
	{
		Rectangle area=new Rectangle();
		double reduction=0.1;
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
			LEDs.get(0).add(new LED_Rectangle((int)(area.getX()),(int) (area.getY()+i*area.getHeight()/left),(int) (area.getWidth()/pos),(int)(area.getHeight()/left),true,c));
		}
		for(int i=0;i<right;i++)
		{
			LEDs.get(1).add(new LED_Rectangle((int)(area.getX()+area.getWidth()*(1.0-(1.0/pos))),(int) (area.getY()+i*area.getHeight()/right),(int) ((area.getWidth())/pos),(int)(area.getHeight()/right),true,c));

		}
		for(int i=0;i<top;i++)
		{
			LEDs.get(2).add(new LED_Rectangle((int)((area.getX()+area.getWidth()/pos)+((area.getWidth()-2*area.getWidth()/pos)/top)*i),(int) (area.getY()),(int)((area.getWidth()-2*area.getWidth()/pos)/top),(int)(area.getHeight()/pos),true,c));

		}
	}
	void setAllSelection(boolean isSelected)
	{
		for(int i=0;i<LEDs.size();i++)
		{
			for(int j=0;j<LEDs.get(i).size();j++)
			{
				LEDs.get(i).get(j).setSelection(isSelected);
			}
		}
	}
	void updateColor(Color c)
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
		//d.width*=0.66;
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
	
	class DragListener implements MouseMotionListener
	{
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			for(int i=0;i<LEDs.size();i++)
			{
				for(int j=0;j<LEDs.get(i).size();j++)
				{
					if(LEDs.get(i).get(j).contains(arg0.getX(),arg0.getY()))
					{
						LEDs.get(i).get(j).setSelection(!LEDs.get(i).get(j).getSelection());
					}
				}
			}
		}
		@Override
		public void mouseMoved(MouseEvent arg0) {}		
	}
	
}
