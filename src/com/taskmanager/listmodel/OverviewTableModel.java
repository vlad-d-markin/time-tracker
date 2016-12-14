package com.taskmanager.listmodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.taskmanager.tasktree.OverviewItem;
import com.taskmanager.tasktree.Subtask;

public class OverviewTableModel extends AbstractTableModel {
	
	private String[] columns = { "Title", "Owner", "From", "Due", "Task", "Story" };
	
	private ArrayList<OverviewItem> subtasks;
	
	public OverviewTableModel() {
		subtasks = new ArrayList<>();
	}

	
	public void setList(ArrayList<OverviewItem> subtasks) {
		this.subtasks = subtasks;
		fireTableDataChanged();
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
//		System.out.println("Row count: " + filtered.size());
		return subtasks.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		OverviewItem s = subtasks.get(arg0);
		
		
		switch (arg1) {
		case 0:
			return s.getSubtasksTitle();
			
		case 1:
			return s.getOwner();
			
		case 2:
			return s.getFrom();
			
		case 3:
			return s.getDue();
			
		case 4:
			return s.getTaskTitle();
			
		case 5:
			return s.getStoryTitle();

		default:
			return "none";
		}
	}

}
