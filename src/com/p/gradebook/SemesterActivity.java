package com.p.gradebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

/**
 * The activity for when the user is interacting with information
 * pertinent to a specific semester. Will be launched by the
 * MainMenuController class.
 * 
 * 
 * @author Blake Whaley
 *
 */
public class SemesterActivity extends Activity {

	/**
	 * Retrieves data for which semester is being loaded which is stored in the extra message.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Make sure running on honeycomb or higher to use Actionbar
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		// Gets the intent and extra message
		Intent intent = getIntent();
		String name = intent.getStringExtra(MainMenuController.EXTRA_MESSAGE);
		// Get data for the specific semester.
		// Semester semester =

	}

	/**
	 * Sets what happens when the "home" button is pressed.
	 * Activity is set to move back to calling activity.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
