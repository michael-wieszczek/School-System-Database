package schoolSystemDatabase;

import schoolSystemDatabase.Address;
import schoolSystemDatabase.InvalidInputException;
import schoolSystemDatabase.Student;

/**
 * Student.java <br>
 * This class contains the getters and setters for the student object. It also 
 * implements comparable. This class takes input for each field from SchoolSystemDatabase
 * and formats it and saves it to a student. <br>
 * @author Michael Wieszczek <br>
 * January 20th, 2020
 */
public class Student implements Comparable <Student> { 

	private int studentNum;
	private String lastName;
	private String firstName;
	private char midInitial; 
	private int grade;
	private String phoneNum;
	private String email;
	private Address r = new Address();
	private String[] classes;

	/**
	 * @return the studentNum
	 */
	public int getStudentNum() {
		return studentNum;
	}
	/**
	 * @param studentNum the studentNum to set
	 */
	public void setStudentNum(int studentNum) throws InvalidInputException {
		if(studentNum > 0 && studentNum < 2000000000) {
			this.studentNum = studentNum;
		}
		else {
			throw new InvalidInputException();
		}
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		char cap = lastName.toUpperCase().charAt(0);
		lastName = cap + lastName.substring(1);
		this.lastName = lastName;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		char cap = firstName.toUpperCase().charAt(0);
		firstName = cap + firstName.substring(1);
		this.firstName = firstName;
	}
	/**
	 * @return the midInitial
	 */
	public char getMidInitial() {
		return midInitial;
	}
	/**
	 * @param c the midInitial to set
	 */
	public void setMidInitial(String midInitial) throws InvalidInputException {
		this.midInitial = midInitial.toUpperCase().charAt(0);
	}
	/**
	 * @return the grade
	 */
	public int getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set.
	 */
	public void setGrade(int grade) throws InvalidInputException {
		if(grade >= 9 && grade <= 12) {
			this.grade = grade;	
		}
		else {
			throw new InvalidInputException();
		}

	}
	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		if(phoneNum.length() == 10) {
			int j = 0;
			for(int i = 0; i < 10; i++) {
				if(phoneNum.charAt(i) == '0' || phoneNum.charAt(i) == '1' || phoneNum.charAt(i) == '2' || phoneNum.charAt(i) == '3' || phoneNum.charAt(i) == '4' || phoneNum.charAt(i) == '5' || phoneNum.charAt(i) == '6' || phoneNum.charAt(i) == '7' || phoneNum.charAt(i) == '8' || phoneNum.charAt(i) == '9') {
					j++;				}
			}
			if(j == 10) {
				phoneNum = "(" + phoneNum.charAt(0) + phoneNum.charAt(1) + phoneNum.charAt(2) + ")" + "-" + phoneNum.charAt(3) + phoneNum.charAt(4) + phoneNum.charAt(5) + "-" + phoneNum.charAt(6) + phoneNum.charAt(7) + phoneNum.charAt(8) + phoneNum.charAt(9);
				this.phoneNum = phoneNum;
			}
			else {
				throw new InvalidInputException();
			}
		}
		else {
			throw new InvalidInputException();
		}
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) throws InvalidInputException{
		int j = 0;
		for(int k = 0; k < email.length(); k++) {
			if(email.charAt(k) == '@') {
				k = email.length();
				j = -1;
			}
		}
		if(j == -1) {
			this.email = email;
		}
		else {
			throw new InvalidInputException();
		}
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return r;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.r = address;
	}
	/**
	 * @return the classes
	 */
	public String[] getClasses() {
		return classes;
	}
	/**
	 * @param classes the classes to set
	 */
	public void setClasses(String[] classes) {
		this.classes = classes;
	}
	public int compareTo(Student r) {
		if(this.studentNum > r.getStudentNum()) {
			return 1;
		}
		else if(this.studentNum < r.getStudentNum()) {
			return -1;
		}
		return 0;
	}
}