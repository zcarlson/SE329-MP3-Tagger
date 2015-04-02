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

    /**
     * Creates a blank MP3Tagger object.
     */
    public MP3Tagger() {
        this.sourcePath = null;
        this.destPath = null;
        this.metadataUpdate = false;
        this.ready = false;
        this.copyMode = true;
        this.filePattern = null;
        this.problems = new LinkedList<String>();
    }

    /**
     * Creates an MP3Tagger object with the specified parameters.
     * 
     * @param src
     *            The directory where the MP3s to be sorted are currently
     *            stored.
     * @param dest
     *            The directory where the MP3s are going to be sorted into.
     * @param update
     *            Determines whether or not the metadata for the MP3s will be
     *            updated.
     * @param copy
     *            Determines if the files are going to be copied into the dest
     *            file, or just moved there.
     * @param pattern
     *            The pattern of how the files will be organized.<br>
     *            Valid pattern strings are formatted as follows: <br>
     *            %A = Artist <br>
     *            %a = Album <br>
     *            %Y = Year <br>
     *            %T = Title <br>
     *            %t = Track <br>
     * 
     *            The following pattern, therefore, would yield the following
     *            file path:<br>
     *            pattern = " %A\%a\%t - %T.mp3"<br>
     *            resulting path = "Artist\Album\Track - Title.mp3"<br>
     *            The following symbols will be removed from the file path after
     *            the appropriate tag information has been added:<br>
     *            [ : \\ * ? \" | < > ]<br>
     */
    public MP3Tagger(String src, String dest, boolean update, boolean copy,
            String pattern) {
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
    /**
     * Sets the source directory to the given path.
     * @param path The path of the new source directory.
     * @return True upon successful completion.
     */
    public boolean setSourceFolderPath(String path) {
        this.sourcePath = path;
        this.checkReady();
        return true;
    }

    @Override
    /**
     * Sets the destination directory to the given path.
     * @param path The path of the new destination directory.
     * @return True upon successful completion.
     */
    public boolean setDestinationFolderPath(String path) {
        this.destPath = path;
        this.checkReady();
        return true;
    }

    @Override
    /**
     * Sets the file structure pattern to the given pattern.
     * @param path The new file structure pattern.
     * @return true upon successful completion.
     */
    public boolean setFileStructurePattern(String pattern) {
        this.filePattern = pattern;
        this.checkReady();
        return true;
    }

    @Override
    /**
     * This method sorts the MP3 files in the specified source directory and either moves or copies the music into the specified destination directory, in the format specified by the file structure pattern.
     * @return True upon successful completion.
     */
    public boolean start() {
        // Create an iterator containing all directories and MP3 files
        Iterator<File> iter = FileUtils.iterateFilesAndDirs(
                new File(sourcePath), new SuffixFileFilter(".mp3"),
                DirectoryFileFilter.INSTANCE);
        File current;
        String slash;

        // Determine slash type
        if (this.destPath.contains("/")) {
            // Unix
            slash = "/";
        } else {
            // Windows
            slash = "\\";
        }

        // iterate through files
        while (iter.hasNext()) {
            current = iter.next();
            // only process MP3s
            if (!current.isDirectory()) {
                update(current);
                embedAlbumArt(current);
                organize(current, slash);
            }
        }

        return true;
    }

    private void update(File current) {
        // update meta tags here
    }
    
    /**
     * Given a handle to a fully tagged mp3 file, retrieve the album art from the data server and embed it into the mp3 file.
     * @param current The fully tagged mp3 file that will have album art added to it.
     */
    private void embedAlbumArt(File current) {
    	
    	
    	
    	
    }

    /**
     * Given the handle to an mp3 file, move the file in the file system based on the input pattern provided.
     * @param current An mp3 file handle
     * @param slash backslash or forward slash, depending on the operating system.
     */
    private void organize(File current, String slash) {
        WritableMp3File currentmp3;
        try {
            // colons cause problems. need to escape the bad characters
            currentmp3 = new WritableMp3File(current);
            System.out.println(this.destPath + slash
                    + currentmp3.getPath(this.filePattern));
            // copy or move files into proper position.
            if (this.copyMode) {

                FileUtils.copyFile(current, new File(this.destPath + slash
                        + currentmp3.getPath(this.filePattern)));
            } else {
                FileUtils.moveFile(current, new File(this.destPath + slash
                        + currentmp3.getPath(this.filePattern)));
            }
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            problems.add("File already exists!   " + current.getName());
        } catch (TagException e) {
            problems.add("Incomplete Tags for " + current.getName());
        }
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

    /**
     * Checks if the MP3Tagger is ready to start().
     * 
     * @return True if MP3Tagger is ready to start, false otherwise.
     */
    private boolean checkReady() {
        if (this.sourcePath != null && this.destPath != null
                && this.filePattern != null) {
            this.ready = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * get the next problem detected in the list.
     * 
     * @return The next problem detected in the list.
     */
    public String getNextProblem() {
        if (problems.size() > 0) {
            return problems.pop();
        } else {
            return null;
        }

    }

    @Override
    public void run() {
        while (this.ready == false) {

        }
        this.start();

    }

}
