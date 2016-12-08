package com.taskmanager.listmodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.taskmanager.tasktree.Subtask;

public class OverviewTableModel extends AbstractTableModel {
	
	private String[] columns = { "Title", "Owner", "From", "Due" };
	
	private ArrayList<Subtask> subtasks;
	private ArrayList<Subtask> filtered;
	
	public OverviewTableModel(ArrayList<Subtask> subtasks) {
		this.subtasks = subtasks;
		filtered = (ArrayList<Subtask>) subtasks.clone();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}



	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}



	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {	}



	@Override
	public int getRowCount() {
		System.out.println("Row count: " + filtered.size());
		return subtasks.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		Subtask s = subtasks.get(arg0);
		
		System.out.println("Get at: " + arg0 + " " + arg1);
		
		switch (arg1) {
		case 0:
			return s.getTitle();
			
		case 1:
			return s.getOwner();

		default:
			return null;
		}
	}

}
