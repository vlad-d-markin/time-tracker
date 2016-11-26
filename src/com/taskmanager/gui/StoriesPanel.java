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

public class StoriesPanel extends JPanel implements ListSelectionListener, ActionListener {
	private JTextField textField;
	private JTextField txtStoryTitle;
	private TasksManager taskManager;
	private JList list;
	private JTextArea txtStoryDescription;
	private Story currentStory = null;
	private JButton buttonRemove;
	private JPanel panelEditStory;

	/**
	 * Create the panel.
	 */
	
	
	public StoriesPanel(TasksManager taskManager) {
		this.taskManager = taskManager;
		
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane);
		
		panelEditStory = new JPanel();
		panelEditStory.setMinimumSize(new Dimension(400, 10));
		panelEditStory.setBorder(new TitledBorder(null, "Edit story", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(panelEditStory);
		
		JLabel lblTitle = new JLabel("Title:");
		
		txtStoryTitle = new JTextField();
		txtStoryTitle.setText("Title here");
		txtStoryTitle.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		
		txtStoryDescription = new JTextArea();
		
		JButton btnSave = new JButton("Save");
		
		JButton btnReset = new JButton("Reset");
		GroupLayout gl_panelEditStory = new GroupLayout(panelEditStory);
		gl_panelEditStory.setHorizontalGroup(
			gl_panelEditStory.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEditStory.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelEditStory.createParallelGroup(Alignment.LEADING)
						.addComponent(txtStoryDescription, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panelEditStory.createSequentialGroup()
							.addComponent(btnReset)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelEditStory.createSequentialGroup()
							.addComponent(lblTitle)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtStoryTitle, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
						.addComponent(lblDescription))
					.addGap(8))
		);
		gl_panelEditStory.setVerticalGroup(
			gl_panelEditStory.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEditStory.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelEditStory.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle)
						.addComponent(txtStoryTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtStoryDescription, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelEditStory.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnReset))
					.addContainerGap())
		);
		panelEditStory.setLayout(gl_panelEditStory);
		
		JPanel panelStories = new JPanel();
		panelStories.setMinimumSize(new Dimension(400, 10));
		panelStories.setMaximumSize(new Dimension(400, 32767));
		panelStories.setBounds(new Rectangle(0, 0, 400, 0));
		panelStories.setBorder(new TitledBorder(null, "All stories", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(panelStories);
		
		textField = new JTextField();
		textField.setText("Search");
		textField.setColumns(10);
		
		list = new JList();
		
		JButton buttonAdd = new JButton("Add");
		buttonAdd.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				taskManager.createStory("New story", "New description");	
			}
		});
		
		buttonRemove = new JButton("Remove");
		buttonRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeCurrent();				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout gl_panelStories = new GroupLayout(panelStories);
		gl_panelStories.setHorizontalGroup(
			gl_panelStories.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelStories.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelStories.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
						.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panelStories.createSequentialGroup()
							.addComponent(buttonAdd, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addGap(57)
							.addComponent(buttonRemove, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
					.addGap(9))
		);
		gl_panelStories.setVerticalGroup(
			gl_panelStories.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelStories.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addGroup(gl_panelStories.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonAdd)
						.addComponent(buttonRemove))
					.addContainerGap())
		);
		
		
		list.setModel(taskManager.getStoryListModel());
		list.addListSelectionListener(this);
		scrollPane.setViewportView(list);
		panelStories.setLayout(gl_panelStories);
	}
	
	
	private void setCurrentStory(Story s) {
		currentStory = s;
		txtStoryTitle.setText(s.getTitle());
		txtStoryDescription.setText(s.getDescription());
		buttonRemove.setEnabled(true);
	}
	
	
	private void removeCurrent() {
		taskManager.removeStory(currentStory);
		currentStory = null;
		buttonRemove.setEnabled(false);
		
	}
	
	private void resetCurrent() {
		
	}

	
	private void saveCurrent() {
		
	}
	
	private void cleanEditor() {
		txtStoryTitle.setText("");
		txtStoryDescription.setText("");
	}

	private void setEditorEnabled(boolean yes) {
		txtStoryDescription.setEnabled(yes);
		txtStoryTitle.setEnabled(yes);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
//		Story selectedStory = (Story) e.getSource();	
		if(list.getSelectedIndex() >= 0) {
			setEditorEnabled(true);
			setCurrentStory(taskManager.getStoryListModel().getElementAt(list.getSelectedIndex()));
		}
		else {
			setEditorEnabled(false);
			cleanEditor();
			panelEditStory.setEnabled(false);			
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
