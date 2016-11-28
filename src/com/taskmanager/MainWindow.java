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
import java.io.File;

import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.sun.glass.events.WindowEvent;
import com.taskmanager.gui.StoriesPanel;
import com.taskmanager.gui.StoryEditorDialog;
import com.taskmanager.gui.TasksPanel;
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
import javax.swing.JFileChooser;

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
import javax.swing.JMenuItem;

public class MainWindow implements ActionListener {

	private JFrame frmTaskManager;
	private TasksManager taskManager;
	private JMenuItem mItemOpen;
	private JFileChooser fileChooser;
	
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
		
//		taskManager.op
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		fileChooser = new JFileChooser();
		frmTaskManager = new JFrame();
		
		frmTaskManager.setTitle("Task Manager");
		frmTaskManager.setBounds(100, 100, 1012, 550);
		frmTaskManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar mainMenu = new JMenuBar();
		frmTaskManager.setJMenuBar(mainMenu);
		
		JMenu menuTabFile = new JMenu("File");		
		mainMenu.add(menuTabFile);
		
		mItemOpen = new JMenuItem("Open");
		mItemOpen.addActionListener(this);
		
		menuTabFile.add(mItemOpen);
		frmTaskManager.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmTaskManager.getContentPane().add(tabbedPane);
		
		// Panels
		
		StoriesPanel panelStories = new StoriesPanel(taskManager);
		tabbedPane.addTab("Stories", panelStories);
		
		TasksPanel panelTasks = new TasksPanel(taskManager);
		tabbedPane.addTab("Tasks", null, panelTasks, null);
		
		JPanel panelSubtasks = new JPanel();
		tabbedPane.addTab("Subtasks", null, panelSubtasks, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		if(e.getSource() == mItemOpen) {
			int fileChooserRetVal = fileChooser.showOpenDialog(frmTaskManager);
			
			if(fileChooserRetVal == JFileChooser.APPROVE_OPTION) {
				File dbFile = fileChooser.getSelectedFile();
				taskManager.closeDB();
				taskManager.openDB(dbFile.getAbsolutePath());
			}
			else {
				
			}
		}
	}
}
