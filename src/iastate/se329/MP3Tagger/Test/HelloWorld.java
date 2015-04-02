package iastate.se329.MP3Tagger.Test;

import iastate.se329.MP3Tagger.WritableMp3File;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.mpatric.mp3agic.*;

import fm.last.musicbrainz.data.model.*;
import fm.last.musicbrainz.data.dao.impl.*;
import fm.last.musicbrainz.data.hibernate.*;


public class HelloWorld {

	public static void main(String[] args) throws UnsupportedTagException, InvalidDataException, IOException
	{
		//file on system, in the project root folder. Need to experiment with getting paths right
//		WritableMp3File file = new WritableMp3File(".\\TestMusic\\Death Grips - Get Got.mp3");
//		
//		
//		String root = "C:\\Users\\Trey\\workspace\\SE329-MP3-Tagger\\TestMusic";
//		File source = new File(root + file.getFilename());
//		try
//		{
//			File dest = new File(root + file.getPath("%A"));
//			System.out.println(file.getPath("%A"));
//			ID3v2 id = file.getId3v2Tag();
//			//FileUtils.moveFileToDirectory(source, dest, true);
//			System.out.println(id.getArtist() + " - " + id.getAlbum());
//			System.out.println(file.getFilename() + " " + file.getLengthInSeconds() + " ");
//			
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
		
		
		
		//Extract tag from mp3 file
		
		
		//get various attributes from tag, some potentially not populated which will return null
		
		// Metadata Server Interface 
	
	}
	
}
