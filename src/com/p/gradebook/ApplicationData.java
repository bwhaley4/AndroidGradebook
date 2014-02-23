package com.p.gradebook;

import java.util.ArrayList;

/**
 * 
 * @author Blake Whaley
 *
 */
public class ApplicationData {
	private ArrayList<Semester> semesters;

	/**
	 * 
	 */
	public ApplicationData() {
		// Set up
		semesters = new ArrayList<Semester>();
	}

	/**
	 * @return the semesters
	 */
	public ArrayList<Semester> getSemesters() {
		return semesters;
	}

	/**
	 * @param semesters
	 *            the semesters to set
	 */
	public void setSemesters(ArrayList<Semester> semesters) {
		this.semesters = semesters;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Semester getSemesterByName(String name) {
		int i = 0;
		while (i < semesters.size()) {
			if (name.equals(semesters.get(i).getName())) {
				return semesters.get(i);
			}
		}
		return null;
	}

}
