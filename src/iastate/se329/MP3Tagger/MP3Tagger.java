package iastate.se329.MP3Tagger;

import java.io.File;
import java.io.IOException;


import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import com.mpatric.mp3agic.*;

public class MP3Tagger implements MP3TaggerInterface, Runnable {

	private String sourcePath;
	private String destPath;
	private boolean metadataUpdate;
	private boolean copyMode;
	private boolean ready;
	private String filePattern;
	private LinkedList<String> problems;
	
	public MP3Tagger()
	{
		this.sourcePath = null;
		this.destPath = null;
		this.metadataUpdate = false;
		this.ready = false;
		this.copyMode = true;
		this.filePattern = null;
		this.problems = new LinkedList<String>();
	}
	
	public MP3Tagger(String src, String dest, boolean update, boolean copy, String pattern)
	{
		this.sourcePath = src;
		this.destPath = dest;
		this.metadataUpdate = update;
		this.copyMode = copy;
		this.filePattern = pattern;
		this.ready = true;
		this.problems = new LinkedList<String>();
	}
	
	@Override
	public boolean setMetadataUpdate(boolean incoming) {
		this.metadataUpdate = incoming;
		return true;
	}

	@Override
	public boolean setSourceFolderPath(String path) {
		this.sourcePath = path;
		this.checkReady();
		return true;
	}

	@Override
	public boolean setDestinationFolderPath(String path) {
		this.destPath = path;
		this.checkReady();
		return true;
	}

	@Override
	public boolean setFileStructurePattern(String pattern) {
		this.filePattern = pattern;
		this.checkReady();
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
					problems.add("File already exists!   " + current.getName());
				}
			}
		}
		
		return true;
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
		return this.ready;
	}
	
	private boolean checkReady()
	{
		if(this.sourcePath != null && this.destPath != null && this.filePattern != null)
		{
			this.ready = true;
			return true;
		}
		else
		{
			return false;
		}
	}

	public String getNextProblem()
	{
		if(problems.size() > 0)
		{
			return problems.pop();
		}
		else
		{
			return null;
		}
		
	}
	
	@Override
	public void run() {
		while(this.ready == false)
		{
			
		}
		this.start();
		
	}

}
