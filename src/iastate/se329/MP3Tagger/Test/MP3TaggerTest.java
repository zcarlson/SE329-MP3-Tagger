package iastate.se329.MP3Tagger.Test;

import java.io.IOException;
import java.util.Scanner;

import iastate.se329.MP3Tagger.*;

public class MP3TaggerTest {

	public static void main(String[] args) {
		MP3TaggerController controller = new MP3TaggerController();
		controller.setSourceFolderPath("C:\\Users\\Trey\\Documents\\GitHub\\SE329-MP3-Tagger");
		controller.setDestinationFolderPath("C:\\Users\\Trey\\Documents\\GitHub\\SE329-MP3-Tagger\\TestZone");
		controller.setFileStructurePattern("%A\\%A - %a\\%t. %T.mp3");
		Thread t = new Thread(controller);
		t.start();
		
		while(t.isAlive())
		{
			
		}
		
		String problem = controller.getNextProblem();
		while(problem != null)
		{
			System.out.println(problem);
			problem = controller.getNextProblem();
		}
		
		
		

	}

}
