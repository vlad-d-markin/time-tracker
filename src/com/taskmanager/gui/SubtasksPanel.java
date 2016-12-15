package com.taskmanager.gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DateFormatter;
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
import com.taskmanager.tasktree.Subtask;
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
import java.util.Date;
import java.awt.Dimension;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class SubtasksPanel extends JPanel implements ListSelectionListener, ActionListener {
	private TasksManager taskManager;
	private JList list;	
	private JButton buttonRemove;
	private JButton buttonAdd;
	

	private Subtask currentSubtask = null;
	private JPanel panelEditStory;
	private JButton btnSave;
	private JButton btnReset;
	private JTextField txtTitle;
	private JComboBox comboTaskId;
	
	int newTaskId;
	private JScrollPane descriptionScrollPane;
	private JTextArea txtDescription;
	private JTextField txtOwner;
	private JLabel lblFrom;
	
	private JFormattedTextField formattedDateFrom;
	private JFormattedTextField formattedDateDue;
	
//	private DateFo
	
	
	/**
	 * Create the panel.
	 */
	
	
	public SubtasksPanel(TasksManager taskManager) {
		this.taskManager = taskManager;
		
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane);
		
		panelEditStory = new JPanel();
		panelEditStory.setMinimumSize(new Dimension(400, 10));
		panelEditStory.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Edit subtask", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
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
		
		JLabel lblStory = new JLabel("Task:");
		
		comboTaskId = new JComboBox();
		
		descriptionScrollPane = new JScrollPane();
		
		JLabel lblOwner = new JLabel("Owner:");
		
		txtOwner = new JTextField();
		txtOwner.setColumns(10);
		
		lblFrom = new JLabel("From:");
		
		formattedDateFrom = new JFormattedTextField(new Date());
		
		JLabel lblDueTo = new JLabel("Due to:");
		
		formattedDateDue = new JFormattedTextField(new Date());
		
		
		GroupLayout gl_panelEditStory = new GroupLayout(panelEditStory);
		gl_panelEditStory.setHorizontalGroup(
			gl_panelEditStory.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEditStory.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelEditStory.createParallelGroup(Alignment.LEADING)
						.addComponent(descriptionScrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
						.addComponent(lblFrom)
						.addGroup(Alignment.TRAILING, gl_panelEditStory.createSequentialGroup()
							.addGroup(gl_panelEditStory.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelEditStory.createSequentialGroup()
									.addGroup(gl_panelEditStory.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTitle)
										.addComponent(lblStory)
										.addComponent(lblOwner)
										.addComponent(lblDueTo))
									.addGap(16)
									.addGroup(gl_panelEditStory.createParallelGroup(Alignment.LEADING)
										.addComponent(formattedDateDue, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
										.addComponent(txtOwner, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
										.addComponent(txtTitle, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
										.addComponent(comboTaskId, 0, 445, Short.MAX_VALUE)
										.addComponent(formattedDateFrom, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)))
								.addComponent(btnReset))
							.addGap(18)
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblDescription))
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
						.addComponent(comboTaskId, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelEditStory.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtOwner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOwner))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelEditStory.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFrom)
						.addComponent(formattedDateFrom, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelEditStory.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDueTo)
						.addComponent(formattedDateDue, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblDescription)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(descriptionScrollPane, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panelEditStory.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnReset))
					.addGap(11))
		);
		
		txtDescription = new JTextArea();
		descriptionScrollPane.setViewportView(txtDescription);
		panelEditStory.setLayout(gl_panelEditStory);
		
		JPanel panelSubTasks = new JPanel();
		panelSubTasks.setMinimumSize(new Dimension(400, 10));
		panelSubTasks.setMaximumSize(new Dimension(400, 32767));
		panelSubTasks.setBounds(new Rectangle(0, 0, 400, 0));
		panelSubTasks.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "All subtasks", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		splitPane.setLeftComponent(panelSubTasks);
		
		list = new JList();
		
		buttonAdd = new JButton("Add");
		buttonAdd.addActionListener(this);
		
		buttonRemove = new JButton("Remove");
		buttonRemove.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout gl_panelSubTasks = new GroupLayout(panelSubTasks);
		gl_panelSubTasks.setHorizontalGroup(
			gl_panelSubTasks.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelSubTasks.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSubTasks.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
						.addGroup(gl_panelSubTasks.createSequentialGroup()
							.addComponent(buttonAdd, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
							.addGap(57)
							.addComponent(buttonRemove, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
					.addGap(9))
		);
		gl_panelSubTasks.setVerticalGroup(
			gl_panelSubTasks.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelSubTasks.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panelSubTasks.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonAdd)
						.addComponent(buttonRemove))
					.addContainerGap())
		);
		
		comboTaskId.setModel(taskManager.getTaskListModel());
		comboTaskId.addActionListener(this);
		
		list.setModel(taskManager.getSubtaskListModel());
		list.addListSelectionListener(this);
		scrollPane.setViewportView(list);
		panelSubTasks.setLayout(gl_panelSubTasks);
		
		buttonRemove.setEnabled(false);
		setEditorEnabled(false);
	}
	
	
	private void setCurrentSubtask(Subtask st) {
		currentSubtask = st;
		txtTitle.setText(st.getTitle());
		txtDescription.setText(st.getDescription());
		txtOwner.setText(st.getOwner());
		formattedDateFrom.setValue(st.getFrom());
		formattedDateDue.setValue(st.getDue());
		
		System.out.println(st.getDue());
		
		buttonRemove.setEnabled(true);
		comboTaskId.setSelectedItem(taskManager.getTaskById(currentSubtask.getTaskId()));
		setParentId(currentSubtask.getTaskId());
	}
	
	
	private void addNew() {
		taskManager.createSubtask("New Task", "Nobody", "Meaningful task description");
	}
	
	private void removeCurrent() {
		taskManager.removeSubtask(currentSubtask);
		currentSubtask = null;
		buttonRemove.setEnabled(false);
	}
	
	private void resetCurrent() {
		txtTitle.setText(currentSubtask.getTitle());
		txtDescription.setText(currentSubtask.getDescription());
		txtOwner.setText(currentSubtask.getOwner());
		formattedDateFrom.setValue(currentSubtask.getFrom());
		formattedDateDue.setValue(currentSubtask.getDue());
		comboTaskId.setSelectedItem(taskManager.getTaskById(currentSubtask.getTaskId()));
	}

	
	private void saveCurrent() {
		currentSubtask.setTitle(txtTitle.getText());
		currentSubtask.setDescription(txtDescription.getText());
		currentSubtask.setOwner(txtOwner.getText());
		currentSubtask.setTaskId(newTaskId);
		Date newFromDate = (Date) formattedDateFrom.getValue();	
		Date newDueDate = (Date) formattedDateDue.getValue();
		if(newFromDate.after(newDueDate)) {
			resetCurrent();
			return;
		}
		currentSubtask.setFrom(newFromDate);
		currentSubtask.setDue(newDueDate);
		currentSubtask.notifyChanged();
		taskManager.saveSubtask(currentSubtask);
	}
	
	private void cleanEditor() {
		txtTitle.setText("");
		txtDescription.setText("");
		comboTaskId.setSelectedItem(null);
	}

	private void setEditorEnabled(boolean yes) {
		txtDescription.setEnabled(yes);
		txtTitle.setEnabled(yes);
		btnSave.setEnabled(yes);
		btnReset.setEnabled(yes);
		comboTaskId.setEnabled(yes);
		formattedDateDue.setEnabled(yes);
		formattedDateFrom.setEditable(yes);
	}
	
	private void setParentId(int id) {
		newTaskId = id;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(list.getSelectedIndex() >= 0) {
			setEditorEnabled(true);
			setCurrentSubtask(taskManager.getSubtaskListModel().getElementAt(list.getSelectedIndex()));
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
		else if(e.getSource() == comboTaskId) {
			if(comboTaskId.getSelectedItem() != null)
				setParentId(((Task) comboTaskId.getSelectedItem()).getId());
		}
	}
}
