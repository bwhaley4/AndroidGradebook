package com.p.gradebook;

/**
 * A gradeable holds the data for all things that can be graded in a class.
 * 
 * @author Blake Whaley
 * 
 */
public class Gradeable {

	private double grade;
	private double maxPoints;
	private double recievedPoints;
	private String name;
	private double weight;

	public Gradeable(String n) {
		name = n;
	}

	/* Getters and Setters */

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
	 * @return the maxPoints
	 */
	public double getMaxPoints() {
		return maxPoints;
	}

	/**
	 * @param maxPoints
	 *            the maxPoints to set
	 */
	public void setMaxPoints(double maxPoints) {
		this.maxPoints = maxPoints;
	}

	/**
	 * @return the recievedPoints
	 */
	public double getRecievedPoints() {
		return recievedPoints;
	}

	/**
	 * @param recievedPoints
	 *            the recievedPoints to set
	 */
	public void setRecievedPoints(double recievedPoints) {
		this.recievedPoints = recievedPoints;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

}
