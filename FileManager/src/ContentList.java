import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;

public class ContentList extends JDialog {
	private JTable table;
	private ListTM ltm;
	private String outdir = "";
	private String outFile = "";
	private String separator = System.getProperty("file.separator");

	public ContentList(JFrame f, File inDir, int mod) {
		super(f,"list of folder entries",true);
		
		setBounds(100, 100, 525, 300);
		getContentPane().setLayout(null);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExit.setBounds(410, 231, 91, 25);
		getContentPane().add(btnExit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 489, 211);
		getContentPane().add(scrollPane);
		
		Object emptmn[] = {"Check","Object Name","Dir"};
		ltm = new ListTM(emptmn, 0);
		
		File[] list = inDir.listFiles();
		for(int i=0;i<list.length;i++) {
			boolean isd = list[i].isDirectory();
			if(mod == 1) ltm.addRow(new Object[] {false,list[i].getName(),isd});
			if(mod == 2 && isd) ltm.addRow(new Object[] {false,list[i].getName(),isd});
			if(mod == 3 && !isd) ltm.addRow(new Object[] {false,list[i].getName(),isd});
		}
		
		table = new JTable(ltm);
		scrollPane.setViewportView(table);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, jel=0, x=0;
				for(x=0; x<ltm.getRowCount();x++)
					if((Boolean)ltm.getValueAt(x,0)) {db++; jel=x;}
					if(db==0) SM("No folder selected",0);
					if(db>1) SM("Multiple folders selected",0);
					if(db==1) {
						outdir = ltm.getValueAt(jel, 1).toString();
						dispose();
					}
				
			}
		});
		btnChange.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnChange.setBounds(314, 231, 91, 25);
		getContentPane().add(btnChange);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, jel=0, x=0;
				for(x=0; x<ltm.getRowCount();x++)
					if((Boolean)ltm.getValueAt(x,0)) {db++; jel=x;}
					if(db==0) SM("No data row selected",0);
					if(db>1) SM("Multiple rows selected",0);
					if(db==1) {
						if((Boolean)ltm.getValueAt(jel,2)) SM("The selected entry is not a file!",0);
						else {
							String fileName = ltm.getValueAt(jel,1).toString();
							File file = new File(inDir.getAbsoluteFile()+separator+fileName);
							try {
								Desktop.getDesktop().open(file);
							}catch(IOException ie) {
								SM("No application to open the file",0);
							}
						}
					}
			}
		});
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnView.setBounds(221, 231, 91, 25);
		getContentPane().add(btnView);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, jel=0, x=0;
				for(x=0; x<ltm.getRowCount();x++)
					if((Boolean)ltm.getValueAt(x,0)) {db++; jel=x;}
					if(db==0) SM("No such file deleted",0);
					if(db>1) SM("Multiple rows selected",0);
					
					if(db==1) {
						String fileName = ltm.getValueAt(jel, 1).toString();
						File delFile = new File(inDir.getAbsoluteFile()+separator+fileName);
						boolean ok = delFile.delete();
						if(ok) {
							ltm.removeRow(jel);
							SM("File deleted",1);
						}
					}
					
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.setBounds(128, 231, 91, 25);
		getContentPane().add(btnDelete);
		
		JButton btnSelectToCopy = new JButton("Select to copy");
		btnSelectToCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, jel=0, x=0;
				for(x=0; x<ltm.getRowCount();x++)
					if((Boolean)ltm.getValueAt(x,0)) {db++; jel=x;}
					if(db==0) SM("No file deleted",0);
					if(db>1) SM("Multiple rows selected",0);
					
					if(db==1) {
						outFile = ltm.getValueAt(jel, 1).toString();
						dispose();
					}
			}
		});
		btnSelectToCopy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSelectToCopy.setBounds(10, 231, 116, 25);
		getContentPane().add(btnSelectToCopy);
		if(mod == 4) btnSelectToCopy.setVisible(true);
		else btnSelectToCopy.setVisible(false);
		
		TableColumn tc = null;
		for (int i = 0; i < 3; i++) {
		tc = table.getColumnModel().getColumn(i);
		if (i==0 || i==2) tc.setPreferredWidth(20);
		else {tc.setPreferredWidth(250);}
		}
		
	}
	
	public static void SM(String msg,int tipus) {
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);
	}
	
	public String getOutdir() {
		return outdir;
	}
	
	public String getOutfile() {
		return outFile;
	}
	
}
