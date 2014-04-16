package com.wmu.churchlogger;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

class CheckboxTableModel extends AbstractTableModel {
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

	/*
	 * Don't need to implement this method unless your table's
	 * editable.
	 */
	public boolean isCellEditable(int row, int col) {
		//Note that the data/cell address is constant,
		//no matter where the cell appears onscreen.
		if (col < 2) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Don't need to implement this method unless your table's
	 * data can change.
	 */
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}
}