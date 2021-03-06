package CUSTOMCOLORS;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import MAINCLASSES.Actions;
import MAINCLASSES.SerialController;

public class Custom_Color_Panel extends JPanel {
	Color_Choosing_Panel colorch;
	Selectable_Preview prev;
	
	public Custom_Color_Panel() {
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		c.weightx=0.15;
		c.weighty=100;
		c.fill=GridBagConstraints.BOTH;
		colorch=new Color_Choosing_Panel();
		colorch.addListener(new ColorChooseListener());
		prev=new Selectable_Preview(this,10,10,10);
		prev.addComponentListener(new ResizeListener());
		prev.addMouseListener(new RectangleSelectionListener());
		prev.addMouseMotionListener(new RectangleSelectionListener());
		prev.addListenerToButtons(new ColorUpdater(),new ColorSaver());
		add(colorch,c);
		c.weightx=0.75;
		c.gridx++;
		add(prev,c);
		
		
		prev.initLED(colorch.getColor());
		
		repaint();
		
	}
	public void updateLEDAmmount(int left,int right,int top)
	{
		prev.setLedAmmount(left,right,top,colorch.getColor());
	}
	
	public void updateLEDAmmount(int[] currentLEDAmmount)
	{
		prev.setLedAmmount(currentLEDAmmount[0], currentLEDAmmount[1], currentLEDAmmount[2], colorch.getColor());
	}
	
	class ColorChooseListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent e) {
			
			colorch.setColor(new Color(Color.HSBtoRGB(colorch.getHUE(), colorch.getSaturation(), colorch.getBrightness())));
			prev.updateColor(colorch.getColor());

			repaint();
		}
	}
	private class ColorUpdater implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SerialController.sendColors(prev.getColors(),Actions.UPDATE);
			
		}
		
	}
	private class ColorSaver implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			SerialController.sendColors(prev.getColors(),Actions.SAVE);

			
		}
		
	}
	private class ResizeListener implements ComponentListener
	{
			@Override
		public void componentHidden(ComponentEvent arg0) {
				

		}
	
		@Override
		public void componentMoved(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			

		}
	
		@Override
		public void componentResized(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			

		}
	
		@Override
		public void componentShown(ComponentEvent arg0) {
			
			
		}
	}
	private class RectangleSelectionListener implements MouseListener,MouseMotionListener
	{
		@Override
		public void mouseDragged(MouseEvent arg0) {
			prev.changeLEDAtPosState(new Point(arg0.getX(),arg0.getY()));
			repaint();
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
			prev.changeLEDAtPosState(new Point(arg0.getX(),arg0.getY()));
			repaint();
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
		@Override
		public void mouseMoved(MouseEvent arg0) {
		}
		
	}
	
}
