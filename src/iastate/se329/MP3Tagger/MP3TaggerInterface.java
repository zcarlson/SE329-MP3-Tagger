package iastate.se329.MP3Tagger;


public interface MP3TaggerInterface {
	public boolean getReady();
	public boolean setMetadataUpdate(boolean incoming);
	public boolean setCopyMode(boolean incoming);
	public boolean setSourceFolderPath(String path);
	public boolean setDestinationFolderPath(String path);
	public boolean setFileStructurePattern(String pattern);
	public boolean start();
	public boolean pause();
	public String getNextProblem();
	public String getStatus();
	
}
