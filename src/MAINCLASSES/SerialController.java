package MAINCLASSES;

import com.fazecast.jSerialComm.*;

import java.awt.*;
import java.io.PrintWriter;
import java.util.*;
 
final public class SerialController {
	private static SerialPort currentPort;
	private static PrintWriter output;
	private static String portname[];
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
		currentPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		currentPort.openPort();
		initPrintStream();
	}
	private static void initPrintStream()
	{
		if(currentPort.isOpen())
		{
			output=new PrintWriter(currentPort.getOutputStream());
		}
	}
	public static void closeConnection()
	{
		
			if(currentPort.isOpen())
			{
			output.flush();
			output.close();
			currentPort.closePort();
			}
		
	}
	
	
	public static void sendColors(ArrayList<ArrayList<Color>> colors)
	{
		if(currentPort.isOpen())
		{
			char dataToSend[]=new char[3*(colors.get(0).size()+colors.get(1).size()+colors.get(2).size())];
			int pos=0;
			for(int i=0;i<colors.size();i++)
			{
				for(int j=0;j<colors.get(i).size();j++)
				{
					dataToSend[pos]=(char) colors.get(i).get(j).getRed();
					pos++;
					dataToSend[pos]=(char) colors.get(i).get(j).getGreen();
					pos++;
					dataToSend[pos]=(char) colors.get(i).get(j).getBlue();
					pos++;
				}
			}
			output.print(dataToSend);
			System.out.println(output.checkError());
		}
	}
}
