package iastate.se329.MP3Tagger.Test.JUnit;

import static org.junit.Assert.*;

import java.io.File;

import iastate.se329.MP3Tagger.MP3TaggerController;
import iastate.se329.MP3Tagger.OSCompatibility;

import org.junit.Test;

public class MP3TaggerControllerTests
{
	
	/**
	 * This test verifies that the MP3TaggerController can locate, copy, and sort 3 simple MP3 files.
	 */
	@Test
	public void startTest1()
	{
		File[] resultFiles;
		if (OSCompatibility.isMac()) {
			File[] files = {
					new File("/Users/anneore/SortedMusic/Kris Wilson/Unknown Album/What Trees Reach For.mp3"),
					new File("/Users/anneore/SortedMusic/Death Cab For Cutie/Transatlanticism/04 Expo '86.mp3"),
					new File("/Users/anneore/SortedMusic/Death Cab For Cutie/Transatlanticism/02 Lightness.mp3")
			};
			resultFiles = files;
		} else {
			File[] files = {
					new File("C:\\Users\\family\\Desktop\\Inbound\\MuzakLibrary\\Bob Acri\\Bob Acri\\Sleep Away.mp3"),
					new File("C:\\Users\\family\\Desktop\\Inbound\\MuzakLibrary\\Mr. Scruff\\Ninja Tuna\\Kalimba.mp3"),
					new File("C:\\Users\\family\\Desktop\\Inbound\\MuzakLibrary\\Richard Stoltzman\\Slovak Radio Symphony Orchestra\\Fine Music, Vol. 1\\Maid with the Flaxen Hair.mp3")	
			};
			resultFiles = files;
		}
			
		for (File i : resultFiles) {
			assertFalse("File should not exist but does!", i.exists());
		}
		
		MP3TaggerController sorter = new MP3TaggerController();
		sorter.setCopyMode(true);
		assertFalse("Sorter should not be ready yet.", sorter.getReady());
		sorter.setSourceFolderPath(OSCompatibility.defaultSourcePath());
		assertFalse("Sorter should not be ready yet.", sorter.getReady());
		sorter.setDestinationFolderPath(OSCompatibility.defaultTargetPath());
		assertFalse("Sorter should not be ready yet.", sorter.getReady());
		sorter.setFileStructurePattern("%A" + OSCompatibility.delimiter() + "%a" + OSCompatibility.delimiter() + "%T.mp3");
		assertTrue("Sorter should now be ready.", sorter.getReady());

		sorter.run();
		
		for (File i : resultFiles) {
			assertTrue("File should exist but does not", i.exists());
		}
	}
	
	/**
	 * This test checks if the Threading functionality of the MP3TaggerController class works as expected.
	 */
	@Test
	public void threadTest1()
	{
		MP3TaggerController sorter = new MP3TaggerController();
		
		sorter.setCopyMode(true);
		Thread test_thread = new Thread(sorter);
		
		test_thread.start();
		
		sorter.setSourceFolderPath("C:\\Users\\Public\\Music\\Sample Music");
		sorter.setDestinationFolderPath("C:\\Users\\family\\Desktop\\Inbound\\MuzakLibrary");
		sorter.setFileStructurePattern("%A\\%a\\%T.mp3");
		
		while(test_thread.isAlive());
		
		assertTrue("This file should now exist.", new File("C:\\Users\\family\\Desktop\\Inbound\\MuzakLibrary\\Bob Acri\\Bob Acri\\Sleep Away.mp3").exists());
		assertTrue("This file should now exist.", new File("C:\\Users\\family\\Desktop\\Inbound\\MuzakLibrary\\Mr. Scruff\\Ninja Tuna\\Kalimba.mp3").exists());
		assertTrue("This file should now exist.", new File("C:\\Users\\family\\Desktop\\Inbound\\MuzakLibrary\\Richard Stoltzman\\Slovak Radio Symphony Orchestra\\Fine Music, Vol. 1\\Maid with the Flaxen Hair.mp3").exists());
	}
}
