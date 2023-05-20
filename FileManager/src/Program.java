import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Program extends JFrame {

	private JPanel contentPane;
	private JTextField textCurFolder;
	private File CurDir;
	private String CurDirText = "";
	private String separator = System.getProperty("file.separator");
	private JTextField textNewDirName;
	private JTextField textNewFileName;
	private JTextArea textNewFileContent;
	private JTextField textSource;
	private JTextField textDestination;
	private String selectedFile="";
	private File sourceFile, destFile;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Program() {
		
		setTitle("FileSystem Manager");
		
		CurDir = new File(System.getProperty("user.dir"));
		CurDirText = CurDir.getAbsolutePath();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Current folder:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(25, 34, 93, 21);
		contentPane.add(lblNewLabel);
		
		textCurFolder = new JTextField(CurDirText);
		textCurFolder.setBackground(Color.WHITE);
		textCurFolder.setEditable(false);
		textCurFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textCurFolder.setBounds(128, 36, 500, 19);
		contentPane.add(textCurFolder);
		textCurFolder.setColumns(10);
		
		JButton btnList = new JButton("List");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContentList ctl1 = new ContentList(Program.this, CurDir, 1);
				ctl1.setVisible(true);
			}
		});
		btnList.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnList.setBounds(25, 82, 93, 25);
		contentPane.add(btnList);
		
		JButton btnChangeUpFolder = new JButton("Change up folder");
		btnChangeUpFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CurDir = CurDir.getParentFile();
					CurDirText = CurDir.getAbsolutePath();
					textCurFolder.setText(CurDirText);
				}catch(Exception ex) {
					SM("You are on the top",0);
				}
			}
		});
		btnChangeUpFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnChangeUpFolder.setBounds(155, 82, 165, 25);
		contentPane.add(btnChangeUpFolder);
		
		JButton btnChangeChildFolder = new JButton("Change child folder");
		btnChangeChildFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContentList ctl2 = new ContentList(Program.this, CurDir, 2);
				ctl2.setVisible(true);
				String outDir = ctl2.getOutdir();
				CurDir = new File(CurDir.getPath() + separator + outDir);
				CurDirText = CurDir.getAbsolutePath();
				textCurFolder.setText(CurDirText);
			}
		});
		btnChangeChildFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnChangeChildFolder.setBounds(362, 82, 165, 25);
		contentPane.add(btnChangeChildFolder);
		
		JLabel lblCreateFolder = new JLabel("New folder name:");
		lblCreateFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCreateFolder.setBounds(372, 117, 115, 21);
		contentPane.add(lblCreateFolder);
		
		textNewDirName = new JTextField();
		textNewDirName.setBackground(Color.WHITE);
		textNewDirName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textNewDirName.setBounds(375, 139, 116, 24);
		contentPane.add(textNewDirName);
		textNewDirName.setColumns(10);
		
		JButton btnCreateNewFolder = new JButton("Create new folder");
		btnCreateNewFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newDir = RTF(textNewDirName);
				if(newDir.length() == 0) SM("New Dir Name is empty",0);
				else {
					File temp = new File(CurDir.getPath() + separator + newDir);
					if(temp.exists()) SM("A folder name is exist",0);
					else {
						temp.mkdir();
						SM("Folder created",1);
						textNewDirName.setText(null);
					}
				}
			}
		});
		btnCreateNewFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCreateNewFolder.setBounds(501, 138, 143, 25);
		contentPane.add(btnCreateNewFolder);
		
		JButton btnDeleteCurFolder = new JButton("Delete cur folder");
		btnDeleteCurFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File newCurDir = CurDir.getParentFile();
				boolean ok = CurDir.delete();
				if(!ok) SM("The folder is not empty, cannot be deleted",0);
				else {
					CurDir = newCurDir;
					CurDirText = CurDir.getAbsolutePath();
					textCurFolder.setText(CurDirText);
					SM("The current folder has become a parent folder",1);
				}
			}
		});
		btnDeleteCurFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteCurFolder.setBounds(501, 173, 143, 25);
		contentPane.add(btnDeleteCurFolder);
		
		JLabel lblNewFileContent = new JLabel("New File Content:");
		lblNewFileContent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewFileContent.setBounds(10, 117, 108, 21);
		contentPane.add(lblNewFileContent);
		
		JLabel lblNewFileContent_1 = new JLabel("New file content:");
		lblNewFileContent_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewFileContent_1.setBounds(375, 203, 108, 21);
		contentPane.add(lblNewFileContent_1);
		
		textNewFileName = new JTextField();
		textNewFileName.setBackground(Color.WHITE);
		textNewFileName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textNewFileName.setColumns(10);
		textNewFileName.setBounds(372, 230, 116, 24);
		contentPane.add(textNewFileName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 145, 342, 109);
		contentPane.add(scrollPane);
		
		JTextArea textNewFileContent = new JTextArea();
		textNewFileContent.setBackground(Color.WHITE);
		scrollPane.setViewportView(textNewFileContent);
		
		JButton btnCreateNewFile = new JButton("Create New File");
		btnCreateNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = textNewFileName.getText();
				if(fileName.length()==0) SM("File name is missing",0);
				else {
					CreateFile(textNewFileContent, fileName);
					textNewFileName.setText(null);
					textNewFileContent.setText(null);
					SM("File created",1);
				}
			}
		});
		btnCreateNewFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCreateNewFile.setBounds(501, 230, 143, 25);
		contentPane.add(btnCreateNewFile);
		
		JButton btnDeleteFile = new JButton("Delete File");
		btnDeleteFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContentList ctl3 = new ContentList(Program.this, CurDir, 3);
				ctl3.setVisible(true);
			}
		});
		btnDeleteFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteFile.setBounds(501, 265, 143, 25);
		contentPane.add(btnDeleteFile);
		
		JLabel lblCopyFile = new JLabel("Copy file:");
		lblCopyFile.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCopyFile.setBounds(10, 260, 108, 21);
		contentPane.add(lblCopyFile);
		
		JLabel lblselectTheSource = new JLabel("1.Select the source folder as a current Dir");
		lblselectTheSource.setVerticalAlignment(SwingConstants.TOP);
		lblselectTheSource.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblselectTheSource.setBounds(10, 285, 456, 19);
		contentPane.add(lblselectTheSource);
		
		JLabel lblselectTheSource_1 = new JLabel("2.Select the file from the folder, with select file button");
		lblselectTheSource_1.setVerticalAlignment(SwingConstants.TOP);
		lblselectTheSource_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblselectTheSource_1.setBounds(10, 305, 456, 19);
		contentPane.add(lblselectTheSource_1);
		
		JLabel lblselectTheSource_1_1 = new JLabel("3.Select the destination folder for the current folder");
		lblselectTheSource_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblselectTheSource_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblselectTheSource_1_1.setBounds(10, 325, 456, 19);
		contentPane.add(lblselectTheSource_1_1);
		
		JLabel lblselectTheSource_1_1_1 = new JLabel("4. Press the select button to select the current folder as the destination folder");
		lblselectTheSource_1_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblselectTheSource_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblselectTheSource_1_1_1.setBounds(10, 345, 456, 19);
		contentPane.add(lblselectTheSource_1_1_1);
		
		JLabel lblselectTheSource_1_1_1_1 = new JLabel("5.Press copy file button to copy");
		lblselectTheSource_1_1_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblselectTheSource_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblselectTheSource_1_1_1_1.setBounds(10, 365, 456, 19);
		contentPane.add(lblselectTheSource_1_1_1_1);
		
		JLabel lblselectTheSource_1_1_1_1_1 = new JLabel("Source: Folder + File");
		lblselectTheSource_1_1_1_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblselectTheSource_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblselectTheSource_1_1_1_1_1.setBounds(10, 390, 164, 20);
		contentPane.add(lblselectTheSource_1_1_1_1_1);
		
		textSource = new JTextField();
		textSource.setBackground(Color.WHITE);
		textSource.setEditable(false);
		textSource.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textSource.setColumns(10);
		textSource.setBounds(10, 410, 490, 20);
		contentPane.add(textSource);
		
		JLabel lblselectTheSource_1_1_1_1_1_1 = new JLabel("Destination Folder (+File)");
		lblselectTheSource_1_1_1_1_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblselectTheSource_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblselectTheSource_1_1_1_1_1_1.setBounds(9, 435, 165, 20);
		contentPane.add(lblselectTheSource_1_1_1_1_1_1);
		
		textDestination = new JTextField();
		textDestination.setBackground(Color.WHITE);
		textDestination.setEditable(false);
		textDestination.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textDestination.setColumns(10);
		textDestination.setBounds(10, 454, 490, 20);
		contentPane.add(textDestination);
		
		JButton btnSelectFile = new JButton("Select File");
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContentList ctl4 = new ContentList(Program.this, CurDir, 4);
				ctl4.setVisible(true);
				selectedFile = ctl4.getOutfile();
				sourceFile = new File(CurDir.getPath() + separator + selectedFile);
				textSource.setText(sourceFile.getAbsolutePath());
			}
		});
		btnSelectFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSelectFile.setBounds(510, 410, 150, 21);
		contentPane.add(btnSelectFile);
		
		JButton btnSelectDestFolder = new JButton("Select Dest. Folder");
		btnSelectDestFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				destFile = new File(CurDir.getPath() + separator + selectedFile);
				textDestination.setText(destFile.getAbsolutePath());
			}
		});
		btnSelectDestFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSelectDestFolder.setBounds(510, 455, 150, 21);
		contentPane.add(btnSelectDestFolder);
		
		JButton btnCopyFile = new JButton("Copy File");
		btnCopyFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textSource.getText().length() == 0) SM("No source file selected",0);
				else if(textDestination.getText().length() == 0) SM("No destionation folder selected",0);
				else {
					CopyFile(sourceFile, destFile);
					SM("File copied!",1);
				}
			}
		});
		btnCopyFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCopyFile.setBounds(670, 433, 100, 21);
		contentPane.add(btnCopyFile);
		
	}
	
	public static void SM(String msg,int tipus) {
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);
	}
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
	
	public void CreateFile(JTextArea jta,String fileName) {
		fileName = CurDir.getAbsolutePath()+ separator+fileName;
		try {
			PrintStream out = new PrintStream(new FileOutputStream(fileName));
			out.print(jta.getText());
			out.close();
			SM("Data is written to file",1);
		}catch(IOException ioe) {
			SM("CreateFile method" + ioe.getMessage(),0);
		}
	}
	
	public void CopyFile(File source, File dest) {
		InputStream is = null;
		OutputStream os = null;
		
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while((length = is.read(buffer)) > 0) {
				os.write(buffer,0,length);
			}
			is.close();
			os.close();
		}catch(Exception e){
			SM("CopyFile:" + e.getMessage(),0);
		}
	}
}
