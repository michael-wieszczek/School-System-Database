package schoolSystemDatabase;

	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.PrintStream;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.InputMismatchException;
	import java.util.NoSuchElementException;
	import java.util.Scanner;
	/**
	 * SchoolSystemDatabase.java <br>
	 * This class gives the user multiple options to operate within a student database, when 
	 * entering a specific integer, a corresponding command will activate, you can add, print,
	 * load, save, sort, search, insert, and delete students within/to the database. This program
	 * saves each student as an object, with information relating to them saved within the 
	 * object. <br>
	 * @author Michael Wieszczek <br>
	 * January 20th, 2020
	 */
	public class SchoolSystemDatabase {

		static Scanner sc = new Scanner(System.in);

		public static void main(String[] args) throws FileNotFoundException {
			Student student = null;
			File file = new File("src/schoolSystemDatabase/student.txt");
			ArrayList<Student> database = new ArrayList<Student>();
			System.out.println("What would you like to do?:");
			int i = 0;
			do {
				System.out.println("1. Add a new Student?");
				System.out.println("2. Print to screen?");
				System.out.println("3. Load");
				System.out.println("4. Save");
				System.out.println("5. Sort");
				System.out.println("6. Search");
				System.out.println("7. Insert");
				System.out.println("8. Delete");
				System.out.println("9. Exit");
				try {
					i = sc.nextInt();

					if(i == 1) {
						//enter a student into the database
						student = createStudent();
						int arrPlace = search(database,student.getStudentNum());
						if(arrPlace == -1) {
							database.add(student);
						}
						else {
							String choice;
							System.out.println("Duplicate student number detected! Would you like to replace the old student with the new one?");
							System.out.println("Y/N");
							choice = sc.nextLine();
							if(choice.toUpperCase().charAt(0) == 'Y') {
								database.set(arrPlace, student);
							}
						}
					}
					else if(i == 2) {
						//print all the students currently in the database
						for(int j = 0; j < database.size(); j++) {
							printStudent(database.get(j));
							System.out.println();
						}
					}
					else if(i == 3) {
						//load data from file
						try {
							Scanner fscan = new Scanner(file);
							int numStudents = fscan.nextInt();
							fscan.nextLine();
							for(int j = 0; j < numStudents; j++) {
								String[] arr = fscan.nextLine().split("_");
								String[] classes = new String[8];
								for(int k = 11, l = 0; k < 19 || l < 8; k++,l++) {
									classes[l] = arr[k];
								}
								student = createStudent(arr[0],arr[1],arr[2],Integer.parseInt(arr[3]),arr[4],arr[5],Integer.parseInt(arr[6]),arr[7],arr[8],arr[9],arr[10],classes);
								database.add(student);
							}
							fscan.close();
						}
						catch (FileNotFoundException e) {
							System.err.print("Could not find file");
						}	
						catch (NoSuchElementException e) {
							System.err.print("File is empty");
						}
					}
					else if(i == 4) {
						//save data from current session into file
						PrintStream fps = new PrintStream(file);
						fps.println(database.size());
						for(int j = 0; j < database.size(); j++) {
							fps.println(toString(database.get(j)));
						}
						fps.close();
					}
					else if(i == 5) {
						//sorts everything.
						Collections.sort(database);
					}
					else if(i == 6) {
						//searches for the given student number
						System.out.print("Please enter a student number to search for: ");
						int searchNum = sc.nextInt(); 
						int index = search(database, searchNum);
						if(index == -1) {
							System.out.println("Student could not be found");
						}
						else {
							System.out.println("Student is at spot " + index);
							System.out.println();
							printStudent(database.get(index));
						}
					}
					else if(i == 7) {
						//Inserts a student at a specific point in a sorted database
						Collections.sort(database);
						student = createStudent();
						int arrPlace = search(database,student.getStudentNum());
						if(arrPlace == -1) {
							if(database.isEmpty() == true) {
								database.add(student);
							}
							else {
								for(int j = 0; j < database.size(); j++) {
									if(student.getStudentNum() < database.get(j).getStudentNum()) {
										database.add(j,student);
										j = database.size() + 1;
									}
									else if(j == database.size() - 1) {
										database.add(student);
										j = database.size() + 1;
									}
								}
							}	
						}
						else {
							String choice;
							System.out.println("Duplicate student number detected! Would you like to replace the old student with the new one?");
							System.out.println("Y/N");
							choice = sc.nextLine();
							if(choice.toUpperCase().charAt(0) == 'Y') {
								database.set(arrPlace, student);
							}
						}
					}
					else if(i == 8) {
						//Deletes a specific student using their student number
						System.out.print("Please enter a student number: ");
						int studNum = sc.nextInt(); //Get int check
						int index = search(database,studNum);
						if(index >= 0) {
							database.remove(index);
						}
						else {
							System.out.println("No student number found");
							System.out.println();
						}
					}
				}
				catch (InvalidInputException ie) {
					System.err.println("Invalid Input");
					sc.nextLine();
				}
				catch(InputMismatchException ime) {
					System.err.println("Invalid Input");
					sc.nextLine();
				}
			}while(i < 9);
		}
		/**
		 * This method prompts the user to fill in information about the student, the 
		 * information is then passed into the student and address class where it is 
		 * formatted and saved. <br>
		 * @return r - the student object
		 */
		public static Student createStudent() {

			Student r = new Student();
			int j = 0;
			
			for(int i = 0; i < 1; i++) {
				System.out.print("First Name: ");
				String firstName = sc.next();
				r.setFirstName(firstName);
			}
			for(int i = 0; i < 1; i++) {
				System.out.print("Middle Initial: ");
				r.setMidInitial(sc.next());
			}
			for(int i = 0; i < 1; i++) {
				System.out.print("Last Name: ");
				String lastName = sc.next();
				r.setLastName(lastName);
			}
			do {
				try {
					System.out.print("Grade: ");
					int grade = sc.nextInt();
					r.setGrade(grade);
					j++;
				}
				catch(InvalidInputException ie) {
					System.err.println("Please enter a valid grade");
					sc.nextLine();
				}
				catch(InputMismatchException ime) {
					System.err.println("Please enter a valid grade");
					sc.nextLine();
				}
			}while(j == 0);
			
			for(int i = 0; i < 1;) {
				try {
				System.out.print("Email: ");
				String email = sc.next();
				r.setEmail(email);
				i++;
				}catch(InvalidInputException ie) {
					System.err.println("Please enter a valid email");
				}
			}
			j = 0;
			do {
				try {
				System.out.print("Phone Number: ");
				String phoneNum = sc.next();
				r.setPhoneNum(phoneNum);
				j++;
				}
				catch(InvalidInputException ie) {
					System.err.println("Invalid phone number entered");
				}
			}while(j == 0);
			do {
				try {
					System.out.print("Student Number: ");
					r.setStudentNum(sc.nextInt());
					j++;
				}
				catch(InvalidInputException ie) {
					System.err.println("Please enter a valid student number");
					sc.nextLine();
				}
				catch(InputMismatchException ime) {
					System.err.println("Please enter a valid student number");
					sc.nextLine();
				}
				
			}while(j == 1);
			
			System.out.println("What is the student's Address:");
			for(int i = 0; i < 1;) {
				sc.nextLine();
				System.out.print("City: ");
				String city = sc.next();
				r.getAddress().setCity(city);
				i++;
			}
			for(int i = 0; i < 1;) {
				try {
					System.out.print("Province/Territory: ");
					String province = sc.next();
					r.getAddress().setProvince(province);
					i++;
				} catch(InvalidInputException ie) {
					System.err.println("Please enter a valid province/territory");
				}
			}
			sc.nextLine();
			for(int i = 0; i < 1;) {
				System.out.print("Steet Address: ");
				String street = sc.nextLine();
				if(street.length() > 0) {
				r.getAddress().setStreet(street);
				i++;
				}
				else {
					System.err.print("Invalid Street Address");
					System.out.println();
				}
			}
			do {
				try {
				System.out.print("Postal Code: ");
				String postal = sc.next();
				r.getAddress().setPostal(postal);
				j++;
				} catch(InvalidInputException ie) {
					System.err.println("Invalid postal code form (A1A1A1)");
				}
			}while(j == 2);
					
			String[] classes = new String[8]; //add saftey to this
			System.out.println("Please print the student's classes(course codes) in order, leave an S for a spare");
			for(int i = 0; i < 8; i++) {
				String schoolClass = "null";
				for(int k = 0; k < 1;) {
					schoolClass = sc.nextLine();
					if(schoolClass.length() < 9 && schoolClass.length() > 0) {
						k = 1;
					}
					else {
						System.out.println("Please enter a valid course code:");
					}
				}
				if(schoolClass.compareToIgnoreCase("s") == 0) {
					schoolClass = "Spare";
				}
				else {
					char s2 = schoolClass.toUpperCase().charAt(0);
					schoolClass = s2 + schoolClass.substring(1);
				}
				classes[i] = schoolClass;
			}
			r.setClasses(classes);
			return r;
		}

		/**
		 * This method creates a student when all information about the student is already
		 * known and saved in a file. <br>
		 * @param firstName
		 * @param c
		 * @param lastName
		 * @param grade
		 * @param email
		 * @param phoneNum
		 * @param studentNum
		 * @param address
		 * @param city
		 * @param province
		 * @param postal
		 * @param classes
		 * @return r - The student object
		 */
		public static Student createStudent(String firstName, String c, String lastName, int grade, String email, String phoneNum, int studentNum, String address, String city, String province, String postal, String[] classes) {
			Student r = new Student();
			r.setFirstName(firstName);
			r.setMidInitial(c);
			r.setLastName(lastName);
			r.setGrade(grade);
			r.setEmail(email);
			r.setPhoneNum(phoneNum);
			r.setStudentNum(studentNum);
			r.getAddress().setStreet(address);
			r.getAddress().setCity(city);
			r.getAddress().setProvince(province);
			r.getAddress().setPostal(postal);
			r.setClasses(classes);
			return r;
		}
		/**
		 * Prints out and formats all the data for a given student <br>
		 * @param r - The given student object
		 */
		public static void printStudent(Student r) {
			//Cleanup output
			System.out.println("Name: " + r.getFirstName() + " " + r.getMidInitial() + ". " + r.getLastName()); 
			System.out.println("Email: " + r.getEmail());
			System.out.println("Phone Number: " + r.getPhoneNum());
			System.out.println("Student Number: " + r.getStudentNum()); 
			System.out.println("Address: " + r.getAddress().getStreet() + ", " + r.getAddress().getCity() + ", " + r.getAddress().getProvince() + ", " + r.getAddress().getPostal());
			System.out.printf("%-11s %-20s %s","","Semester 1:", "Semester 2:");
			System.out.println();
			for(int i = 0; i < 4; i++) {
				System.out.print("Period " + (i + 1) + ":    ");
				System.out.printf("%-20s %s", r.getClasses()[i], r.getClasses()[i+4]);
				System.out.println();
			}
		}
		/**
		 * Takes all the data stored in the student object and uses concatenation to turn it into
		 * one string to be saved in a file <br>
		 * @param r - The student objects
	r.	 * @return saveState - The concatenated string of the student
		 */
		public static String toString(Student r) {
			String saveState = r.getFirstName() + "_" + r.getMidInitial() + "_" + r.getLastName() + "_" + r.getGrade() + "_" + r.getEmail() + "_" + r.getPhoneNum().substring(1,4) + r.getPhoneNum().substring(6, 9) + r.getPhoneNum().substring(10, 14) + "_" + r.getStudentNum() + "_" + r.getAddress().getStreet() + "_" + r.getAddress().getCity() + "_" + r.getAddress().getProvince() + "_" + r.getAddress().getPostal() + "_" + r.getClasses()[0] + "_" + r.getClasses()[1] + "_" + r.getClasses()[2] + "_" + r.getClasses()[3] + "_" + r.getClasses()[4] + "_" + r.getClasses()[5] + "_" + r.getClasses()[6] + "_" + r.getClasses()[7];
			return saveState;

		}
		/**
		 * When given the array and target this method will count through the array until it either
		 * finds the target, or reaches the end <br>
		 * @param arr - The given array
		 * @param target - The value we are trying to find
		 * @return i - Either the index of the target, or -1 is target was not found
		 */
		public static int search(ArrayList<Student> database, int target) {
			for(int i = 0; i < database.size(); i++) {
				if(target == database.get(i).getStudentNum()) {
					return i;
				}
			}
			return -1;
		}

	}
