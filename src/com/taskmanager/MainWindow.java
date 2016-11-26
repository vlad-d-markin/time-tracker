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
import com.taskmanager.gui.StoriesPanel;
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
import javax.swing.JTabbedPane;

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
		
		frmTaskManager.setTitle("Task Manager");
		frmTaskManager.setBounds(100, 100, 1012, 550);
		frmTaskManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmTaskManager.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		frmTaskManager.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmTaskManager.getContentPane().add(tabbedPane);
		
		StoriesPanel panelStories = new StoriesPanel();
		tabbedPane.addTab("Stories", panelStories);
		
		JPanel panelTasks = new JPanel();
		tabbedPane.addTab("Tasks", null, panelTasks, null);
		
		JPanel panelSubtasks = new JPanel();
		tabbedPane.addTab("Subtasks", null, panelSubtasks, null);
	}
}
