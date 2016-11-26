package com.taskmanager.tasktree;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;

import com.taskmanager.db.TasksDatabase;
import com.taskmanager.listmodel.StoryListModel;

public class TasksManager implements StoryListener {
	
	private TasksDatabase db;
	private ArrayList<Story> stories;
	
	private StoryListModel storyListModel;
	
	
	public TasksManager() {
		stories = new ArrayList<Story>();
		db = new TasksDatabase();
		storyListModel = new StoryListModel(stories);
	}
	
	public void openDB(String path) {
		try{
			db.connect(path);
			System.out.println("Successfully connected to DB: " + path);
			loadStories();
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
	
	public StoryListModel getStoryListModel() {
		return this.storyListModel;
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
	
	public void removeStory(Story s) {
		try {
			db.removeStory(s.getId());
			storyListModel.removeStory(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyChanged(Story s) {
//		storyListModel.update(s);		
	}
	
}
