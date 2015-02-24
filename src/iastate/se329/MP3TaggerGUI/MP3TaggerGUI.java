package iastate.se329.MP3TaggerGUI;

import iastate.se329.MP3Tagger.MP3TaggerController;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;

public class MP3TaggerGUI extends JFrame
{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txt_fileStructureInput;
    private JTextField txt_sourceDir;
    private JTextField txt_destinationDir;
    private JFileChooser fileChooser;
    private MP3TaggerController tagger;

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
        JButton btn_start = new JButton("Start");
        btn_start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	tagger.setDestinationFolderPath(txt_destinationDir.getText());
            	tagger.setSourceFolderPath(txt_sourceDir.getText());
            	tagger.setFileStructurePattern(txt_fileStructureInput.getText());
            	if(tagger.getReady())
            	{
            		tagger.start();
            	}
                
            }
        });
        btn_start.setBounds(10, 207, 89, 23);
        contentPane.add(btn_start);

        JButton btn_stop = new JButton("Stop");
        btn_stop.setBounds(335, 207, 89, 23);
        contentPane.add(btn_stop);
    }
}
