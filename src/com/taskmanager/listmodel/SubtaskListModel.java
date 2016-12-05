package com.taskmanager.listmodel;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import com.taskmanager.tasktree.Subtask;
import com.taskmanager.tasktree.SubtaskListener;
import com.taskmanager.tasktree.Task;
import com.taskmanager.tasktree.TaskListener;

public class SubtaskListModel extends AbstractListModel<Subtask> implements SubtaskListener {
	private ArrayList<Subtask> subtasks;
	
	public SubtaskListModel(ArrayList<Subtask> subtasks) {
		this.subtasks = subtasks;
	}
	
	public void refresh() {
		fireIntervalRemoved(this, 0, getSize() - 1);
		fireIntervalAdded(this, 0, getSize() - 1);
		fireContentsChanged(this, 0, getSize() - 1);
	}
	
	
	public void cleanList() {
		int s = subtasks.size();
		fireIntervalRemoved(this, 0, s > 0 ? s - 1 : 0);	
		subtasks.clear();
	}
	
	public void add(Subtask st) {
		subtasks.add(st);
		st.setListener(this);
		fireIntervalAdded(this, getSize() - 1, getSize() - 1);
	}
	
	public void subtaskChanged(Subtask subtask) {
		int idx = subtasks.indexOf(subtask);
		fireContentsChanged(this, idx, idx);
	}

	public void remove(Subtask subtask) {
		int idx = subtasks.indexOf(subtask);
		subtasks.remove(idx);
		fireIntervalRemoved(this, idx, idx);
	}
	
	@Override
	public Subtask getElementAt(int arg0) {
		return subtasks.get(arg0);
	}

	@Override
	public int getSize() {
		return subtasks.size();
	}

	@Override
	public void notifyChanged(Subtask subtask) {
		subtaskChanged(subtask);		
	}
}
