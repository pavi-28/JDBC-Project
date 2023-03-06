package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/* blueprint of the system */

public class Student {

	/* creating a scanner class object for getting input from user. 
	 * As attributes needs to be accessed only by Admin, we create it with private access specifier
	 */
	private static Scanner scanner = new Scanner(System.in);

	private String studentName;
	private int studentId;
	private String standard;
	private long contactNo;

	static Connection connection = null;

	/* toEstablishConnection() method is defined for creating a connection with
	 * system database by providing login credentials of MySQL defining it inside
	 * try-catch to handle the exceptions
	 */
	static Connection toEstablishconnection() {

		try {
			String username = "root";
			String password = "Pavi@2861";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", username, password);

		} 
		catch (Exception obj) {
			System.out.println(obj);
		}
		return connection;
	}

	/* When an object is created, JVM calls default constructor. 
	 * Here we prompt user to enter userName and password to login 
	 * Calling login method by passing parameters
	 */
	public Student() throws NullPointerException, SQLException {
		System.out.println("!!! Login First !!!");
		System.out.print("Enter username: ");
		String userName = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		/* calling the toLogin method */
		toLogin(userName, password);             
	}

	/* To access the private attributes of the system, public getter and setter
	 * methods are created with the help of encapsulation
	 */
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	/* Defining a login method using if condition to check username and password, 
	 * entered by user is equal to the value initialized or not. 
	 * Only when the conditions are true, the system gets logged in and display the menu. 
	 * Else, the system fails to login
	 */
	void toLogin(String userName, String password)
			throws NullPointerException, SQLException {
		if (userName.equals("Room1") && password.equals("Login123")) { 
			System.out.println("successfully logged in");

			System.out.println("\n1. To Insert a student");
			System.out.println("2. To Display all students");
			System.out.println("3. To search By Id a student");
			System.out.println("4. To Update a student");
			System.out.println("5. To Delete a student");
			System.out.println("6. Press 6 to Logout");

			/* creating an object for studentDatabase class to call the non-static methods
			 * inside that class. */
			StudentDatabase stndobj = new StudentDatabase();

			/* variable of Connection type created to capture 
			 * the returned value from toEstablishConnection() method */
			Connection connectionToDb = toEstablishconnection(); 

			/* variable to store user input value */
			byte userInput;    
			/* do-loop executes at least once before checking condition */
			do {                     
				userInput = scanner.nextByte();

				/* passing userInput to switch case as case number */
				switch(userInput) {     

				/* userInput = 1 - toAdd method is called (inserting records) */
				case 1:
					stndobj.toAdd(connectionToDb); 
					break;
					/* userInput = 2 - toDisplayAll method is called */
				case 2:
					stndobj.toDisplayAll(connectionToDb); 
					break;
					/* userInput = 3 - toDisplaybyId method called */
				case 3:
					stndobj.toDisplaybyId(connectionToDb); 
					break;
					/* userInput = 4 - toUpdate method is called */
				case 4:
					stndobj.toUpdate(connectionToDb); 
					break;					
					/* userInput =5 - toDelete method is called */
				case 5:
					stndobj.toDelete(connectionToDb); 
					break;					
					/* when 6 is the userInput, user is logged out and 
					 * the connection to database is closed.
					 */
				case 6: 
					toLogout();
					connection.close();
					break;

					/* if the userInput doesn't match case value, default statement is executed */
				default: 
					System.out.println(" Enter the Correct valid Input! ");
				}
			}

			/* When userinput is 6, the corresponding case's method call is executed.
			 * So, that the system gets logged out. 
			 * Until input is not equal to 6, the do-loop gets executed
			 */
			while (userInput != 6);
		}

		/* If the if condition fails, then the else part is executed */
		else
			System.out.println("Login Failed!!!");
	}

	/* Defining logout() method for logging out the system */
	static void toLogout() {
		System.out.println("Logged out!!! \nThank You! Visit Again!!!");
	}

}



