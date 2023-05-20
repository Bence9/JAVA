package konyvesbolt;

import javax.swing.table.DefaultTableModel;

public class ArkeresTM extends DefaultTableModel {
	
	public ArkeresTM (Object fildNames[], int rows){
		super(fildNames, rows);
		}
	
	public boolean isCellEditable(int row, int col) {
//		if (col == 0) {return true;}
		return false;
		}
	
		public Class<?> getColumnClass(int index){
//		if (index == 0) return(Boolean.class);
//		else
		if (index==0 || index==3 || index==4 || index ==5) return(Integer.class);
		return(String.class);
		}
}
