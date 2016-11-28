package com.taskmanager.listmodel;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import com.taskmanager.tasktree.Story;
import com.taskmanager.tasktree.Task;
import com.taskmanager.tasktree.TaskListener;

public class TaskListModel extends AbstractListModel<Task> implements TaskListener {
private ArrayList<Task> tasks;
	
	public TaskListModel(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void refresh() {
		fireIntervalRemoved(this, 0, getSize() - 1);
		fireIntervalAdded(this, 0, getSize() - 1);
		fireContentsChanged(this, 0, getSize() - 1);
	}
	
	
	public void cleanList() {
		int s = tasks.size();
		fireIntervalRemoved(this, 0, s > 0 ? s - 1 : 0);	
		tasks.clear();
	}
	
	public void add(Task t) {
		tasks.add(t);
		t.setListener(this);
		fireIntervalAdded(this, getSize() - 1, getSize() - 1);
	}
	
	public void taskChanged(Task task) {
		int idx = tasks.indexOf(task);
		fireContentsChanged(this, idx, idx);
	}

	public void remove(Task task) {
		int idx = tasks.indexOf(task);
		tasks.remove(idx);
		fireIntervalRemoved(this, idx, idx);
	}
	
	@Override
	public Task getElementAt(int arg0) {
		return tasks.get(arg0);
	}

	@Override
	public int getSize() {
		return tasks.size();
	}

	@Override
	public void notifyChanged(Task task) {
		taskChanged(task);		
	}
}
