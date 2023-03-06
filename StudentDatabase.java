package jdbc;

import java.sql.*;
import java.util.Scanner;

/* In this class, in various methods defined her, For writing query, Statement is created. 
 * records are displayed with the help of resultSet class and the inbuilt methods 
 * like next(), getInt(), getString().
 * Prepared statement is created when userinput is expected to completely execute the mysql query
 * To perform DML queries(INSERT, UPDATE, DELETE), executeUpdate() method is used. 
 * It returns an integer value which gives the no.of.records being deleted.
 * An integer variable is declared to hold the return value from executeUpdate() method.
 */


public class StudentDatabase {
	/* scanner object private to this class is created to accept userInput */
	private static Scanner scan = new Scanner(System.in);

	/* toAdd method is defined to insert data into a table in a database 
	 * corresponding to the query written inside preparedStatement.
	 */
	void toAdd(Connection connection) throws NullPointerException, SQLException{
		PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO student_info values(?, ?, ?, ?)");
		System.out.print("Enter the id to be inserted: ");
		insertStatement.setInt(1, scan.nextInt());
		System.out.print("Enter name to be inserted: ");
		insertStatement.setString(2, scan.next());
		System.out.print("Enter standard to be inserted: ");
		insertStatement.setString(3, scan.next());
		System.out.print("Enter contact.no to be inserted: ");
		insertStatement.setInt(4, scan.nextInt());

		int insertRecord = insertStatement.executeUpdate();
		System.out.println(insertRecord +" records inserted");
	}

	/* toDisplayAll method is defined to display all the records in a database 
	 * corresponding to the query written inside executeQuery().
	 */
	void toDisplayAll(Connection connection) throws NullPointerException, SQLException{
		Statement stmt = connection.createStatement();

		//student_info table is created in mydata database
		ResultSet result = stmt.executeQuery("SELECT * FROM student_info");
		while(result.next()) { 
			System.out.println(result.getInt(1)+" " +result.getString(2)+" " +result.getString(3)
			+" " +result.getInt(4));
		}	
	}

	/* toDisplaybyId method is defined to search for that particular id and display the record in a database 
	 * corresponding to the query written inside preparedStatement
	 */
	void toDisplaybyId(Connection connection) throws NullPointerException, SQLException {
		PreparedStatement prepstmnt = connection.prepareStatement("SELECT * FROM student_info WHERE studentId = ? ");  
		System.out.println("Enter Id to display that particular record: ");
		int searchId = scan.nextInt();
		prepstmnt.setInt(1,searchId);
		ResultSet searchRecord = prepstmnt.executeQuery();
		while(searchRecord.next()) {
			System.out.println(searchRecord.getInt(1)+" "+searchRecord.getString(2)
			+" "+searchRecord.getString(3)+" "+searchRecord.getInt(4));
		}
	}

	/* toUpdate method is defined to update that specific record corresponding to id entered, 
	 * from a database, corresponding to the query written inside preparedStatement.
	 */
	void toUpdate(Connection connection) throws NullPointerException, SQLException{
		System.out.println("Enter the id no to be updated: ");
		int recordNum = scan.nextInt();
		System.out.println("Enter the name to be updated: ");
		String name = scan.next();
		PreparedStatement updateStmt = connection.prepareStatement("UPDATE student_info SET studentName = ? WHERE studentId = ?");
		updateStmt.setString(1, name);
		updateStmt.setInt(2,recordNum);

		int updatedRecord = updateStmt.executeUpdate();
		System.out.println(updatedRecord +" record successfully updated!");
	}

	/* toDelete method is defined to  delete that specific record corresponding to id entered, from a database 
	 * corresponding to the query written inside preparedStatement
	 */
	void toDelete(Connection connection) throws NullPointerException, SQLException{
		PreparedStatement pstmt = connection.prepareStatement("DELETE FROM student_info WHERE studentId = ? ");
		System.out.println("Enter id to delete that record: ");
		int deleteId = scan.nextInt();
		pstmt.setInt(1, deleteId);

		int delRecord = pstmt.executeUpdate();
		System.out.println(delRecord +" record successfully deleted!");
	}

}


