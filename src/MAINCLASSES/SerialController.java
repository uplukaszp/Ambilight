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
	
	private SerialController(){}
	
	public static void initialize()
	{
		SerialPort[] ports=SerialPort.getCommPorts();
		currentPort=ports[0];
		
		portname=new String[ports.length];
		
		for(int i=0;i<ports.length;i++)
		{
			portname[i]=ports[i].getSystemPortName();
		}
	}
	public static String[] getPortNames()
	{
		return portname;
	}
	
	public static void connectToPort(String port) throws Exception
	{
		currentPort=SerialPort.getCommPort(port);
		currentPort.setComPortParameters(250000, 8, 1, 0);
		currentPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 1, 1);
		currentPort.openPort();
		if(!currentPort.isOpen())throw new Exception("Can`t open port: "+currentPort.getSystemPortName());
			
		
	
	}

	public static void closeConnection()
	{
		if(currentPort.isOpen())
		{
			currentPort.closePort();
		}
	}
	
	
	public static void sendColors(ArrayList<ArrayList<Color>> colors,Actions action)
	{
		if(currentPort.isOpen())
		{
			byte []dataToSend=convertColorToByte(colors);
			addActionTodData(dataToSend, action);
			currentPort.writeBytes(dataToSend, dataToSend.length);
			System.out.println("Sended");
		}
	}
	private static byte[] convertColorToByte(ArrayList<ArrayList<Color>> colors)
	{
		int positionInArray=0;
		
		byte convertedData[]=new byte[(3*(colors.get(0).size()+colors.get(1).size()+colors.get(2).size()))+1];
		for(int i=colors.get(1).size()-1;i>=0;i--)
		{
			convertedData[positionInArray]=(byte) (colors.get(1).get(i).getRed()&0xff);
			positionInArray++;
			convertedData[positionInArray]= (byte) ((colors.get(1).get(i).getGreen()&0xff));
			positionInArray++;
			convertedData[positionInArray]= (byte) ((colors.get(1).get(i).getBlue()&0xff));
			positionInArray++;
		}
		for(int i=colors.get(2).size()-1;i>=0;i--)
		{
			convertedData[positionInArray]=(byte) (colors.get(2).get(i).getRed()&0xff);
			positionInArray++;
			convertedData[positionInArray]= (byte) ((colors.get(2).get(i).getGreen()&0xff));
			positionInArray++;
			convertedData[positionInArray]= (byte) ((colors.get(2).get(i).getBlue()&0xff));
			positionInArray++;
		}
		for(int i=0;i<colors.get(0).size();i++)
		{
			convertedData[positionInArray]=(byte) (colors.get(0).get(i).getRed()&0xff);
			positionInArray++;
			convertedData[positionInArray]= (byte) ((colors.get(0).get(i).getGreen()&0xff));
			positionInArray++;
			convertedData[positionInArray]= (byte) ((colors.get(0).get(i).getBlue()&0xff));
			positionInArray++;
		}
		return convertedData;
	}
	
	private static void addActionTodData(byte []convertedData,Actions action)
	{
		switch (action) {
		case UPDATE:
			convertedData[convertedData.length-1]=0;
			break;
		case SAVE:
			convertedData[convertedData.length-1]=1;
			break;
		default:
			break;
		}
	}
}
