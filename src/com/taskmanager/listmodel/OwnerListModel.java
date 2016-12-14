package com.taskmanager.listmodel;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.taskmanager.tasktree.Story;
import com.taskmanager.tasktree.StoryListener;

public class OwnerListModel extends AbstractListModel<String> implements ComboBoxModel<String> {
	private ArrayList<String> owners;
	private String selected;
	
	public OwnerListModel(ArrayList<String> owners) {
		this.owners = owners;
	}
	
	public void refresh() {
		fireIntervalRemoved(this, 0, getSize() - 1);
		fireIntervalAdded(this, 0, getSize() - 1);
		fireContentsChanged(this, 0, getSize() - 1);
	}
	
	
	public void cleanList() {
		int s = owners.size();
		fireIntervalRemoved(this, 0, s > 0 ? s - 1 : 0);	
		owners.clear();
	}
	
	public void addOwner(String o) {
		owners.add(o);
		fireIntervalAdded(this, getSize() - 1, getSize() - 1);
	}
	
	public void ownerChanged(String owner) {
		int idx = owners.indexOf(owner);
		fireContentsChanged(this, idx, idx);
	}

	public void removeOwner(Story owner) {
		int idx = owners.indexOf(owner);
		owners.remove(idx);
		fireIntervalRemoved(this, idx, idx);
	}
	
	@Override
	public String getElementAt(int arg0) {
		return owners.get(arg0);
	}

	@Override
	public int getSize() {
		return owners.size();
	}

	@Override
	public Object getSelectedItem() {
		return selected;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selected = (String) anItem;
		ownerChanged(selected);
	}

}
