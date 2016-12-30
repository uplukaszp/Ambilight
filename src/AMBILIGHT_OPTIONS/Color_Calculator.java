package AMBILIGHT_OPTIONS;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

public class Color_Calculator   {

	static final int pos=15;
	int left,right,top;
	GraphicsDevice device;
	BufferedImage img2;
	Graphics2D g;
	BufferedImage img;
	Rectangle area;
	
	private GraphicsEnvironment ge;
	private GraphicsDevice[] gd;
	
	 ArrayList<ArrayList<Color>> colours;

	Color_Calculator(int left,int right,int top,float smoothing)
	{
		setLedAmmount(left, right, top);
		
		ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		gd=ge.getScreenDevices();		
		device=gd[0];
		
		img2=new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
		setSmoothing(smoothing);
		
		
	}
	void setDevice(int dev)
	{
		if(dev>gd.length-1)dev=0;
		device=gd[dev];
		area=new Rectangle(device.getDefaultConfiguration().getBounds().x,device.getDefaultConfiguration().getBounds().y,device.getDefaultConfiguration().getBounds().width,device.getDefaultConfiguration().getBounds().height);
		
	}
	String[] getDevices()
	{
		String devs[]=new String[gd.length];
		for(int i=0;i<gd.length;i++)
		{
			devs[i]=new String(gd[i].getIDstring().substring(1)+" "+gd[i].getDisplayMode().getWidth()+"x"+gd[i].getDisplayMode().getHeight());
		}
		
		return devs;
	}
	void setLedAmmount(int left,int right,int top)
	{
		this.left=left;
		this.right=right;
		this.top=top;
		colours=new ArrayList<ArrayList<Color>>();
		colours.add(new ArrayList<Color>(Collections.nCopies(left, Color.WHITE)));
		colours.add(new ArrayList<Color>(Collections.nCopies(right, Color.WHITE)));
		colours.add(new ArrayList<Color>(Collections.nCopies(top, Color.WHITE)));
	}
	void setSmoothing(float smoothing)
	{
		g=img2.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_SPEED);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,smoothing));
		area=new Rectangle(device.getDefaultConfiguration().getBounds().x,device.getDefaultConfiguration().getBounds().y,device.getDefaultConfiguration().getBounds().width,device.getDefaultConfiguration().getBounds().height);
	
	}
 ArrayList<ArrayList<Color>> calculateColours()
 {
	
	 img=ScreenShoter.getScreenshot(area);
	 
	 //debug
	 /*try {
			ImageIO.write(img.getSubimage(rtoscan.x, rtoscan.y, rtoscan.width, rtoscan.height), "jpg", new File("img.jpg"));
	 	} catch (IOException e) {
			// TODO Auto-generated catch block
	 		e.printStackTrace();
		}*/
	
	 Rectangle rtoscan=new Rectangle();
	 colours.get(0).clear();
	 colours.get(1).clear();
	 colours.get(2).clear();
	 for(int i=0;i<left;i++)
	 {		 
		
		rtoscan.x=0;
		rtoscan.y=(int) (i*area.getHeight()/left);
		rtoscan.width=(int) (area.getWidth()/pos);
		rtoscan.height=(int) (area.getHeight()/left);
		colours.get(0).add(i, scaling(img,rtoscan));
	 }
	 
	 for(int i=0;i<right;i++)
	 {
		 rtoscan.x=(int) (area.getWidth()*(1.0-(1.0/pos)));
		 rtoscan.y=(int) (i*area.getHeight()/right);
		 rtoscan.width=(int) ((area.getWidth())/pos);
		 rtoscan.height=(int)(area.getHeight()/right );
		 colours.get(1).add(i,scaling(img,rtoscan));
	 }
	 
	 for(int i=0;i<top;i++)
	 {
		 rtoscan.x=(int)( area.getWidth()/pos+((area.getWidth()-2*area.getWidth()/pos)/top)*i);
		 rtoscan.y=0;
		 rtoscan.width=(int)((area.getWidth()-2*area.getWidth()/pos)/top);
		 rtoscan.height=(int)(area.getHeight()/pos);
		 colours.get(2).add(i, scaling(img,rtoscan));
		
	 }
	return colours;

 }
 private Color scaling(BufferedImage img,Rectangle rtoscan)
 {
	 g.drawImage(img.getSubimage(rtoscan.x,rtoscan.y, rtoscan.width, rtoscan.height), 0,0, 1, 1,null);
	 return new Color(img2.getRGB(0, 0),true);
 }

}

