package iastate.se329.MP3Tagger;

import iastate.se329.MP3Tagger.Test.WritableMp3File;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import com.mpatric.mp3agic.*;

public class MP3Tagger implements MP3TaggerInterface {

	private String sourcePath;
	private String destPath;
	private boolean metadataUpdate;
	private boolean copyMode;
	private String filePattern;	
	
	public MP3Tagger()
	{
		this.sourcePath = "";
		this.destPath = "";
		this.metadataUpdate = false;
		this.copyMode = true;
		this.filePattern = "";
	}
	
	public MP3Tagger(String src, String dest, boolean update, boolean copy, String pattern)
	{
		this.sourcePath = src;
		this.destPath = dest;
		this.metadataUpdate = update;
		this.copyMode = copy;
		this.filePattern = pattern;
	}
	
	@Override
	public boolean setMetadataUpdate(boolean incoming) {
		this.metadataUpdate = incoming;
		return true;
	}

	@Override
	public boolean setSourceFolderPath(String path) {
		this.sourcePath = path;
		return true;
	}

	@Override
	public boolean setDestinationFolderPath(String path) {
		this.destPath = path;
		return true;
	}

	@Override
	public boolean setFileStructurePattern(String pattern) {
		this.filePattern = pattern;
		return true;
	}

	@Override
	public boolean start() {
		
		Iterator<File> iter = FileUtils.iterateFilesAndDirs(new File(sourcePath), new SuffixFileFilter(".mp3"), DirectoryFileFilter.INSTANCE);
		File current;
		
		WritableMp3File currentmp3;
		while(iter.hasNext())
		{
			current = iter.next();
			if(!current.isDirectory())
			{
				try {
					//colons cause problems. need to escape the bad characters
					currentmp3 = new WritableMp3File(current);
					System.out.println(this.destPath +"\\"+ currentmp3.getPath(""));
					if(this.copyMode)
					{
						FileUtils.copyFile(current, new File(this.destPath +"\\"+ currentmp3.getPath("")));
					}
					else
					{
						FileUtils.moveFile(current, new File(this.destPath +"\\"+ currentmp3.getPath("")));
					}
				} catch (UnsupportedTagException e) {
					e.printStackTrace();
				} catch (InvalidDataException e) {
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("File already Exists. Skipping.");
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean pause() {
		return false;
	}

	@Override
	public String getStatus() {
		
		
		return null;
	}

	@Override
	public boolean setCopyMode(boolean incoming) {
		this.copyMode = incoming;
		return true;
	}

	@Override
	public boolean getReady() {
		if(this.sourcePath != null && this.destPath != null && this.filePattern != null)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

}
