package MAINCLASSES;

import com.fazecast.jSerialComm.*;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
 
final public class SerialController {
	private static SerialPort currentPort;
	private static String portname[];
	private static OutputStream out;
	private SerialController()
	{	
	}
	
	public static void initialize()
	{
		SerialPort[] ports=SerialPort.getCommPorts();
		currentPort=ports[0];
		portname=new String[ports.length];
		int i=0;
		for(SerialPort p:ports)
		{
			portname[i]=p.getSystemPortName();
			i++;
		}
	}
	public static String[] getPortNames()
	{
		return portname;
	}
	public static void connectToPort(String port)
	{
		currentPort=SerialPort.getCommPort(port);
		currentPort.setComPortParameters(250000, 8, 1, 0);
		currentPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 1, 1);
		currentPort.openPort();
		try
		{
			if(!currentPort.isOpen())throw(new Exception());
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	
	}

	public static void closeConnection()
	{
		if(currentPort.isOpen())
		{
			currentPort.closePort();
		}
	}
	
	
	public static void sendColors(ArrayList<ArrayList<Color>> colors)
	{
		if(currentPort.isOpen())
		{
			int pos=0;
						
			byte dataToSend[]=new byte[3*(colors.get(0).size()+colors.get(1).size()+colors.get(2).size())];
			for(int i=colors.get(1).size()-1;i>=0;i--)
			{
				dataToSend[pos]=(byte) (colors.get(1).get(i).getRed()&0xff);
				pos++;
				dataToSend[pos]= (byte) ((colors.get(1).get(i).getGreen()&0xff));
				pos++;
				dataToSend[pos]= (byte) ((colors.get(1).get(i).getBlue()&0xff));
				pos++;

			}
			for(int i=colors.get(2).size()-1;i>=0;i--)
			{
				dataToSend[pos]=(byte) (colors.get(2).get(i).getRed()&0xff);
				pos++;
				dataToSend[pos]= (byte) ((colors.get(2).get(i).getGreen()&0xff));
				pos++;
				dataToSend[pos]= (byte) ((colors.get(2).get(i).getBlue()&0xff));
				pos++;

			}
			for(int i=0;i<colors.get(0).size();i++)
			{
				dataToSend[pos]=(byte) (colors.get(0).get(i).getRed()&0xff);
				pos++;
				dataToSend[pos]= (byte) ((colors.get(0).get(i).getGreen()&0xff));
				pos++;
				dataToSend[pos]= (byte) ((colors.get(0).get(i).getBlue()&0xff));
				pos++;

			}
			currentPort.writeBytes(dataToSend, dataToSend.length);
		}
	}
}
