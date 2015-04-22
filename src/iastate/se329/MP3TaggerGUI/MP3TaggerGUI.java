package iastate.se329.MP3TaggerGUI;

import iastate.se329.MP3Tagger.MP3TaggerController;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

public class MP3TaggerGUI extends JFrame implements PropertyChangeListener
{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txt_fileStructureInput;
    private JTextField txt_sourceDir;
    private JTextField txt_destinationDir;
    private JFileChooser fileChooser;
    private MP3TaggerController tagger;
    private JProgressBar progressBar;
    private JButton btn_start;
    private JButton btn_stop;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
    	
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                try
                {
                    MP3TaggerGUI frame = new MP3TaggerGUI();
                    frame.setVisible(true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MP3TaggerGUI()
    {
        // Initialization
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        
        // JMenuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenu mnEdit = new JMenu("Edit");
        menuBar.add(mnEdit);

        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);

        tagger = new MP3TaggerController();
        
        // Initialize contentPane (the JPanel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Copy Mode Check
        JCheckBox copyCheck = new JCheckBox("Copy Files");
        copyCheck.setSelected(true);
        copyCheck.setBounds(10, 165, 150, 21);
        contentPane.add(copyCheck);
        
     // AlbumArt Update Mode Check
        JCheckBox artCheck = new JCheckBox("Embed Art");
        artCheck.setSelected(false);
        artCheck.setBounds(160, 165, 150, 21);
        contentPane.add(artCheck);
        
     	// Metadata Update Mode Check
        JCheckBox metadataCheck = new JCheckBox("Update Metadata");
        metadataCheck.setSelected(false);
        metadataCheck.setBounds(10, 185, 150, 21);
        contentPane.add(metadataCheck);
        
        // File structure input
        JLabel lbl_fileStructureInput = new JLabel("File Structure");
        lbl_fileStructureInput.setToolTipText("Input the desired file structure to be created");
        lbl_fileStructureInput.setBounds(10, 10, 100, 21);
        contentPane.add(lbl_fileStructureInput);

        txt_fileStructureInput = new JTextField();
        txt_fileStructureInput.setToolTipText("Any item followed by a '/' or '\\' is a folder name. The file name is designated by the last option.  Valid options include: %A (Artist), %a (Album), %T (Track Title), %t (TrackNumber, and %Y (Year)");
        txt_fileStructureInput.setText("%A\\%a\\%T.mp3");
        txt_fileStructureInput.setBounds(10, 31, 200, 21);
        txt_fileStructureInput.addActionListener(new ActionListener() {
        	//listens to enter on the text box, updates the tagger
			@Override
			public void actionPerformed(ActionEvent e) {
				//tagger.setFileStructurePattern(txt_fileStructureInput.getText());
			}
        	
        });
        contentPane.add(txt_fileStructureInput);
        txt_fileStructureInput.setColumns(10);

        // Source directory input
        JLabel lbl_sourceDir = new JLabel("Source");
        lbl_sourceDir.setBounds(10, 65, 100, 21);
        contentPane.add(lbl_sourceDir);

        txt_sourceDir = new JTextField();
        txt_sourceDir.setText("C:\\Users\\UserName\\CurMusic");
        txt_sourceDir.setBounds(10, 86, 200, 21);
        contentPane.add(txt_sourceDir);
        txt_sourceDir.setColumns(10);

        // Destination directory input
        JLabel lbl_destinationDir = new JLabel("Destination");
        lbl_destinationDir.setBounds(10, 120, 100, 21);
        contentPane.add(lbl_destinationDir);

        txt_destinationDir = new JTextField();
        txt_destinationDir.setText("C:\\Users\\UserName\\TaggedMusic");
        txt_destinationDir.setColumns(10);
        txt_destinationDir.setBounds(10, 141, 200, 21);
        contentPane.add(txt_destinationDir);

        // File browser buttons
        JButton btn_destinationFileBrowser = new JButton("Browse");
        btn_destinationFileBrowser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int returnVal = fileChooser.showOpenDialog(MP3TaggerGUI.this);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    txt_destinationDir.setText(file.getAbsolutePath());
                    //tagger.setDestinationFolderPath(txt_destinationDir.getText());
                }
            }
        });
        btn_destinationFileBrowser.setBounds(335, 140, 89, 23);
        contentPane.add(btn_destinationFileBrowser);

        JButton btn_sourcefilebrowser = new JButton("Browse");
        btn_sourcefilebrowser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int returnVal = fileChooser.showOpenDialog(MP3TaggerGUI.this);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    txt_sourceDir.setText(file.getAbsolutePath());
                    //tagger.setSourceFolderPath(txt_sourceDir.getText());
                    
                }
            }
        });
        btn_sourcefilebrowser.setBounds(335, 85, 89, 23);
        contentPane.add(btn_sourcefilebrowser);

        // Start and stop buttons
        btn_start = new JButton("Start");
        btn_start.setActionCommand("start");
        btn_start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Task task;
            	tagger.setDestinationFolderPath(txt_destinationDir.getText());
            	tagger.setSourceFolderPath(txt_sourceDir.getText());
            	tagger.setFileStructurePattern(txt_fileStructureInput.getText());
            	tagger.setCopyMode(copyCheck.isSelected());
            	tagger.setMetadataUpdate(metadataCheck.isSelected());
       
            	if(tagger.getReady())
            	{
            		//tagger.start();
            		btn_start.setEnabled(false);
            		btn_stop.setEnabled(true);
            		//Starts the tagging operation in the worker class below
            		task = new Task();
                    task.addPropertyChangeListener(MP3TaggerGUI.this);
                    task.execute();
            	}
                
            }
        });
        
        
        btn_start.setBounds(10, 207, 89, 23);
        contentPane.add(btn_start);
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setBounds(120, 207, 200, 23);
        contentPane.add(progressBar);

        btn_stop = new JButton("Stop");
        btn_stop.setBounds(335, 207, 89, 23);
        contentPane.add(btn_stop);
        btn_stop.setEnabled(false);
    }
    
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
//            taskOutput.append(String.format(
//                    "Completed %d%% of task.\n", task.getProgress()));
        } 
    }
    
    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread. Does all of what tagger.start() used to do, but only has
         * tagger handle one file at a time. This is to track progress of how many files have been tagged.
         */
        @Override
        public Void doInBackground() {
            
            int progress = 0;
            //Initialize progress property.
            setProgress(0);
            
            Iterator<File> iter = FileUtils.iterateFilesAndDirs(
                    new File(txt_sourceDir.getText()), new SuffixFileFilter(".mp3"),
                    DirectoryFileFilter.INSTANCE);
            
            int directorySize = FileUtils.listFiles(new File(txt_sourceDir.getText()), null, true).size();
           
            File current;
            String slash;

            // Determine slash type
            if (txt_destinationDir.getText().contains("/")) {
                // Unix
                slash = "/";
            } else {
                // Windows
                slash = "\\";
            }

            // iterate through files
            while (iter.hasNext()) {
            	current = iter.next();
            	tagger.start(current, slash);
            	progress += (int)Math.ceil((1.0 / (double)directorySize) * 100.0);
                setProgress(Math.min(progress, 100));
            }
            
            
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
//            Toolkit.getDefaultToolkit().beep();
        	btn_start.setEnabled(true);
//            setCursor(null); turn off the wait cursor
//            taskOutput.append("Done!\n");
        }
    }
    
}

