package com.taskmanager.tasktree;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Subtask {
	private int id;
	private int task_id;
	private String title;
	private String owner;
	private String description;
	private Date from;
	private Date due;
	private SubtaskListener listener;
	
	private DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public Subtask(int id, int task_id, String title, String owner, String description, String from, String due) {
		this.id = id;
		this.task_id = task_id;
		this.title = title;
		this.owner = owner;
		this.description = description;
		try {
			this.from = DATE_FORMAT.parse(from);
		}
		catch (ParseException e) {
			this.from = new Date();
		}
		try {
			this.due = DATE_FORMAT.parse(due);
		}
		catch (ParseException e) {
			this.due = new Date();
		}
	}
	
	public Subtask(String title, String owner, String description) {
		this.id = -1;
		this.task_id = -1;
		this.title = title;
		this.owner = owner;
		this.description = description;
		this.from = new Date();
		this.due = new Date();
	}
	
	public String toString() {
		return String.format("[ST%04d] [T%04d] %s", id, task_id, title);
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFrom() {
		return from;
	}
	
	public String getFromText() {
		return DATE_FORMAT.format(from);
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getDue() {
		return due;
	}
	
	public String getDueText() {
		return DATE_FORMAT.format(due);
	}

	public void setDue(Date due) {
		this.due = due;
	}
	
	public void setListener(SubtaskListener listener) {
		this.listener = listener;
	}
	
	public void setTaskId(int id) {
		this.task_id = id;
	}
	
	public int getTaskId() {
		return task_id;
	}
	
	public void notifyChanged() {
		if(listener != null)
			listener.notifyChanged(this);
	}
	
}
