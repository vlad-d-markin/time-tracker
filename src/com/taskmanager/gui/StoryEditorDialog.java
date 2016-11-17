package com.taskmanager.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.taskmanager.tasktree.Story;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class StoryEditorDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStorytitle;
	private JTextArea txtrStorydescription;
	private Story story;


	/**
	 * Create the dialog.
	 */
	public StoryEditorDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][grow]"));
		{
			JLabel lblTitle = new JLabel("Title:");
			contentPanel.add(lblTitle, "cell 0 0,alignx trailing");
		}
		{
			txtStorytitle = new JTextField();
			txtStorytitle.setText("storyTitle");
			contentPanel.add(txtStorytitle, "cell 1 0,growx");
			txtStorytitle.setColumns(10);
		}
		{
			JLabel lblDescription = new JLabel("Description:");
			contentPanel.add(lblDescription, "cell 0 1,aligny top");
		}
		{
			txtrStorydescription = new JTextArea();
			txtrStorydescription.setText("storyDescription");
			contentPanel.add(txtrStorydescription, "cell 1 1,grow");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
				okButton.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);			
						story.setTitle(txtStorytitle.getText());
						story.setDescription(txtrStorydescription.getText());
						
						txtStorytitle.setText("OK");
						txtrStorydescription.setText("");
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						
						txtStorytitle.setText("CANCEL");
						txtrStorydescription.setText("");
					}
				});
			}
		}
	}
	
	
	public void open(Story story) {
		System.out.println("Open story");
		this.story = story;
		setVisible(true);
		setModal(true);
		txtStorytitle.setText(this.story.getTitle());
		txtrStorydescription.setText(this.story.getDescription());
	}


}
