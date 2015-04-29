package iastate.se329.MP3Tagger;

public class OSCompatibility {
	
    private static final char PCDelimiter = '\\';
    private static final char MacDelimiter = '/';
    
    private static final String PCDefaultSource = "C:" + PCDelimiter + "Users" +
    	PCDelimiter + System.getProperty("user.name") + PCDelimiter + "Music";
    private static final String MacDefaultSource = MacDelimiter + "Users" + MacDelimiter + 
    	System.getProperty("user.name") + MacDelimiter + "Music";
	
    private static final String PCDefaultTarget = "C:" + PCDelimiter + "Users" +
        	PCDelimiter + System.getProperty("user.name") + PCDelimiter + "SortedMusic";
        private static final String MacDefaultTarget = MacDelimiter + "Users" + MacDelimiter + 
        	System.getProperty("user.name") + MacDelimiter + "SortedMusic";
    
    public static boolean isMac() {
		if (System.getProperty("os.name").equals("Mac OS X")) return true;
		return false;
	}
	
	public static char delimiter() {
		if (isMac()) return MacDelimiter;
		else return PCDelimiter;
	}
	
	public static String defaultSourcePath() {
		if (isMac()) return MacDefaultSource;
		return PCDefaultSource;
	}
	
	public static String defaultTargetPath() {
		if (isMac()) return MacDefaultTarget;
		return PCDefaultTarget;
	}
	
	
}
