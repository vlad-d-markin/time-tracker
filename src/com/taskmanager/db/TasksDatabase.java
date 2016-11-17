package com.taskmanager.db;

import java.sql.*;

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
	
	
	public void createStory(String title, String description) throws SQLException {
		Statement createStoryStatement = dbConnection.createStatement();
		
		createStoryStatement.execute("INSERT INTO 'stories' ('title', 'description') VALUES ('" + title + "', '" + description + "')");
		
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
