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
	
	public List<String> getSelectedNames(){
		List<String> names = new ArrayList<String>();
		for(int i = 0; i < data.length; i++){
			if((Boolean)data[2][i] == true){
				names.add(data[0][i] + " " + data[1][i]);
			}
		}return names;
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