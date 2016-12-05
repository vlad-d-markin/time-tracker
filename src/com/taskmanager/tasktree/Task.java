package com.taskmanager.tasktree;

public class Task {
	private int story_id;
	private int id;
	private String title;
	private String description;
	
	private TaskListener listener;
	
	public Task(int id, int story_id, String title, String description) {
		this.id = id;
		this.story_id = story_id;
		this.title = title;
		this.description = description;
	}
	
	public Task(int story, String title, String description) {
		this(-1, story, title, description);
	}
	
	public String toString() {
		return String.format("[T%04d] [S%04d] %s", id, story_id, title);
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getId() {
		return id;
	}
	
	public int getStoryId() {
		return story_id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public int getStory_id() {
		return story_id;
	}

	public void setStory_id(int story_id) {
		this.story_id = story_id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setListener(TaskListener listener) {
		this.listener = listener;
	}
	
	public void notifyChanged() {
		listener.notifyChanged(this);
	}
}
