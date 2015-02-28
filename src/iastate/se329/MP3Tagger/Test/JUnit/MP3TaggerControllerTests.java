package iastate.se329.MP3Tagger.Test.JUnit;

import static org.junit.Assert.*;

import java.io.File;

import iastate.se329.MP3Tagger.MP3TaggerController;

import org.junit.Test;

public class MP3TaggerControllerTests
{
	
	/**
	 * This test verifies that the MP3TaggerController can locate, copy, and sort 3 simple MP3 files.
	 */
	@Test
	public void startTest1()
	{
		File result_file1 = new File("C:\\Users\\family\\Desktop\\Inbound\\MuzakLibrary\\Bob Acri\\Bob Acri\\Sleep Away.mp3");
		File result_file2 = new File("C:\\Users\\family\\Desktop\\Inbound\\MuzakLibrary\\Mr. Scruff\\Ninja Tuna\\Kalimba.mp3");
		File result_file3 = new File("C:\\Users\\family\\Desktop\\Inbound\\MuzakLibrary\\Richard Stoltzman\\Slovak Radio Symphony Orchestra\\Fine Music, Vol. 1\\Maid with the Flaxen Hair.mp3");
		
		assertFalse("This file should not exist in this directory yet!", result_file1.exists());
		assertFalse("This file should not exist in this directory yet!", result_file2.exists());
		assertFalse("This file should not exist in this directory yet!", result_file3.exists());
		
		MP3TaggerController sorter = new MP3TaggerController();
		sorter.setCopyMode(true);
		assertFalse("Sorter should not be ready yet.", sorter.getReady());
		sorter.setSourceFolderPath("C:\\Users\\Public\\Music\\Sample Music");
		assertFalse("Sorter should not be ready yet.", sorter.getReady());
		sorter.setDestinationFolderPath("C:\\Users\\family\\Desktop\\Inbound\\MuzakLibrary");
		assertFalse("Sorter should not be ready yet.", sorter.getReady());
		sorter.setFileStructurePattern("%A\\%a\\%T.mp3");
		assertTrue("Sorter should now be ready.", sorter.getReady());
		
		sorter.run();
		
		assertTrue("This file should exist in this directory by now!", result_file1.exists());
		assertTrue("This file should exist in this directory by now!", result_file2.exists());
		assertTrue("This file should exist in this directory by now!", result_file3.exists());
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
