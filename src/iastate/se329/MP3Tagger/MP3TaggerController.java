package iastate.se329.MP3Tagger;

import java.io.File;

public class MP3TaggerController implements MP3TaggerInterface, Runnable {

    private MP3Tagger tagger;

    public MP3TaggerController() {
        this.tagger = new MP3Tagger();
    }

    public boolean setMetadataUpdate(boolean incoming) {
        return tagger.setMetadataUpdate(incoming);
    }

    public boolean setSourceFolderPath(String path) {
        return tagger.setSourceFolderPath(path);
    }

    public boolean setDestinationFolderPath(String path) {
        return tagger.setDestinationFolderPath(path);
    }

    public boolean setFileStructurePattern(String pattern) {
        return tagger.setFileStructurePattern(pattern);
    }

    public boolean start(File current, String slash) {
        return tagger.start(current, slash);
    }

    public boolean pause() {
        return tagger.pause();
    }

    public String getStatus() {
        return tagger.getStatus();
    }

    public boolean setCopyMode(boolean incoming) {
        return tagger.setCopyMode(incoming);
    }

    public boolean getReady() {
        return tagger.getReady();
    }

    public String getNextProblem() {
        return tagger.getNextProblem();
    }

    @Override
    public void run() {
        tagger.run();

    }

}
