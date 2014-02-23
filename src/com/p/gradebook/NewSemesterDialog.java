package com.p.gradebook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

/**
 * Dialog box that appears when user clicks on "add new semester" from drop down
 * menu in the Main Menu.
 * 
 * @author Blake Whaley
 * 
 */
public class NewSemesterDialog extends DialogFragment {

	private EditText text;
	private String name;

	/**
	 * Constructor.
	 */
	public NewSemesterDialog() {
		// Required Empty constructor
	}

	/**
	 * interface for a custom dialog box for adding a new semester.
	 * 
	 * NOTE TO SELF:
	 * All listeners must have the onNameGiven method to get 
	 * the value put into the edittext.
	 * 
	 * @author Blake Whaley
	 *
	 */
	public interface NewSemesterDialogListener {
		void onNameGiven(String input);
	}

	/**
	 * Creates the instance of the dialog.
	 * 
	 * @param title
	 * @return
	 */
	public static NewSemesterDialog newInstance(String title) {
		NewSemesterDialog d = new NewSemesterDialog();
		Bundle args = new Bundle();
		args.putString("title", title);
		d.setArguments(args);
		return d;
	}

	/**
	 * Builds the layout of the dialog.
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle("What is the new semester to be called?");
		text = new EditText(getActivity());
		builder.setView(text);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String name = text.getText().toString().trim();
				NewSemesterDialogListener listener = (NewSemesterDialogListener) getActivity();
				listener.onNameGiven(name);

			}
		});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		return builder.create();

	}
}
