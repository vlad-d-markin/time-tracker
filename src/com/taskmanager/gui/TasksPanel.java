package com.taskmanager.gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.taskmanager.tasktree.Story;
import com.taskmanager.tasktree.Task;
import com.taskmanager.tasktree.TasksManager;

import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;

public class TasksPanel extends JPanel implements ListSelectionListener, ActionListener {
	private TasksManager taskManager;
	
	private JTextField textField;	
	private JList list;	
	private JButton buttonRemove;
	private JButton buttonAdd;
	

	private Task currentTask = null;
	private JPanel panelEditStory;
	private JButton btnSave;
	private JButton btnReset;
	private JTextField txtTitle;
	private JComboBox comboStoryId;
	
	int newStoryId;
	private JScrollPane descriptionScrollPane;
	private JTextArea txtDescription;
	
	/**
	 * Create the panel.
	 */
	
	
	public TasksPanel(TasksManager taskManager) {
		this.taskManager = taskManager;
		
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane);
		
		panelEditStory = new JPanel();
		panelEditStory.setMinimumSize(new Dimension(400, 10));
		panelEditStory.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Edit task", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		splitPane.setRightComponent(panelEditStory);
		
		JLabel lblTitle = new JLabel("Title:");
		
		txtTitle = new JTextField();
		txtTitle.setText("Title here");
		txtTitle.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(this);
		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		
		JLabel lblStory = new JLabel("Story:");
		
		comboStoryId = new JComboBox();
		
		descriptionScrollPane = new JScrollPane();
		
		
		GroupLayout gl_panelEditStory = new GroupLayout(panelEditStory);
		gl_panelEditStory.setHorizontalGroup(
			gl_panelEditStory.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEditStory.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelEditStory.createParallelGroup(Alignment.LEADING)
						.addComponent(descriptionScrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
						.addGroup(gl_panelEditStory.createSequentialGroup()
							.addGroup(gl_panelEditStory.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_panelEditStory.createSequentialGroup()
									.addGroup(gl_panelEditStory.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTitle)
										.addComponent(lblStory))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panelEditStory.createParallelGroup(Alignment.LEADING)
										.addComponent(comboStoryId, 0, 448, Short.MAX_VALUE)
										.addComponent(txtTitle, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)))
								.addComponent(lblDescription, Alignment.LEADING)
								.addComponent(btnReset))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panelEditStory.setVerticalGroup(
			gl_panelEditStory.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEditStory.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelEditStory.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle)
						.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelEditStory.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStory)
						.addComponent(comboStoryId, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(descriptionScrollPane, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panelEditStory.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnReset))
					.addGap(11))
		);
		
		txtDescription = new JTextArea();
		descriptionScrollPane.setViewportView(txtDescription);
		panelEditStory.setLayout(gl_panelEditStory);
		
		JPanel panelStories = new JPanel();
		panelStories.setMinimumSize(new Dimension(400, 10));
		panelStories.setMaximumSize(new Dimension(400, 32767));
		panelStories.setBounds(new Rectangle(0, 0, 400, 0));
		panelStories.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "All tasks", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		splitPane.setLeftComponent(panelStories);
		
		textField = new JTextField();
		textField.setText("Search");
		textField.setColumns(10);
		
		list = new JList();
		
		buttonAdd = new JButton("Add");
		buttonAdd.addActionListener(this);
		
		buttonRemove = new JButton("Remove");
		buttonRemove.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout gl_panelStories = new GroupLayout(panelStories);
		gl_panelStories.setHorizontalGroup(
			gl_panelStories.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelStories.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelStories.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
						.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panelStories.createSequentialGroup()
							.addComponent(buttonAdd, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
							.addGap(57)
							.addComponent(buttonRemove, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
					.addGap(9))
		);
		gl_panelStories.setVerticalGroup(
			gl_panelStories.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelStories.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panelStories.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonAdd)
						.addComponent(buttonRemove))
					.addContainerGap())
		);
		
		comboStoryId.setModel(taskManager.getStoryListModel());
		comboStoryId.addActionListener(this);
		
		list.setModel(taskManager.getTaskListModel());
		list.addListSelectionListener(this);
		scrollPane.setViewportView(list);
		panelStories.setLayout(gl_panelStories);
	}
	
	
	private void setCurrentTask(Task t) {
		currentTask = t;
		txtTitle.setText(t.getTitle());
		txtDescription.setText(t.getDescription());
		buttonRemove.setEnabled(true);
		comboStoryId.setSelectedItem(taskManager.getStoryById(currentTask.getStory_id()));
	}
	
	
	private void addNew() {
		taskManager.createTask("New Task", "Meaningful task description");
	}
	
	private void removeCurrent() {
		taskManager.removeTask(currentTask);
		currentTask = null;
		buttonRemove.setEnabled(false);
		
	}
	
	private void resetCurrent() {
		txtTitle.setText(currentTask.getTitle());
		txtDescription.setText(currentTask.getDescription());
		comboStoryId.setSelectedItem(taskManager.getStoryById(currentTask.getStory_id()));
	}

	
	private void saveCurrent() {
		currentTask.setTitle(txtTitle.getText());
		currentTask.setDescription(txtDescription.getText());
		currentTask.setStory_id(newStoryId);
		currentTask.notifyChanged();
		taskManager.saveTask(currentTask);
	}
	
	private void cleanEditor() {
		txtTitle.setText("");
		txtDescription.setText("");
		comboStoryId.setSelectedItem(null);
	}

	private void setEditorEnabled(boolean yes) {
		txtDescription.setEnabled(yes);
		txtTitle.setEnabled(yes);
		btnSave.setEnabled(yes);
		btnReset.setEnabled(yes);
		comboStoryId.setEnabled(yes);
	}
	
	private void setParentId(int id) {
		newStoryId = id;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(list.getSelectedIndex() >= 0) {
			setEditorEnabled(true);
			setCurrentTask(taskManager.getTaskListModel().getElementAt(list.getSelectedIndex()));
		}
		else {
			setEditorEnabled(false);
			cleanEditor();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSave) {
			saveCurrent();
		}
		else if(e.getSource() == btnReset) {
			resetCurrent();
		}
		else if(e.getSource() == buttonAdd) {
			addNew();
		}
		else if(e.getSource() == buttonRemove) {
			removeCurrent();
		}
		else if(e.getSource() == comboStoryId) {
			if(comboStoryId.getSelectedItem() != null)
				setParentId(((Story) comboStoryId.getSelectedItem()).getId());
		}
	}
}
