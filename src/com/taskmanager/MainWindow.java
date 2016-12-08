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
import com.taskmanager.gui.OverviewPanel;
import com.taskmanager.gui.StoriesPanel;
import com.taskmanager.gui.StoryEditorDialog;
import com.taskmanager.gui.SubtasksPanel;
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
import java.awt.Component;
import java.awt.Container;

import javax.swing.JTabbedPane;
import javax.swing.JMenuItem;

public class MainWindow implements ActionListener {

	private JFrame frmTaskManager;
	private TasksManager taskManager;	
	private JFileChooser fileChooser;
	private JTabbedPane tabbedPane;
	
	private JMenuItem mItemOpen;
	private JMenuItem mntmCreate;
	private JMenuItem mntmCloseDb;
	private JMenuItem mntmQuit;
	private JPanel panelOverview;
	
	
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
		fileChooser = new JFileChooser();
		frmTaskManager = new JFrame();
		frmTaskManager.setMinimumSize(new Dimension(1060, 540));
		
		frmTaskManager.setTitle("Task Manager");
		frmTaskManager.setBounds(100, 100, 1012, 550);
		frmTaskManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar mainMenu = new JMenuBar();
		frmTaskManager.setJMenuBar(mainMenu);
		
		JMenu menuTabFile = new JMenu("File");		
		mainMenu.add(menuTabFile);
		
		mItemOpen = new JMenuItem("Open");
		mItemOpen.addActionListener(this);
		
		mntmCreate = new JMenuItem("Create");
		menuTabFile.add(mntmCreate);
		mntmCreate.addActionListener(this);
		
		menuTabFile.add(mItemOpen);
		
		mntmCloseDb = new JMenuItem("Close DB");
		menuTabFile.add(mntmCloseDb);
		mntmCloseDb.addActionListener(this);
		
		mntmQuit = new JMenuItem("Quit");
		menuTabFile.add(mntmQuit);
		mntmQuit.addActionListener(this);
		
		frmTaskManager.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmTaskManager.getContentPane().add(tabbedPane);
		
		// Panels
		
		StoriesPanel panelStories = new StoriesPanel(taskManager);
		tabbedPane.addTab("Stories", panelStories);
		
		TasksPanel panelTasks = new TasksPanel(taskManager);
		tabbedPane.addTab("Tasks", null, panelTasks, null);
		
		SubtasksPanel panelSubtasks = new SubtasksPanel(taskManager);
		tabbedPane.addTab("Subtasks", null, panelSubtasks, null);
		
		panelOverview = new OverviewPanel(taskManager);
		tabbedPane.addTab("Overview", null, panelOverview, null);
		
		enableMainPanel(false);
	}
	
	
	public void enableMainPanel(boolean yes) {
		enableContainer(tabbedPane, yes);
	}
	
	private void enableContainer(Container container, boolean enable) {
		Component[] components = container.getComponents();
		container.setEnabled(enable);
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
            	enableContainer((Container)component, enable);
            }
        }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		
		if(e.getSource() == mntmCreate) {
			int fileChooserRetVal = fileChooser.showSaveDialog(frmTaskManager);
			
			if(fileChooserRetVal == JFileChooser.APPROVE_OPTION) {
				File dbFile = fileChooser.getSelectedFile();
				taskManager.closeDB();
				taskManager.openDB(dbFile.getAbsolutePath());
				enableMainPanel(true);
			}
		}		
		else if(e.getSource() == mItemOpen) {
			int fileChooserRetVal = fileChooser.showOpenDialog(frmTaskManager);
			
			if(fileChooserRetVal == JFileChooser.APPROVE_OPTION) {
				File dbFile = fileChooser.getSelectedFile();
				taskManager.closeDB();
				taskManager.openDB(dbFile.getAbsolutePath());
				enableMainPanel(true);
			}
		}
		else if(e.getSource() == mntmCloseDb) {
			taskManager.closeDB();
			enableMainPanel(false);
		}
		else if(e.getSource() == mntmQuit) {
			taskManager.closeDB();
			System.exit(0);
		}
	}
}
