package com.wmu.churchlogger;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Searcher {

	public TableModel findMemberFromTable(JTable table, String search){
		int rowAmt = 7;
		Object[][] tableData = (Object[][])getTableData(table);
		List<Object[]> displayData = new ArrayList<Object[]>();
		
		for(int row = 0; row < tableData.length; row++){
			for(int col = 0; col < tableData[0].length; col++){
			
				if(((String)tableData[row][col]).equalsIgnoreCase(search)){
					System.out.println("Found");
					displayData.add(new Object[rowAmt]);
					for(int newCol = 0; newCol < rowAmt; newCol++){
						//System.out.println("Added a thing");
						displayData.get(displayData.size()-1)[newCol] = tableData[row][newCol];
					}
				}
			}
		}
		
		DefaultTableModel tableModel = new DefaultTableModel();
		Object[] columnHeadings = new String[] {
				"First Name", "Last Name", "Phone", "Email", "Join Date", "Stree Address", "Zip" 
		};
		tableModel.setColumnIdentifiers(columnHeadings);
		for(Object[] o : displayData){
			String[] recordString = new String[rowAmt];
			for(int index = 0; index < recordString.length; index++){
				recordString[index] = (String)o[index];
				System.out.println("Added " + recordString[index]);
			}tableModel.addRow(recordString);
		}
		
		return tableModel;
	}

	public Object[][] getTableData (JTable table) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
		Object[][] tableData = new Object[nRow][nCol];
		for (int i = 0 ; i < nRow ; i++)
			for (int j = 0 ; j < nCol ; j++)
				tableData[i][j] = dtm.getValueAt(i,j);
		return tableData;
	}
}
