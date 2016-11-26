package com.taskmanager.gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
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
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JSplitPane;

public class StoriesPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	
	
	public StoriesPanel() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane);
		
		JPanel panelEditStory = new JPanel();
		panelEditStory.setMinimumSize(new Dimension(400, 10));
		panelEditStory.setBorder(new TitledBorder(null, "Edit story", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(panelEditStory);
		
		JPanel panelStories = new JPanel();
		panelStories.setMinimumSize(new Dimension(400, 10));
		panelStories.setMaximumSize(new Dimension(400, 32767));
		panelStories.setBounds(new Rectangle(0, 0, 400, 0));
		panelStories.setBorder(new TitledBorder(null, "All stories", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(panelStories);
		
		textField = new JTextField();
		textField.setText("Search");
		textField.setColumns(10);
		
		JButton button = new JButton("Add");
		
		JButton button_1 = new JButton("Remove");
		
		JList list = new JList();
		GroupLayout gl_panelStories = new GroupLayout(panelStories);
		gl_panelStories.setHorizontalGroup(
			gl_panelStories.createParallelGroup(Alignment.LEADING)
				.addGap(0, 361, Short.MAX_VALUE)
				.addGap(0, 243, Short.MAX_VALUE)
				.addGap(0, 351, Short.MAX_VALUE)
				.addGroup(gl_panelStories.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelStories.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
						.addGroup(gl_panelStories.createSequentialGroup()
							.addComponent(button, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
							.addGap(57)
							.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
						.addComponent(list, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelStories.setVerticalGroup(
			gl_panelStories.createParallelGroup(Alignment.LEADING)
				.addGap(0, 471, Short.MAX_VALUE)
				.addGap(0, 449, Short.MAX_VALUE)
				.addGap(0, 449, Short.MAX_VALUE)
				.addGroup(gl_panelStories.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(gl_panelStories.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap())
		);
		panelStories.setLayout(gl_panelStories);
	}
}