package com.p.gradebook;

import java.util.ArrayList;

/**
 * 
 * A class holds all of the data for one class in a semester.
 * 
 * @author Blake Whaley
 * 
 */
public class Class {

	private String name;
	private double grade;
	private String letterGrade;
	private ArrayList<Gradeable> gradeables;
	private int credits;

	public Class(String n) {
		name = n;
		gradeables = new ArrayList<Gradeable>();
	}

	public void calculateClassGrade() {
		double tempGrade = 0;
		double sumWeights = 0;
		for (Gradeable g : gradeables) {
			tempGrade += g.getGrade() * g.getWeight();
			sumWeights += g.getWeight();
		}
		setGrade(tempGrade / (10 * sumWeights));
	}

	public void addGrade(Gradeable g) {
		gradeables.add(g);
	}

	public String gradeToLetter(double grade) {
		if (grade >= 93) {
			return "A";
		} else if (grade >= 90) {
			return "A-";
		} else if (grade >= 87) {
			return "B+";
		} else if (grade >= 83) {
			return "B";
		} else if (grade >= 80) {
			return "B-";
		} else if (grade >= 77) {
			return "C+";
		} else if (grade >= 73) {
			return "C";
		} else if (grade >= 70) {
			return "C-";
		} else if (grade >= 67) {
			return "D+";
		} else if (grade >= 63) {
			return "D";
		} else if (grade >= 60) {
			return "D-";
		}
		return "F";
	}

	/* Getters and Setters */

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	/**
	 * @return the grade
	 */
	public double getGrade() {
		return grade;
	}

	/**
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(double grade) {
		this.grade = grade;
	}

	/**
	 * @return the letterGrade
	 */
	public String getLetterGrade() {
		return letterGrade;
	}

	/**
	 * @param letterGrade
	 *            the letterGrade to set
	 */
	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}

	/**
	 * @return the gradeables
	 */
	public ArrayList<Gradeable> getGradeables() {
		return gradeables;
	}

	/**
	 * @param gradeables
	 *            the gradeables to set
	 */
	public void setGradeables(ArrayList<Gradeable> gradeables) {
		this.gradeables = gradeables;
	}

}
