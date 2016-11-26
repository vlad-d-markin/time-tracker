package com.taskmanager.db;

import java.sql.*;
import java.util.ArrayList;

import com.taskmanager.tasktree.Story;

public class TasksDatabase {
	private Connection dbConnection;
	private boolean opened;
	
	public TasksDatabase() {
		this.opened = false;
	}
	
	public void connect(String path) throws SQLException, ClassNotFoundException {
		this.opened = true;
		
		Class.forName("org.sqlite.JDBC");
		dbConnection = DriverManager.getConnection("jdbc:sqlite:" + path);
		
		Statement createStatement = dbConnection.createStatement();
		
		createStatement.execute("CREATE TABLE IF NOT EXISTS 'stories' 		 ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'title' TEXT, 'description' TEXT)");
		createStatement.execute("CREATE TABLE IF NOT EXISTS 'tasks'     	 ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'title' TEXT, 'description' TEXT)");
		createStatement.execute("CREATE TABLE IF NOT EXISTS 'subtasks' 		 ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'title' TEXT, 'description' TEXT, 'owner' TEXT)");
		createStatement.execute("CREATE TABLE IF NOT EXISTS 'stories_tasks'  ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'story_id' INTEGER, 'task_id'    INTEGER)");
		createStatement.execute("CREATE TABLE IF NOT EXISTS 'tasks_subtasks' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'task_id'  INTEGER, 'subtask_id' INTEGER)");
		
		createStatement.close();
	}
	
	public void close() throws SQLException {
		if(this.opened)
			dbConnection.close();
		this.opened = false; 
	}
	
	public ArrayList<Story> getStories() throws SQLException {
		Statement getStatement = dbConnection.createStatement();
		ArrayList<Story> stories = new ArrayList<Story>();
		
		ResultSet result = getStatement.executeQuery("SELECT id, title, description FROM 'stories'");
		while(result.next()) {
			Story s = new Story(result.getInt(1), result.getString(2), result.getString(3));
			stories.add(s);
			System.out.println(s.getTitle());
		}
		
		getStatement.close();
		return stories;
	}
	
	public void createStory(Story s) throws SQLException {
		Statement createStoryStatement = dbConnection.createStatement();
		
		createStoryStatement.execute("INSERT INTO 'stories' ('title', 'description') VALUES ('" + s.getTitle() + "', '" + s.getDescription() + "')");
		
		createStoryStatement.close();
	}
	
	public void editStory(int id, String title, String description) throws SQLException {
		Statement statement = dbConnection.createStatement();
		
		statement.execute("UPDATE 'stories' SET title='" + title + ", description='" + description + "' WHERE id='" + (new Integer(id)).toString() + "'");
		
		statement.close();
	}
	
	public void removeStory(int id) throws SQLException {
		Statement removeStoryStatement = dbConnection.createStatement();
		
		removeStoryStatement.execute("DELETE FROM 'stories' WHERE id='" + (new Integer(id)).toString() + "'");
		
		removeStoryStatement.close();
	}
	
}
