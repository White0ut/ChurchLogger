package com.wmu.churchlogger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

class CheckboxTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private DBAccess database;
	
	private String[] columnNames = {"First Name","Last Name","Attended"};
	private Object[][] data;
	
	public CheckboxTableModel(DBAccess database){
		this.database = database;
		
		try {
			data = database.updateMemberList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Integer> getSelected(){
		List<Integer> mIDs = new ArrayList<Integer>();
		int tempID = 0;
		
		for(int i = 0; i < data.length; i++){
			if((Boolean)data[i][2] == true){
				try {
					tempID = database.findMemberByName((String)data[i][0], (String)data[i][1]);
					mIDs.add(new Integer(tempID));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}return mIDs;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public Class getColumnClass(int c) {
		String s = "blahblahblah";
		Boolean d = new Boolean(false);
		
		if(c < 2){
			return s.getClass();
		}else
		return d.getClass();
	}

	public boolean isCellEditable(int row, int col) {
		if (col < 2) {
			return false;
		} else {
			return true;
		}
	}

	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}
}