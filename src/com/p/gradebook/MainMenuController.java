package com.p.gradebook;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.gradebook.R;
import com.p.gradebook.NewSemesterDialog.NewSemesterDialogListener;

/**
 * Controller for the main menu in the app.
 * 
 * @author Blake Whaley
 * 
 */
public class MainMenuController extends FragmentActivity implements
		OnClickListener, OnItemSelectedListener, NewSemesterDialogListener {
	// Key for other activities to read extra data when started from this
	// activity
	public final static String EXTRA_MESSAGE = "com.p.gradebook.MESSAGE";

	// Holds data for application
	private ApplicationData appData;

	private static String FILENAME = "gradebook_data.gd";
	private View settingsButton;
	private View aboutButton;
	private View quitButton;
	private Spinner chooseSemester;

	private int num;

	/**
	 * Sets up the buttons and the spinner in the main menu.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		appData = new ApplicationData();
		loadApplicationData();

		// Button setup
		settingsButton = findViewById(R.id.settings);
		settingsButton.setOnClickListener(this);
		aboutButton = findViewById(R.id.about_button);
		aboutButton.setOnClickListener(this);
		quitButton = findViewById(R.id.quit_button);
		quitButton.setOnClickListener(this);

		chooseSemester = (Spinner) findViewById(R.id.choose_semester);
		chooseSemester.setOnItemSelectedListener(this);
		setAdapter();
		num = 0;

	}

	/**
	 * Setup for the spinner. Initializes with two options:
	 * index 0 - "Select a Semester" - does nothing when selected.
	 * index 1 - "Add New" - opens up a dialog box when selected.
	 */
	public void setAdapter() {
		int size = appData.getSemesters().size() + 2;
		String[] sems = new String[size];
		sems[0] = "Select a Semester";
		sems[1] = "Add New";
		int counter = 2;
		for (Semester s : appData.getSemesters()) {
			sems[counter] = s.getName();
			counter++;
		}
		// Additional spinner setup
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, sems);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		chooseSemester.setAdapter(adapter);
	}

	/**
	 * Save all of the application data in following format:
	 * Case Semester:
	 * Type, Name, Num Classes, gpa
	 * Case Class:
	 * Type, Name, num grades, grade, credits, letter
	 * Case Grade:
	 * Type, name, grade, max , received, weight
	 * 
	 * @return true if successful
	 */
	public boolean saveApplicationData() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			for (Semester s : appData.getSemesters()) {
				// Type
				fos.write("Semester,".getBytes());
				// Name
				fos.write(s.getName().getBytes());
				fos.write(",".getBytes());
				// Num Classes
				fos.write(Integer.toString(s.getClasses().size()).getBytes());
				fos.write(",".getBytes());
				// GPA
				fos.write(Double.toString(s.getGpa()).getBytes());
				fos.write("\n".getBytes());
				// Write All Class information
				for (Class c : s.getClasses()) {
					// Type
					fos.write("Class,".getBytes());
					// Name
					fos.write(c.getName().getBytes());
					fos.write(",".getBytes());
					// num grades
					fos.write(Integer.toString(c.getGradeables().size())
							.getBytes());
					fos.write(",".getBytes());
					// grade
					fos.write(Double.toString(c.getGrade()).getBytes());
					fos.write(",".getBytes());
					// credits
					fos.write(Integer.toString(c.getCredits()).getBytes());
					fos.write(",".getBytes());
					// letter
					fos.write(c.getLetterGrade().getBytes());
					fos.write("\n".getBytes());
					// Write gradeable data
					for (Gradeable g : c.getGradeables()) {
						// Type
						fos.write("Gradeable,".getBytes());
						// Name
						fos.write(g.getName().getBytes());
						fos.write(",".getBytes());
						// grade
						fos.write(Double.toString(g.getGrade()).getBytes());
						fos.write(",".getBytes());
						// max
						fos.write(Double.toString(g.getMaxPoints()).getBytes());
						fos.write(",".getBytes());
						// received
						fos.write(Double.toString(g.getRecievedPoints())
								.getBytes());
						fos.write(",".getBytes());
						// weight
						fos.write(Double.toString(g.getWeight()).getBytes());
						fos.write("\n".getBytes());
					}
				}
			}
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Load application data from file. According to the format specified
	 * in the saveApplicationData method.
	 * 
	 * @return true if successful
	 */
	public boolean loadApplicationData() {

		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(
					openFileInput(FILENAME)));
			String line;
			String[] splits;
			Semester curSem = null;
			Class curClass = null;
			while ((line = input.readLine()) != null) {
				splits = line.split(",");
				if (splits[0].equals("Semester")) {
					if (curSem != null) {
						appData.getSemesters().add(curSem);
					}
					curSem = new Semester(splits[1]);
					curSem.setGpa(Double.parseDouble(splits[3]));
				}
				if (splits[0].equals("Class")) {
					if (curClass != null) {
						curSem.addClass(curClass);
					}
					curClass = new Class(splits[1]);
					curClass.setGrade(Double.parseDouble(splits[3]));
					curClass.setCredits(Integer.parseInt(splits[4]));
					curClass.setLetterGrade(splits[5]);
				}
				if (splits[0].equals("Gradeable")) {
					Gradeable g = new Gradeable(splits[1]);
					g.setGrade(Double.parseDouble(splits[2]));
					g.setMaxPoints(Double.parseDouble(splits[3]));
					g.setRecievedPoints(Double.parseDouble(splits[4]));
					g.setWeight(Double.parseDouble(splits[5]));
					curClass.addGrade(g);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Method for inflating the menu/action bar.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.main_menu, menu);
		return true;
	}

	/**
	 * Action handler for clicks on buttons.
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.settings:
			break;
		case R.id.about_button:
			// Do about
			break;
		case R.id.quit_button:
			saveApplicationData();
			finish();
			break;
		}
	}

	/**
	 * Handles action when spinner item is selected. 
	 * "Add New" adds another semester to the list/program.
	 * Clicking on any other semester launches a new activity for that semester.
	 */
	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
		String s = (String) parent.getItemAtPosition(pos);
		// Add new semester to the list
		if (s.equals("Add New")) {
			showNewSemesterDialog();
		}
		// Open activity for semester selected.
		else {
			// Send intent to SemesterActivity
			Intent startSemester = new Intent(this, SemesterActivity.class);
			// Puts the semester name in the extra message for the activity
			// being started to read.
			startSemester.putExtra(EXTRA_MESSAGE, s);
			startActivity(startSemester);
		}

	}

	/**
	 * Action when nothing is selected by spinner.
	 */
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		System.out.println("Nothing Selected"); // For debugging
	}

	/**
	 * Launches the dialog box when a new semester is to be added.
	 */
	private void showNewSemesterDialog() {

		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		NewSemesterDialog nameDialog = NewSemesterDialog
				.newInstance("Give a name to your semester");
		nameDialog.show(fm, "fragment_edit_name");

	}

	/**
	 * Creates a new semester when given a string. 
	 * The dialog box calls this method to pass the information.
	 */
	@Override
	public void onNameGiven(String input) {
		Semester sem = new Semester(input);
		appData.getSemesters().add(sem);
		setAdapter();
	}
}
