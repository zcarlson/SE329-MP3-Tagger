package iastate.se329.MP3Tagger.Test;

import java.io.IOException;
import java.util.List;

import com.mpatric.mp3agic.*;

import fm.last.musicbrainz.data.model.*;
import fm.last.musicbrainz.data.dao.impl.*;
import fm.last.musicbrainz.data.hibernate.*;


public class HelloWorld {

	public static void main(String[] args) throws UnsupportedTagException, InvalidDataException, IOException
	{
		
		Mp3File file = new Mp3File("Death Grips - Get Got.mp3");
		
		System.out.println(file.getLength());
		/*
		Artist artist = new Artist();
		ArtistDaoImpl artistdao = new ArtistDaoImpl();
		List<Artist> artistList = artistdao.getByName("Beck");
		artist = artistList.get(0);
		System.out.println(artist.getName());
		*/
		System.out.println("se");
	}
	
}
