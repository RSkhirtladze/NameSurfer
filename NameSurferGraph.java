
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

	ArrayList<NameSurferEntry> entries = new ArrayList<NameSurferEntry>();
	private double decWidth = getWidth() / NDECADES;

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		entries.clear();
		update();
	}

	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note that
	 * this method does not actually draw the graph, but simply stores the entry;
	 * the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		if (!entries.contains(entry) && entry != null) {
			entries.add(entry);
			update();
		}
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of entries.
	 * Your application must call update after calling either clear or addEntry;
	 * update is also called whenever the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		drawBack();
		for (int i = 0; i < entries.size(); i++) {
			drawOneGraph(entries.get(i), selectColor(i));
		}
	}
	
	/* Method creates graph of current name.*/
	private void drawOneGraph(NameSurferEntry nameSurferEntry, Color selectColor) {
		for (int i = 0; i < NDECADES - 1; i++) {
			double X1, X2, Y1, Y2;
			X1 = getWidth() / NDECADES * i;
			Y1 = (getHeight() - 2 * GRAPH_MARGIN_SIZE) * nameSurferEntry.getRank(i) / MAX_RANK + GRAPH_MARGIN_SIZE;
			if (nameSurferEntry.getRank(i) == 0)
				Y1 = getHeight() - GRAPH_MARGIN_SIZE;

			X2 = X1 + getWidth() / NDECADES;
			Y2 = (getHeight() - 2 * GRAPH_MARGIN_SIZE) * nameSurferEntry.getRank(i + 1) / MAX_RANK + GRAPH_MARGIN_SIZE;
			if (nameSurferEntry.getRank(i + 1) == 0)
				Y2 = getHeight() - GRAPH_MARGIN_SIZE;

			GLine line = new GLine(X1, Y1, X2, Y2);
			line.setColor(selectColor);
			add(line);

			String lab = nameSurferEntry.getName();
			if (nameSurferEntry.getRank(i) == 0) {
				lab += " *0*";

			} else {
				lab += " " + nameSurferEntry.getRank(i);
			}
			GLabel label = new GLabel(lab);
			label.setColor(selectColor);
			add(label, X1, Y1);

			if (i == NDECADES - 2) {
				lab = nameSurferEntry.getName();
				if (nameSurferEntry.getRank(i + 1) != 0) {
					lab += " " + nameSurferEntry.getRank(i + 1);
				} else {
					lab += " *0*";
				}
				GLabel finlab = new GLabel(lab);
				finlab.setColor(selectColor);
				add(finlab, X2, Y2);
			}
		}

	}

	/*This method randomises colour. */
	private Color selectColor(int z) {
		switch (z % 6) {
		case 1:
			return Color.ORANGE;
		case 2:
			return Color.BLUE;
		case 3:
			return Color.BLACK;
		case 4:
			return Color.GREEN;
		case 5:
			return Color.RED;
		case 0:
			return Color.MAGENTA;
		default:
			return Color.BLACK;
		}
	}
	
	/*This method adds background on canvas. */
	private void drawBack() {

		decWidth = getWidth() / NDECADES;
		GLine ln = new GLine(0, 0, 0, 0);
		GLabel lab = new GLabel("");

		ln = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(ln);
		ln = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(ln);

		for (int j = 0; j < NDECADES; j++) {

			lab = new GLabel(Integer.toString(10 * j + START_DECADE), decWidth * j, getHeight());
			add(lab);

			ln = new GLine(decWidth * j, 0, decWidth * j, getHeight());
			add(ln);
		}
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}
