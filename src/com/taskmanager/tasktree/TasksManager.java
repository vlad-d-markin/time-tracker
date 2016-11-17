package com.taskmanager.tasktree;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;

import com.taskmanager.db.TasksDatabase;

public class TasksManager implements StoryListener {
	
	private TasksDatabase db;
	private ArrayList<Story> stories;
	
	public class StoryListModel extends AbstractListModel<Story> {
		
		public void addStory(Story s) {
			stories.add(s);
			fireIntervalAdded(this, stories.size()-1, stories.size()-1);
		}
		
		public void update(Story s) {
			int idx = stories.indexOf(s);
			fireContentsChanged(this, idx, idx);
			
		}

		@Override
		public Story getElementAt(int index) {
			return stories.get(index);
		}

		@Override
		public int getSize() {
			return stories.size();
		}
		
	}
	
	private StoryListModel storyListModel;
	
	
	public TasksManager() {
		stories = new ArrayList<Story>();
		db = new TasksDatabase();
		storyListModel = new StoryListModel();
	}
	
	public void openDB(String path) {
		try{
			db.connect(path);
			System.out.println("Successfully connected to DB: " + path);
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
	
	public Story createStory(String title, String description) {
		Story s = new Story(title, description);
		s.adddListener(this);
		storyListModel.addStory(s);
		return s;
	}

	@Override
	public void notifyChanged(Story s) {
		storyListModel.update(s);		
	}
	
}
