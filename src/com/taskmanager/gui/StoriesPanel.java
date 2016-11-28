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
	private TasksManager taskManager;
	
	private JTextField textField;	
	private JList list;	
	private JButton buttonRemove;
	private JButton buttonAdd;
	

	private Story currentStory = null;
	private JPanel panelEditStory;
	private JButton btnSave;
	private JButton btnReset;
	private JTextField txtStoryTitle;
	private JScrollPane scrollPane_1;
	private JTextArea txtStoryDescription;
	
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
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(this);
		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		
		scrollPane_1 = new JScrollPane();
		
		
		GroupLayout gl_panelEditStory = new GroupLayout(panelEditStory);
		gl_panelEditStory.setHorizontalGroup(
			gl_panelEditStory.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEditStory.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelEditStory.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panelEditStory.createSequentialGroup()
							.addGroup(gl_panelEditStory.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
								.addGroup(gl_panelEditStory.createSequentialGroup()
									.addComponent(btnReset)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelEditStory.createSequentialGroup()
									.addComponent(lblTitle)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtStoryTitle, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)))
							.addGap(8))
						.addGroup(gl_panelEditStory.createSequentialGroup()
							.addComponent(lblDescription)
							.addContainerGap(383, Short.MAX_VALUE))))
		);
		gl_panelEditStory.setVerticalGroup(
			gl_panelEditStory.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEditStory.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelEditStory.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle)
						.addComponent(txtStoryTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDescription)
					.addGap(12)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panelEditStory.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnReset))
					.addContainerGap())
		);
		
		txtStoryDescription = new JTextArea();
		txtStoryDescription.setText("Description");
		scrollPane_1.setViewportView(txtStoryDescription);
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
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
					.addGap(18)
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
	
	
	private void addNew() {
		taskManager.createStory("New Story", "Meaningful description");
	}
	
	private void removeCurrent() {
		taskManager.removeStory(currentStory);
		currentStory = null;
		buttonRemove.setEnabled(false);
		
	}
	
	private void resetCurrent() {
		txtStoryTitle.setText(currentStory.getTitle());
		txtStoryDescription.setText(currentStory.getDescription());
	}

	
	private void saveCurrent() {
		currentStory.setTitle(txtStoryTitle.getText());
		currentStory.setDescription(txtStoryDescription.getText());
		currentStory.notifyChanged();
		taskManager.saveStory(currentStory);
//		list.requestFocus();
	}
	
	private void cleanEditor() {
		txtStoryTitle.setText("");
		txtStoryDescription.setText("");
	}

	private void setEditorEnabled(boolean yes) {
		txtStoryDescription.setEnabled(yes);
		txtStoryTitle.setEnabled(yes);
		btnSave.setEnabled(yes);
		btnReset.setEnabled(yes);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(list.getSelectedIndex() >= 0) {
			setEditorEnabled(true);
			setCurrentStory(taskManager.getStoryListModel().getElementAt(list.getSelectedIndex()));
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
	}

}
