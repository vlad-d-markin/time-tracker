package com.taskmanager.tasktree;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;

import com.taskmanager.db.TasksDatabase;
import com.taskmanager.listmodel.StoryListModel;
import com.taskmanager.listmodel.TaskListModel;

public class TasksManager {
	
	private TasksDatabase db;
	
	private ArrayList<Story> stories;
	private ArrayList<Task> tasks;
	
	private StoryListModel storyListModel;
	private TaskListModel taskListModel;
	
	
	public TasksManager() {
		stories = new ArrayList<Story>();
		tasks = new ArrayList<Task>();
		db = new TasksDatabase();
		storyListModel = new StoryListModel(stories);
		taskListModel = new TaskListModel(tasks);
	}
	
	public void openDB(String path) {
		try{
			db.connect(path);
			System.out.println("Successfully connected to DB: " + path);
			loadStories();
			loadTasks();
		}
		catch(SQLException e) {
			System.err.println("DB error while connecting.");
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void closeDB() {
		try{
			db.close();
		}
		catch(SQLException e) {
			System.err.println("DB error while disconnecting.");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Story> getStories() {
		return stories;
	}
	
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	public StoryListModel getStoryListModel() {
		return this.storyListModel;
	}
	
	public TaskListModel getTaskListModel() {
		return this.taskListModel;
	}
	
	
	public void loadStories() {
		try {
			ArrayList<Story> list = db.getStories();
			storyListModel.cleanList();
			for (Story story : list) {
				storyListModel.addStory(story);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Story getStoryById(int id) {
		for (Story story : stories) {
			if(story.getId() == id) 
				return story;
		}
		return null;
	}
	
	public void createStory(String title, String description) {		
		try {
			Story s = new Story(title, description);
			db.createStory(s);
			loadStories();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveStory(Story s) {
		try {
			db.editStory(s);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void removeStory(Story s) {
		try {
			db.removeStory(s.getId());
			storyListModel.removeStory(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadTasks() {
		try {
			ArrayList<Task> list = db.getTasks();
			taskListModel.cleanList();
			for (Task task : list) {
				taskListModel.add(task);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean createTask(String title, String description) {
		try {
			db.createTask(new Task(-1, title, description));
			loadTasks();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean removeTask(Task t) {
		try {
			db.removeTask(t.getId());
			taskListModel.remove(t);
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean saveTask(Task t) {
		try {
			db.editTask(t);
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
