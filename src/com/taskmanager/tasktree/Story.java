package com.taskmanager.tasktree;

import java.util.ArrayList;

public class Story {
	private int id;
	private String title;
	private String description;
	private ArrayList<StoryListener> listeners;
	
	public Story(int id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.listeners = new ArrayList<StoryListener>();
	}
	
	public Story(String title, String description) {
		this(-1, title, description);
	}
	
	public void setTitle(String title) {
		this.title = title;
		notifyChanged();
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setDescription(String description) {
		this.description = description;
		notifyChanged();
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return this.title;
	}
	
	public void adddListener(StoryListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(StoryListener listener) {
		listeners.remove(listener);
	}
	
	public void notifyChanged() {
		for (StoryListener storyListener : listeners) {
			storyListener.notifyChanged(this);
		}
	}
}
