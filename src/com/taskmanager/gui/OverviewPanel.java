package com.taskmanager.gui;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.taskmanager.listmodel.OverviewTableModel;
import com.taskmanager.tasktree.Story;
import com.taskmanager.tasktree.Subtask;
import com.taskmanager.tasktree.Task;
import com.taskmanager.tasktree.TasksManager;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;

public class OverviewPanel extends JPanel {
		
	private TasksManager tasksManager;
	
	private JTable table;
	
	private JComboBox comboStory;
	private JComboBox comboTask;
	private JComboBox comboOwner;
	
	private JFormattedTextField formattedFrom;
	private JFormattedTextField formattedDue;
	
	private Date defaultFrom;
	private Date defaultDue;
	
	private OverviewTableModel model;

	public OverviewPanel(TasksManager tasksManager) {
		this.tasksManager = tasksManager;
		model = new OverviewTableModel();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 1035, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1035, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
					.addGap(0))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblStory = new JLabel("Story:");
		
		comboStory = new JComboBox();
		
		JLabel lblTask = new JLabel("Task:");
		
		comboTask = new JComboBox();
		
		JLabel lblOwner = new JLabel("Owner:");
		
		comboOwner = new JComboBox();
		
		JLabel lblFrom = new JLabel("From:");
		
		JLabel lblDue = new JLabel("Due:");
		
		
		defaultFrom = new Date();
		defaultDue = new Date();
		
		try {
			defaultFrom = Subtask.DATE_FORMAT.parse("1970-01-01");
			defaultDue = Subtask.DATE_FORMAT.parse("2090-01-01");
		}
		catch (ParseException e) { }
		
		formattedFrom = new JFormattedTextField(defaultFrom);		
		formattedDue = new JFormattedTextField(defaultDue);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadTable();				
			}
		});
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
					formattedDue.setValue(defaultDue);
					formattedFrom.setValue(defaultFrom);
					comboOwner.setSelectedItem(null);
					comboStory.setSelectedItem(null);
					comboTask.setSelectedItem(null);
					loadTable();
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnReset)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnApply))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStory)
								.addComponent(lblTask))
							.addGap(31)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(comboStory, 0, 388, Short.MAX_VALUE)
								.addComponent(comboTask, 0, 388, Short.MAX_VALUE))
							.addGap(36)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblOwner)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboOwner, 0, 442, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblFrom)
										.addComponent(lblDue))
									.addGap(26)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(formattedFrom, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
										.addComponent(formattedDue, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))))))
					.addGap(7))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStory)
						.addComponent(comboStory, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOwner)
						.addComponent(comboOwner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTask)
						.addComponent(comboTask, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFrom)
						.addComponent(formattedFrom, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDue)
						.addComponent(formattedDue, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnApply)
						.addComponent(btnReset))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		setLayout(groupLayout);
		
		comboStory.setModel(tasksManager.getStoryListModel());
		comboTask.setModel(tasksManager.getTaskListModel());
		comboOwner.setModel(tasksManager.getOwnerListModel());
		
		
		table.setModel(model);
		
	}
	
	
	private void loadTable() {
//		ArrayList<Subtask> subtasks = tasksManager.getSubtasks();
		
		Story s = (Story) comboStory.getSelectedItem();
		Task t = (Task) comboTask.getSelectedItem();
		String owner = (String) comboOwner.getSelectedItem();
		
		
		model.setList(tasksManager.getFilteredSubtasks(
				s == null ? null : s.getId(), 
				t == null ? null : t.getId(),
				owner,
				(Date) formattedFrom.getValue(),
				(Date) formattedDue.getValue()
				));
		
	}
}
