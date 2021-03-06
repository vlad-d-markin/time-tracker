package com.taskmanager.tasktree;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractListModel;

import com.taskmanager.db.TasksDatabase;
import com.taskmanager.listmodel.OwnerListModel;
import com.taskmanager.listmodel.StoryListModel;
import com.taskmanager.listmodel.SubtaskListModel;
import com.taskmanager.listmodel.TaskListModel;

public class TasksManager {
	
	private TasksDatabase db;
	
	private ArrayList<Story> stories;
	private ArrayList<Task> tasks;
	private ArrayList<Subtask> subtasks;
	private ArrayList<String> owners;
	
	private StoryListModel storyListModel;
	private TaskListModel taskListModel;
	private SubtaskListModel subtaskListModel;
	private OwnerListModel ownerListModel;
	
	
	public TasksManager() {
		stories = new ArrayList<Story>();
		tasks = new ArrayList<Task>();
		subtasks = new ArrayList<Subtask>();
		db = new TasksDatabase();
		owners = new ArrayList<>();
		storyListModel = new StoryListModel(stories);
		taskListModel = new TaskListModel(tasks);
		subtaskListModel = new SubtaskListModel(subtasks);
		ownerListModel = new OwnerListModel(owners);
	}
	
	public void openDB(String path) {
		try{
			db.connect(path);
			System.out.println("Successfully connected to DB: " + path);
			loadStories();
			loadTasks();
			loadSubtasks();
			refreshOwners();
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
	
	public SubtaskListModel getSubtaskListModel() {
		return this.subtaskListModel;
	}
	
	public OwnerListModel getOwnerListModel() {		
		return this.ownerListModel;
	}
	
	public void loadStories() {
		try {
			ArrayList<Story> list = db.getStories();
			storyListModel.cleanList();
			for (Story story : list) {
				storyListModel.addStory(story);
			}
			
		} catch (SQLException e) {
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
	
	public void refreshOwners() {
		try {
			ArrayList<String> owners = db.getOwners();
			ownerListModel.cleanList();
			for(String o : owners)
				ownerListModel.addOwner(o);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createStory(String title, String description) {		
		try {
			Story s = new Story(title, description);
			db.createStory(s);
			loadStories();
		} catch (SQLException e) {
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
	
	public Task getTaskById(int id) {
		for (Task task : tasks) {
			if(task.getId() == id) 
				return task;
		}
		return null;
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
	
	public boolean loadSubtasks() {
		try {
			ArrayList<Subtask> list = db.getSubtasks();
			subtaskListModel.cleanList();
			for (Subtask subtask : list) {
				subtaskListModel.add(subtask);
			}
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean createSubtask(String title, String owner, String description) {
		try {
			db.createSubtask(new Subtask(title, owner, description));
			loadSubtasks();
			refreshOwners();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean removeSubtask(Subtask st) {
		try {
			db.removeSubtask(st.getId());
			subtaskListModel.remove(st);
			refreshOwners();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean saveSubtask(Subtask st) {
		try {
			db.editSubtask(st);
			refreshOwners();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Subtask> getSubtasks() {
		return subtasks;
	}
	
	public ArrayList<OverviewItem> getFilteredSubtasks(Integer storyId, Integer taskId, String owner, Date from, Date due) {
		try {
			return db.filterSubtasks(storyId, taskId, owner, from, due);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
