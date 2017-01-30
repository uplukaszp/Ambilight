package CUSTOMCOLORS;

import java.awt.Color;
import java.awt.Rectangle;

public class LED_Rectangle extends Rectangle{
	
	boolean isSelected;
	Color color;
	public LED_Rectangle(int x,int y,int w,int h,boolean isSelected,Color color) {
		super(x,y,w,h);
		this.color=color;
		this.isSelected=isSelected;
	}
	
	void setSelection(boolean isSelected)
	{
		this.isSelected=isSelected;
	}
	boolean getSelection()
	{
		return isSelected;
	}
	
	void setColor(Color c)
	{
		color =c;
	}
	Color getColor()
	{
		return color;
	}
}
