package com.taskmanager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;

import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.sun.glass.events.WindowEvent;
import com.taskmanager.gui.StoryEditorDialog;
import com.taskmanager.tasktree.TasksManager;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class MainWindow {

	private JFrame frmTaskManager;
	private StoryEditorDialog storyEditorDialog;
	private TasksManager taskManager;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmTaskManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		taskManager = new TasksManager();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTaskManager = new JFrame();
		storyEditorDialog = new StoryEditorDialog();
		storyEditorDialog.setVisible(false);
		
		frmTaskManager.setTitle("Task Manager");
		frmTaskManager.setBounds(100, 100, 1012, 550);
		frmTaskManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmTaskManager.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		frmTaskManager.getContentPane().setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel storiesPanel = new JPanel();
		storiesPanel.setBorder(new TitledBorder(null, "Stories", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmTaskManager.getContentPane().add(storiesPanel);
		
		JList list = new JList(taskManager.getStoryListModel());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnAddStory = new JButton("Add");
		btnAddStory.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				storyEditorDialog.open(taskManager.createStory("New Story", "New story description"));
			}
		});
		
		JButton btnRemoveStory = new JButton("Remove");
		GroupLayout gl_storiesPanel = new GroupLayout(storiesPanel);
		gl_storiesPanel.setHorizontalGroup(
			gl_storiesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_storiesPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_storiesPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_storiesPanel.createSequentialGroup()
							.addComponent(btnAddStory, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
							.addGap(19)
							.addComponent(btnRemoveStory, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_storiesPanel.setVerticalGroup(
			gl_storiesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_storiesPanel.createSequentialGroup()
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
					.addGap(17)
					.addGroup(gl_storiesPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddStory)
						.addComponent(btnRemoveStory))
					.addGap(7))
		);
		storiesPanel.setLayout(gl_storiesPanel);
		
		JPanel tasksPanel = new JPanel();
		tasksPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Tasks", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		frmTaskManager.getContentPane().add(tasksPanel);
		
		JButton btnAddTask = new JButton("Add");
		
		JButton btnRemoveTask = new JButton("Remove");
		
		JList list_1 = new JList();
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GroupLayout gl_tasksPanel = new GroupLayout(tasksPanel);
		gl_tasksPanel.setHorizontalGroup(
			gl_tasksPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 253, Short.MAX_VALUE)
				.addGroup(gl_tasksPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_tasksPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tasksPanel.createSequentialGroup()
							.addComponent(btnAddTask, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
							.addGap(19)
							.addComponent(btnRemoveTask, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
						.addComponent(list_1, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_tasksPanel.setVerticalGroup(
			gl_tasksPanel.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 505, Short.MAX_VALUE)
				.addGroup(gl_tasksPanel.createSequentialGroup()
					.addComponent(list_1, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
					.addGap(17)
					.addGroup(gl_tasksPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddTask)
						.addComponent(btnRemoveTask))
					.addGap(7))
		);
		tasksPanel.setLayout(gl_tasksPanel);
		
		JPanel subtasksPanel = new JPanel();
		subtasksPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Subtasks", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		frmTaskManager.getContentPane().add(subtasksPanel);
		
		JButton btnAddSubtask = new JButton("Add");
		
		JButton btnRemoveSubtask = new JButton("Remove");
		
		JList list_2 = new JList();
		list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GroupLayout gl_subtasksPanel = new GroupLayout(subtasksPanel);
		gl_subtasksPanel.setHorizontalGroup(
			gl_subtasksPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 253, Short.MAX_VALUE)
				.addGroup(gl_subtasksPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_subtasksPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_subtasksPanel.createSequentialGroup()
							.addComponent(btnAddSubtask, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
							.addGap(19)
							.addComponent(btnRemoveSubtask, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
						.addComponent(list_2, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_subtasksPanel.setVerticalGroup(
			gl_subtasksPanel.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 505, Short.MAX_VALUE)
				.addGroup(gl_subtasksPanel.createSequentialGroup()
					.addComponent(list_2, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
					.addGap(17)
					.addGroup(gl_subtasksPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddSubtask)
						.addComponent(btnRemoveSubtask))
					.addGap(7))
		);
		subtasksPanel.setLayout(gl_subtasksPanel);
	}
}
