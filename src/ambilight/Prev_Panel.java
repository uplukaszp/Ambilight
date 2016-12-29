package ambilight;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Prev_Panel extends JPanel{
	int left,right,top;
	ArrayList<ArrayList<Color>> colours;
	static final int pos=10;


	Prev_Panel(int left,int top,int right,ArrayList<ArrayList<Color>> colours)
	{
		setBorder(new TitledBorder("Preview"));
		this.left=left;
		this.right=right;
		this.top=top;
		this.colours=colours;
		
	}
	public void updateColours(ArrayList<ArrayList<Color>> colours)
	{
		this.colours=colours;
	}
	public void setLedAmount(int left,int top,int right)
	{
		this.left=left;
		this.right=right;
		this.top=top;
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
		
		for(int i=0;i<left;i++)
		{
			g2.setColor(colours.get(0).get(i));
			g2.fillRect((int)(area.getX()),(int) (area.getY()+i*area.getHeight()/left),(int) (area.getWidth()/pos),(int)(area.getHeight()/left ));
			g2.setColor(Color.black);
			g2.drawRect((int)(area.getX()),(int) (area.getY()+i*area.getHeight()/left),(int) (area.getWidth()/pos),(int)(area.getHeight()/left ));
		}
		for(int i=0;i<right;i++)
		{
			g2.setColor(colours.get(1).get(i));
			g2.fillRect((int)(area.getX()+area.getWidth()*(1.0-(1.0/pos))),(int) (area.getY()+i*area.getHeight()/right),(int) ((area.getWidth())/pos),(int)(area.getHeight()/right ));
			g2.setColor(Color.black);
			g2.drawRect((int)(area.getX()+area.getWidth()*(1.0-(1.0/pos))),(int) (area.getY()+i*area.getHeight()/right),(int) ((area.getWidth())/pos),(int)(area.getHeight()/right ));
		}
		for(int i=0;i<top;i++)
		{	g2.setColor(colours.get(2).get(i));
			g2.fillRect((int)((area.getX()+area.getWidth()/pos)+((area.getWidth()-2*area.getWidth()/pos)/top)*i),(int) (area.getY()),(int)((area.getWidth()-2*area.getWidth()/pos)/top),(int)(area.getHeight()/pos));
			g2.setColor(Color.black);
			g2.drawRect((int)((area.getX()+area.getWidth()/pos)+((area.getWidth()-2*area.getWidth()/pos)/top)*i),(int) (area.getY()),(int)((area.getWidth()-2*area.getWidth()/pos)/top),(int)(area.getHeight()/pos));

		}
	}
	
	
}
