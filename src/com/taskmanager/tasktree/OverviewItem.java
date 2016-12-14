package com.taskmanager.tasktree;

public class OverviewItem {
	private String subtasksTitle;
	private String owner;
	private String due;
	private String from;
	private String taskTitle;
	private String storyTitle;
	public OverviewItem(String subtasksTitle, String owner, String due, String from, String taskTitle,
			String storyTitle) {
		super();
		this.subtasksTitle = subtasksTitle;
		this.owner = owner;
		this.due = due;
		this.from = from;
		this.taskTitle = taskTitle;
		this.storyTitle = storyTitle;
	}
	public String getSubtasksTitle() {
		return subtasksTitle;
	}
	public void setSubtasksTitle(String subtasksTitle) {
		this.subtasksTitle = subtasksTitle;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getDue() {
		return due;
	}
	public void setDue(String due) {
		this.due = due;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getStoryTitle() {
		return storyTitle;
	}
	public void setStoryTitle(String storyTitle) {
		this.storyTitle = storyTitle;
	}
}
