package iastate.se329.MP3Tagger.Test;

import iastate.se329.MP3Tagger.*;

public class MP3TaggerTest {

	public static void main(String[] args) {
		MP3TaggerController controller = new MP3TaggerController();
		controller.setSourceFolderPath("C:\\Users\\Trey\\Documents\\GitHub\\SE329-MP3-Tagger");
		controller.setDestinationFolderPath("C:\\Users\\Trey\\Documents\\GitHub\\SE329-MP3-Tagger\\TestZone");
		
		if(controller.start())
		{
			System.out.println("Done");
		}
		

	}

}
