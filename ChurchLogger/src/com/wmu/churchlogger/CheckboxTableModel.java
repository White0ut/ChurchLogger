package com.wmu.churchlogger;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

class CheckboxTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private String[] columnNames = {"First Name","Last Name","Attended"};
	private Object[][] data = 
		{
			{"Dillon", "Burton", new Boolean(false)},
			{"Joe", "Cool", new Boolean(false)} 
		};
	
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
		return getValueAt(0, c).getClass();
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