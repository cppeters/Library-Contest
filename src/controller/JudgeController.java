package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Contest;
import model.ContestDatabaseManager;
import model.Entry;
import model.EntryDatabaseManager;
import model.User;
import view.AdminContestListView;
import view.JudgeContestListView;
import view.JudgeEntryListView;
import view.NewContestForm;
import view.View;
import view.Viewable;

public class JudgeController {
	private final View myView;
	private final User myUser;
	private final ContestDatabaseManager myContestDBManager; 
	private final EntryDatabaseManager myEntryDBManager; 
	
	/**List of all views that have been displayed to this user since this controller
	 * was created.*/
	private final LinkedList<Viewable> viewHistory;

	
	public JudgeController(User theUser, ContestDatabaseManager theContestDatabaseManager, 
							EntryDatabaseManager theEntryDatabaseManager, View theView) {
		myView = theView;
		myUser = theUser;
		myContestDBManager = theContestDatabaseManager;
		myEntryDBManager = theEntryDatabaseManager;
		viewHistory = new LinkedList<>();
		setupBackFunctionality();
		setupListView();
	}
	
	@SuppressWarnings("serial")
	private void setupBackFunctionality() {
		myView.addBackButtonListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked back.");
				if (!viewHistory.isEmpty() && viewHistory.getLast() != null) {
					myView.showPage(viewHistory.pop());	
					System.out.println("Swapped page.");
				}
				if (viewHistory.isEmpty()) {
					myView.setBackButtonEnabled(false);
				}
			}			
		});
		if (viewHistory.isEmpty()) {
			myView.setBackButtonEnabled(false);
		}
	}
	
	private void addToHistory(Viewable theViewable) {
		viewHistory.add(theViewable);
		myView.setBackButtonEnabled(true);
	}
	
	private void setupListView() {
		JudgeContestListView ClistView = myView.getJugdgeContestListView();
		ClistView.setContestList(allContests());
		ClistView.addContestListListener(new ListSelectionListener() {

			// Show the entries from the contest
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					JudgeEntryListView ElistView = myView.getJugdgeEntryListView();
					ElistView.setEntryList(allEntries());
					myView.showPage(ElistView);
					addToHistory(ClistView);
				}
			}
		});
		myView.showPage(ClistView);
	}
	
	private Contest[] allContests() {
		List<Contest> contests = myContestDBManager.getAllContests();
		return contests.toArray(new Contest[contests.size()]);
	}
	
	private Entry[] allEntries() {
		List<Entry> entries = myEntryDBManager.getAllEntries();
		return entries.toArray(new Entry[entries.size()]);
	}
}

		
		
	

