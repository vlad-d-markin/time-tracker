package com.taskmanager.listmodel;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import com.taskmanager.tasktree.Story;

public class StoryListModel extends AbstractListModel<Story> {
	private ArrayList<Story> stories;
	
	public StoryListModel(ArrayList<Story> stories) {
		this.stories = stories;
	}
	
	public void refresh() {
		fireIntervalRemoved(this, 0, getSize() - 1);
		fireIntervalAdded(this, 0, getSize() - 1);
		fireContentsChanged(this, 0, getSize() - 1);
	}
	
	
	public void cleanList() {
		int s = stories.size();
		fireIntervalRemoved(this, 0, s > 0 ? s - 1 : 0);	
		stories.clear();
	}
	
	public void addStory(Story s) {
		stories.add(s);
		fireIntervalAdded(this, getSize() - 1, getSize() - 1);
	}
	
	public void storyChanged(Story story) {
		int idx = stories.indexOf(story);
		fireContentsChanged(this, idx, idx);
	}

	public void removeStory(Story story) {
		int idx = stories.indexOf(story);
		stories.remove(idx);
		fireIntervalRemoved(this, idx, idx);
	}
	
	@Override
	public Story getElementAt(int arg0) {
		return stories.get(arg0);
	}

	@Override
	public int getSize() {
		return stories.size();
	}

}
