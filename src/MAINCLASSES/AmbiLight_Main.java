package MAINCLASSES;

import java.awt.EventQueue;
import java.util.List;
import java.util.logging.Logger;

import com.sun.glass.ui.Screen;

public class AmbiLight_Main {

	public static void main(String[] args) {
		
		
		SerialController.initialize();

				EventQueue.invokeLater(new Runnable()
		{

			@Override
			public void run() {
				
				new Main_Frame();
			
			}
			
		});
	}

}
