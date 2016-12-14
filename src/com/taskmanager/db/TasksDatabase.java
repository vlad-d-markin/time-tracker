package com.taskmanager.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import com.taskmanager.tasktree.OverviewItem;
import com.taskmanager.tasktree.Story;
import com.taskmanager.tasktree.Subtask;
import com.taskmanager.tasktree.Task;

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
		createStatement.execute("CREATE TABLE IF NOT EXISTS 'subtasks' 		 ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'title' TEXT, 'description' TEXT, 'owner' TEXT, 'from' DATE, 'due' DATE)");
		createStatement.execute("CREATE TABLE IF NOT EXISTS 'stories_tasks'  ('story_id' INTEGER, 'task_id'    INTEGER)");
		createStatement.execute("CREATE TABLE IF NOT EXISTS 'tasks_subtasks' ('task_id'  INTEGER, 'subtask_id' INTEGER)");
		createStatement.execute("CREATE VIEW IF NOT EXISTS overview AS SELECT subtasks.id, subtasks.title, subtasks.owner, subtasks.due, subtasks.`from`, stories.title AS story_title, tasks.title AS task_title, tasks.id AS task_id, stories.id AS story_id " + 
      "FROM subtasks, tasks_subtasks, tasks, stories_tasks, stories " + 
      "WHERE subtasks.id = tasks_subtasks.subtask_id AND tasks_subtasks.task_id = stories_tasks.task_id AND tasks.id = stories_tasks.task_id AND stories_tasks.story_id = stories.id;");
		
		
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
			System.out.println("Story" + s.getTitle());
		}
		
		getStatement.close();
		return stories;
	}
	
	public Story getStory(int id) throws SQLException {
		Statement getStatement = dbConnection.createStatement();
		
		ResultSet result = getStatement.executeQuery("SELECT id, title, description FROM 'stories' WHERE id='" + (new Integer(id)).toString() + "'");
		Story s = new Story(result.getInt(1), result.getString(2), result.getString(3));
		
		getStatement.close();
		return s;
	}
	
	public void createStory(Story s) throws SQLException {
		Statement createStoryStatement = dbConnection.createStatement();
		
		createStoryStatement.execute("INSERT INTO 'stories' ('title', 'description') VALUES ('" + s.getTitle() + "', '" + s.getDescription() + "')");
		
		createStoryStatement.close();
	}
	
	public void editStory(Story s) throws SQLException {
		Statement statement = dbConnection.createStatement();
		
		statement.execute("UPDATE 'stories' SET title='" + s.getTitle() + "', description='" + s.getDescription() + "' WHERE id='" + (new Integer(s.getId())).toString() + "'");
		
		statement.close();
	}
	
	public void removeStory(int id) throws SQLException {
		Statement removeStoryStatement = dbConnection.createStatement();
		
		removeStoryStatement.execute("DELETE FROM 'stories' WHERE id='" + (new Integer(id)).toString() + "'");
		
		removeStoryStatement.close();
	}
	
	public ArrayList<Task> getTasks() throws SQLException {
		Statement getStatement = dbConnection.createStatement();
		ArrayList<Task> tasks = new ArrayList<Task>();
		
		ResultSet result = getStatement.executeQuery("SELECT id, story_id, title, description FROM 'tasks', 'stories_tasks' WHERE task_id=tasks.id");
		while(result.next()) {
			Task t = new Task(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4));
			tasks.add(t);
			System.out.println("Task: " + t.getTitle());
		}
		
		getStatement.close();
		return tasks;
	}
	
	public void createTask(Task t) throws SQLException {
		Statement statement = dbConnection.createStatement();
		
		statement.execute("INSERT INTO tasks (title, description) VALUES ('" + t.getTitle() + "', '" + t.getDescription() + "')");
		statement.execute("INSERT INTO stories_tasks (story_id, task_id) VALUES ( NULL, last_insert_rowid())");
		
		statement.close();
	}
	
	public void removeTask(int id) throws SQLException {
		Statement removeStatement = dbConnection.createStatement();
		
		removeStatement.execute("DELETE FROM 'tasks' WHERE id='" + (new Integer(id)).toString() + "'");
		removeStatement.execute("DELETE FROM 'stories_tasks' WHERE task_id='" + (new Integer(id)).toString() + "'");
		removeStatement.execute("UPDATE tasks_subtasks SET task_id=NULL WHERE task_id='" + (new Integer(id)).toString() + "'");
		
		removeStatement.close();
	}
	
	
	public void editTask(Task t) throws SQLException {
		Statement statement = dbConnection.createStatement();
		
		statement.execute("UPDATE 'tasks' SET title='" + t.getTitle() + "', description='" + t.getDescription() + "' WHERE id='" + (new Integer(t.getId())).toString() + "'");
		statement.execute("UPDATE stories_tasks SET story_id='" + (new Integer(t.getStoryId())).toString() + "' WHERE task_id='" + (new Integer(t.getId())).toString() + "'");
		
		statement.close();
	}
	
	
	public ArrayList<Subtask> getSubtasks() throws SQLException {
		Statement getStatement = dbConnection.createStatement();
		ArrayList<Subtask> subtasks = new ArrayList<Subtask>();
		
		ResultSet result = getStatement.executeQuery("SELECT `id`, `task_id`, `title`, `owner`, `description`, `from`, `due` FROM subtasks, tasks_subtasks WHERE tasks_subtasks.subtask_id = subtasks.id ORDER BY task_id, title");
		while(result.next()) {
			Subtask s = new Subtask(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7));
			subtasks.add(s);
			System.out.println("Subtask: " + s.getTitle() + "    " + s.getDue());
		}
		
		getStatement.close();
		return subtasks;
	}
	
	public void createSubtask(Subtask st) throws SQLException {
		Statement statement = dbConnection.createStatement();
		
		statement.execute("INSERT INTO subtasks (title, owner, description, `from`, due) VALUES ('" + st.getTitle() + "', '" + st.getOwner() + "', '" + st.getDescription() + "', '" + st.getFromText() + "', '" + st.getDueText() + "')");
		statement.execute("INSERT INTO tasks_subtasks (task_id, subtask_id) VALUES ( NULL, last_insert_rowid())");
		
		statement.close();
	}
	
	public void removeSubtask(int id) throws SQLException {
		Statement removeStatement = dbConnection.createStatement();
		
		removeStatement.execute("DELETE FROM 'subtasks' WHERE id='" + (new Integer(id)).toString() + "'");
		removeStatement.execute("DELETE FROM 'tasks_subtasks' WHERE subtask_id='" + (new Integer(id)).toString() + "'");
		
		removeStatement.close();
	}
	
	public void editSubtask(Subtask st) throws SQLException {
		Statement statement = dbConnection.createStatement();
		
		statement.execute("UPDATE 'subtasks' SET title='" + st.getTitle() + "', description='" + st.getDescription() + "', owner='" + st.getOwner() + "', `from`='" + st.getFromText() + "', due='" + st.getDueText() + "' WHERE id='" + (new Integer(st.getId())).toString() + "'");
		statement.execute("UPDATE tasks_subtasks SET task_id='" + (new Integer(st.getTaskId())).toString() + "' WHERE subtask_id='" + (new Integer(st.getId())).toString() + "'");
		
		statement.close();
	}
	
	public ArrayList<OverviewItem> filterSubtasks(Integer storyId, Integer taskId, String owner, java.util.Date from, java.util.Date due) throws SQLException {
		Statement getStatement = dbConnection.createStatement();
		ArrayList<OverviewItem> subtasks = new ArrayList<>();
		
		String fromStr = "1970-01-01";
		String dueStr = "2090-01-01"; 
		
		if(from != null) {
			fromStr = Subtask.DATE_FORMAT.format(from);
		}
		if(due != null) {
			dueStr = Subtask.DATE_FORMAT.format(due);
		}
		
		
		String storyCond = storyId == null ? "1" : "story_id = " + storyId.toString();
		String taskCond  = taskId == null  ? "1" : "task_id = " + taskId.toString();
		String ownerCond = owner == null   ? "1" : "owner = '" + owner + "'";
		String fromCond  = from == null    ? "1" : "`from` >= '" + fromStr + "'";
		String dueCond   = due == null     ? "1" : "due <= '" + dueStr + "'";
		
		String cond = " WHERE " + storyCond + " AND " + taskCond + " AND " + ownerCond + " AND " + fromCond  + " AND " + dueCond;
		
		
		ResultSet res = getStatement.executeQuery("SELECT * FROM overview " + cond);
		while(res.next()) {
			OverviewItem oi = new OverviewItem(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(7), res.getString(6));
			subtasks.add(oi);
		}
		
		return subtasks;
	}
	
	
	public ArrayList<String> getOwners() throws SQLException {
		Statement getStatement = dbConnection.createStatement();
		ArrayList<String> owners = new ArrayList<>();		
		
		ResultSet res = getStatement.executeQuery("SELECT DISTINCT owner FROM subtasks");
		while(res.next()) {
			owners.add(res.getString(1));
		}
		
		return owners;
	}
}
