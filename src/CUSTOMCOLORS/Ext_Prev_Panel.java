package CUSTOMCOLORS;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import AMBILIGHT_OPTIONS.Prev_Panel;

public class Ext_Prev_Panel extends Prev_Panel{

	JPanel parent;
	Ext_Prev_Panel(JPanel parent) {
		super();
		this.parent=parent;
		
	}
	
	public Dimension getPreferredSize()
	{
		Dimension d=parent.getSize();
		//d.width*=0.66;
		return d;
	}

}
