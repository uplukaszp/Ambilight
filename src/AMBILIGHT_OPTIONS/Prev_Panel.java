package AMBILIGHT_OPTIONS;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Prev_Panel extends JPanel{
	ArrayList<ArrayList<Color>> colours;
	static final int pos=10;


	Prev_Panel(ArrayList<ArrayList<Color>> colours)
	{
		setBorder(new TitledBorder("Preview"));
		this.colours=colours;
		
	}
	public void updateColours(ArrayList<ArrayList<Color>> colours)
	{
		this.colours=colours;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2=(Graphics2D)g;
		double reduction=0.2;
		Rectangle area=new Rectangle();
		area.x=(int) (getWidth()*reduction);
		area.y=(int) (getHeight()*reduction);
		area.width=getWidth()-2*area.x;
		area.height=getHeight()-2*area.y;
		
		g2.setColor(Color.black);
		g2.draw(area);
		
		for(int i=0;i<colours.get(0).size();i++)
		{
			g2.setColor(colours.get(0).get(i));
			g2.fillRect((int)(area.getX()),(int) (area.getY()+i*area.getHeight()/colours.get(0).size()),(int) (area.getWidth()/pos),(int)(area.getHeight()/colours.get(0).size() ));
			g2.setColor(Color.black);
			g2.drawRect((int)(area.getX()),(int) (area.getY()+i*area.getHeight()/colours.get(0).size()),(int) (area.getWidth()/pos),(int)(area.getHeight()/colours.get(0).size() ));
		}
		for(int i=0;i<colours.get(1).size();i++)
		{
			g2.setColor(colours.get(1).get(i));
			g2.fillRect((int)(area.getX()+area.getWidth()*(1.0-(1.0/pos))),(int) (area.getY()+i*area.getHeight()/colours.get(1).size()),(int) ((area.getWidth())/pos),(int)(area.getHeight()/colours.get(1).size() ));
			g2.setColor(Color.black);
			g2.drawRect((int)(area.getX()+area.getWidth()*(1.0-(1.0/pos))),(int) (area.getY()+i*area.getHeight()/colours.get(1).size()),(int) ((area.getWidth())/pos),(int)(area.getHeight()/colours.get(1).size() ));
		}
		for(int i=0;i<colours.get(2).size();i++)
		{	g2.setColor(colours.get(2).get(i));
			g2.fillRect((int)((area.getX()+area.getWidth()/pos)+((area.getWidth()-2*area.getWidth()/pos)/colours.get(2).size())*i),(int) (area.getY()),(int)((area.getWidth()-2*area.getWidth()/pos)/colours.get(2).size()),(int)(area.getHeight()/pos));
			g2.setColor(Color.black);
			g2.drawRect((int)((area.getX()+area.getWidth()/pos)+((area.getWidth()-2*area.getWidth()/pos)/colours.get(2).size())*i),(int) (area.getY()),(int)((area.getWidth()-2*area.getWidth()/pos)/colours.get(2).size()),(int)(area.getHeight()/pos));

		}
	}
	
	
}
