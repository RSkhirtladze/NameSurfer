
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	private JTextField nameField = new JTextField(10);
	private JButton clearBut = new JButton("Clear");
	private JButton graphBut = new JButton("Graph");
	private NameSurferDataBase DataBase = new NameSurferDataBase(NAMES_DATA_FILE);
	private NameSurferGraph diagram = new NameSurferGraph();

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */
	public void init() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		addPanel();
		addListeners();
		add(diagram);
	}

	private void addListeners() {
		nameField.addActionListener(this);
		addActionListeners();
	}

	private void addPanel() {
		JLabel label = new JLabel("Name");
		add(label, SOUTH);
		add(nameField, SOUTH);
		add(graphBut, SOUTH);
		add(clearBut, SOUTH);
	}

	private void checkName() {
		NameSurferEntry name = DataBase.findEntry(nameField.getText().toLowerCase());
		if (name != null) {
			diagram.addEntry(name);
		}
		nameField.setText("");
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked, so you
	 * will have to define a method to respond to button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == graphBut || e.getSource() == nameField) {
			checkName();
		} else if (e.getSource() == clearBut) {
			diagram.clear();
		}
	}

}
