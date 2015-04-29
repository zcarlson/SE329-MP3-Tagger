package iastate.se329.MP3Tagger;

import java.io.File;
import java.io.IOException;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class WritableMp3File extends Mp3File {

    /**
     * Default Constructor for the WritableMp3File class.
     */
    public WritableMp3File() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates a WritableMp3File object for an mp3 file designated by arg0.
     * 
     * @param arg0
     *            The mp3 file path.
     * @throws IOException
     *             Thrown if the specified file does not exist.
     * @throws UnsupportedTagException
     *             Thrown if any required tags are missing.
     * @throws InvalidDataException
     *             Thrown if the specified file is not a proper mp3 file.
     */
    public WritableMp3File(String arg0) throws IOException,
            UnsupportedTagException, InvalidDataException {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates a WritableMp3File object for an mp3 file designated by arg0.
     * 
     * @param arg0
     *            The mp3 file.
     * @throws IOException
     *             Thrown if the specified file does not exist.
     * @throws UnsupportedTagException
     *             Thrown if any required tags are missing.
     * @throws InvalidDataException
     *             Thrown if the specified file is not a proper mp3 file.
     */
    public WritableMp3File(File arg0) throws IOException,
            UnsupportedTagException, InvalidDataException {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates a WritableMp3File object for an mp3 file designated by arg0 with
     * a buffer size of arg1.
     * 
     * @param arg0
     *            The mp3 file path.
     * @param arg1
     *            The length of the buffer.
     * @throws IOException
     *             Thrown if the specified file does not exist.
     * @throws UnsupportedTagException
     *             Thrown if any required tags are missing.
     * @throws InvalidDataException
     *             Thrown if the specified file is not a proper mp3 file.
     */
    public WritableMp3File(String arg0, int arg1) throws IOException,
            UnsupportedTagException, InvalidDataException {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates a WritableMp3File object for an mp3 file designated by arg0.
     * 
     * @param arg0
     *            The mp3 file path.
     * @param arg1
     *            Boolean to determine if the file should be scanned.
     * @throws IOException
     *             Thrown if the specified file does not exist.
     * @throws UnsupportedTagException
     *             Thrown if any required tags are missing.
     * @throws InvalidDataException
     *             Thrown if the specified file is not a proper mp3 file.
     */
    public WritableMp3File(String arg0, boolean arg1) throws IOException,
            UnsupportedTagException, InvalidDataException {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates a WritableMp3File object for an mp3 file designated by arg0 with
     * a buffer size of arg1.
     * 
     * @param arg0
     *            The mp3 file.
     * @param arg1
     *            The length of the buffer.
     * @throws IOException
     *             Thrown if the specified file does not exist.
     * @throws UnsupportedTagException
     *             Thrown if any required tags are missing.
     * @throws InvalidDataException
     *             Thrown if the specified file is not a proper mp3 file.
     */
    public WritableMp3File(File arg0, int arg1) throws IOException,
            UnsupportedTagException, InvalidDataException {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates a WritableMp3File object for an mp3 file designated by arg0 with
     * a buffer size of arg1.
     * 
     * @param arg0
     *            The mp3 file path.
     * @param arg1
     *            The length of the buffer.
     * @param arg2
     *            Boolean to determine if the file should be scanned.
     * @throws IOException
     *             Thrown if the specified file does not exist.
     * @throws UnsupportedTagException
     *             Thrown if any required tags are missing.
     * @throws InvalidDataException
     *             Thrown if the specified file is not a proper mp3 file.
     */
    public WritableMp3File(String arg0, int arg1, boolean arg2)
            throws IOException, UnsupportedTagException, InvalidDataException {
        super(arg0, arg1, arg2);
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates a WritableMp3File object for an mp3 file designated by arg0 with
     * a buffer size of arg1.
     * 
     * @param arg0
     *            The mp3 file.
     * @param arg1
     *            The length of the buffer.
     * @param arg2
     *            Boolean to determine if the file should be scanned.
     * @throws IOException
     *             Thrown if the specified file does not exist.
     * @throws UnsupportedTagException
     *             Thrown if any required tags are missing.
     * @throws InvalidDataException
     *             Thrown if the specified file is not a proper mp3 file.
     */
    public WritableMp3File(File arg0, int arg1, boolean arg2)
            throws IOException, UnsupportedTagException, InvalidDataException {
        super(arg0, arg1, arg2);
        // TODO Auto-generated constructor stub
    }

    /**
     * Reads the tags on the MP3 to provide the information specified on the
     * pattern string. Valid pattern strings are formatted as follows: <br>
     * %A = Artist <br>
     * %a = Album <br>
     * %Y = Year <br>
     * %T = Title <br>
     * %t = Track <br>
     * 
     * The following pattern, therefore, would yield the following path:<br>
     * pattern = " %A\%a\%t - %T"<br>
     * resulting path = "Artist\Album\Track - Title"<br>
     * The following symbols will be removed from the path after the appropriate
     * tag information has been added:<br>
     * [ : \\ * ? \" | < > ]<br>
     * 
     * @param pattern
     *            The pattern of the requested information.
     * @return The information contained within the tags of the MP3.
     * @throws TagException
     *             Thrown if a tag is not provided.
     */
    public String getPath(String pattern) throws TagException {
        // path cannot contain / \ : * ? " | < >
        ID3v2 tag = this.getId3v2Tag();
        try {
            pattern = pattern.replaceAll("%A", tag.getArtist().trim());
            pattern = pattern.replaceAll("%a", tag.getAlbum().trim());
            pattern = pattern.replaceAll("%Y", tag.getYear().trim());
            pattern = pattern.replaceAll("%T", tag.getTitle().trim());
            pattern = pattern.replaceAll("%t", tag.getTrack().trim());
        } catch (Exception e) {
            throw new TagException();
        }
        pattern = pattern.replaceAll("[:\\*?\"|<>]", "");
        return pattern;
    }

}
