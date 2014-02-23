package com.p.gradebook;

import java.util.ArrayList;

/**
 * Holds all of the data that needs to be recorded about a semester.
 * 
 * @author Blake Whaley
 * 
 */
public class Semester {
	private String name;
	private ArrayList<Class> class_list;
	public double gpa;

	public Semester(String name) {
		this.name = name;
		class_list = new ArrayList<Class>();
	}

	public void addClass(Class c) {
		class_list.add(c);
	}

	public void calculateGpa() {
		int semCredits = 0;
		double gpaSum = 0;
		for (Class c : class_list) {
			semCredits += c.getCredits();
			gpaSum += letterToGpa(c.getLetterGrade());
		}
		setGpa(gpaSum / semCredits);
	}

	public double letterToGpa(String letter) {
		if (letter.equals("A+") || letter.equals("A")) {
			return 4.0;
		} else if (letter.equals("A-")) {
			return 3 + 2 / 3;
		} else if (letter.equals("B+")) {
			return 3 + 1 / 3;
		} else if (letter.equals("B")) {
			return 3.0;
		} else if (letter.equals("B-")) {
			return 2 + 2 / 3;
		} else if (letter.equals("C+")) {
			return 2 + 1 / 3;
		} else if (letter.equals("C")) {
			return 2;
		} else if (letter.equals("C-")) {
			return 1 + 2 / 3;
		} else if (letter.equals("D+")) {
			return 1 + 1 / 3;
		} else if (letter.equals("D")) {
			return 1;
		} else if (letter.equals("D-")) {
			return 2 / 3;
		}
		return 0;
	}

	/** Getters and Setters */
	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public Class getClass(String classname) {
		for (Class c : class_list) {
			if (classname.equals(c.getName())) {
				return c;
			}
		}
		return null;
	}

	public ArrayList<Class> getClasses() {
		return class_list;
	}

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

}
