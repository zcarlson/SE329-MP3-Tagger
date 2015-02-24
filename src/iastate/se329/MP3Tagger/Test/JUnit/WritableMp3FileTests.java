package iastate.se329.MP3Tagger.Test.JUnit;

import static org.junit.Assert.*;

import java.io.IOException;

import iastate.se329.MP3Tagger.*;

import org.junit.Test;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

public class WritableMp3FileTests
{
	@Test
	/**
	 * This is an initial test of the effectiveness of the tag reading capacity of WritableMp3Files.
	 */
	public void getPathTest1()
	{
		WritableMp3File file;
		String title = "Sleep Away",
				artist = "Bob Acri",
				album = "Bob Acri",
				year = "2004",
				track = "3";
		try
		{
			file = new WritableMp3File("C:\\Users\\Public\\Music\\Sample Music\\Sleep Away.mp3");
			assertEquals("The title is inaccurate.", title, file.getPath("%T"));
			assertEquals("The artist is inaccurate.", artist, file.getPath("%A"));
			assertEquals("The album is inaccurate.", album, file.getPath("%a"));
			assertEquals("The year is inaccurate.", year, file.getPath("%Y"));
			assertEquals("The track number is inaccurate.", track, file.getPath("%t"));
		}
		catch(UnsupportedTagException e)
		{
			fail("The requested tags should be present and readable.");
		}
		catch(InvalidDataException e)
		{
			fail("The file should be a proper MP3 File.");
		}
		catch(IOException e)
		{
			fail("The file should be findable.");
		}
		catch(TagException e)
		{
			fail("The requested tags should be present and readable.");
		}
	}
	
	@Test
	/**
	 * This is the second of basic test functionality; this time, getPath()'s ability to form valid file paths is first tested.
	 */
	public void getPathTest2()
	{
		WritableMp3File file;
		String title = "Maid with the Flaxen Hair",
				artist = "Richard Stoltzman/Slovak Radio Symphony Orchestra",
				album = "Fine Music, Vol. 1",
				year = "2008",
				track = "2",
				path;
		try
		{
			file = new WritableMp3File("C:\\Users\\Public\\Music\\Sample Music\\Maid with the Flaxen Hair.mp3");
			assertEquals("The title is inaccurate.", title, file.getPath("%T"));
			assertEquals("The artist is inaccurate.", artist, file.getPath("%A"));
			assertEquals("The album is inaccurate.", album, file.getPath("%a"));
			assertEquals("The year is inaccurate.", year, file.getPath("%Y"));
			assertEquals("The track number is inaccurate.", track, file.getPath("%t"));
			path = file.getPath("%A\\%a\\%t; %T");
			assertEquals("The derived path is different from the expected path.", artist + "\\" + album + "\\" + track + "; " + title, path);
		}
		catch(UnsupportedTagException e)
		{
			fail("The requested tags should be present and readable.");
		}
		catch(InvalidDataException e)
		{
			fail("The file should be a proper MP3 File.");
		}
		catch(IOException e)
		{
			fail("The file should be findable.");
		}
		catch(TagException e)
		{
			fail("The requested tags should be present and readable.");
		}
	}
}
