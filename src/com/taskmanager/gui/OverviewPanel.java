package com.taskmanager.gui;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	
	private OverviewTableModel model;

	public OverviewPanel(TasksManager tasksManager) {
		this.tasksManager = tasksManager;
		model = new OverviewTableModel(tasksManager.getSubtasks());
		
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
		
		JComboBox comboTask = new JComboBox();
		comboTask.setEditable(true);
		
		JLabel lblSubtask = new JLabel("Subtask:");
		
		JComboBox comboSubtask = new JComboBox();
		comboSubtask.setEditable(true);
		
		JLabel lblOwner = new JLabel("Owner:");
		
		JComboBox comboBox_3 = new JComboBox();
		
		JLabel lblFrom = new JLabel("From:");
		
		JLabel lblDue = new JLabel("Due:");
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadTable();				
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnApply)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStory)
								.addComponent(lblTask)
								.addComponent(lblSubtask))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(comboStory, 0, 388, Short.MAX_VALUE)
								.addComponent(comboTask, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboSubtask, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(36)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblOwner)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboBox_3, 0, 442, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblFrom)
										.addComponent(lblDue))
									.addGap(26)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(formattedTextField, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
										.addComponent(formattedTextField_1, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))))))
					.addGap(7))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStory)
						.addComponent(comboStory, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOwner)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTask)
						.addComponent(comboTask, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFrom)
						.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSubtask)
						.addComponent(comboSubtask, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDue)
						.addComponent(formattedTextField_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnApply)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		setLayout(groupLayout);
		
		comboStory.setModel(tasksManager.getStoryListModel());
		
		table.setModel(model);
	}
	
	
	private void loadTable() {
//		ArrayList<Subtask> subtasks = tasksManager.getSubtasks();
		
		
	}
}
