package main;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Database{
	
	// create table indexes
	static int comp_index = 1, group_index = 1;
	
	// create scanner object
	static Scanner keyb = new Scanner(System.in);
	
	public static void main(String[] args)throws Exception{
		
		// establish a connection to oracle server
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection( 
	    "jdbc:oracle:thin:@sabzevi2.homeip.net:1521:orcl", "csus", "student");
		
		// create an instance of the statement object
	    Statement st=con.createStatement();	    
       
        // create empty tables
        initTables(st);
        
        //populate tables with starting data
        initData(st);
        
        // display menu
        System.out.println("Menu \n----------------\n 1) Insert\n 2) Delete\n 3) Update\n 4) View\n 5) Quit\n");
        
        // prompt the user
        System.out.println("Please enter a number or 'Menu' to see options.\n");
        String input = commandPrompt();
        
        // react based on input given
        processInput(st, input);
        
        // drop tables
        dropTables(st);
        
        // close scanner class
        keyb.close();
   }
	
	private static void initTables(Statement st) throws SQLException {
		
		// drop tables if they exist
		dropTables(st);
		
		// create new tables
		st.executeQuery("CREATE TABLE villa_company (company_id NUMBER CONSTRAINT villa_company_id_pk PRIMARY KEY, name VARCHAR(20))");
		st.executeQuery("CREATE TABLE villa_group (group_id NUMBER CONSTRAINT villa_group_id_pk PRIMARY KEY, "
	   		+ "name VARCHAR(20), company_id NUMBER CONSTRAINT villa_company_id_fk REFERENCES villa_company)");
		
	}
	
	private static void initData(Statement st) throws SQLException {
		
		// populate tables with starting data
		st.executeQuery("INSERT INTO villa_company VALUES(" + comp_index + ", 'JYP')");
		comp_index++;
		st.executeQuery("INSERT INTO villa_company VALUES(" + comp_index + ", 'HYBE')");
		comp_index++;
		st.executeQuery("INSERT INTO villa_company VALUES(" + comp_index + ", 'CUBE')");
		comp_index++;
		st.executeQuery("INSERT INTO villa_company VALUES(" + comp_index + ", 'SM')");
		comp_index++;
		st.executeQuery("INSERT INTO villa_company VALUES(" + comp_index + ", 'YG')");
		comp_index++;
		
		
		st.executeQuery("INSERT INTO villa_group VALUES(" + group_index + ", 'Twice', 1)");
		group_index++;
		st.executeQuery("INSERT INTO villa_group VALUES(" + group_index + ", 'Itzy', 1)");
		group_index++;
		st.executeQuery("INSERT INTO villa_group VALUES(" + group_index + ", 'Le Sserafim', 2)");
		group_index++;
		st.executeQuery("INSERT INTO villa_group VALUES(" + group_index + ", 'Seventeen', 2)");
		group_index++;
		st.executeQuery("INSERT INTO villa_group VALUES(" + group_index + ", 'New Jeans', 2)");
		group_index++;
		st.executeQuery("INSERT INTO villa_group VALUES(" + group_index + ", '(G)I-dle', 3)");
		group_index++;
		st.executeQuery("INSERT INTO villa_group VALUES(" + group_index + ", 'Red Velvet', 4)");
		group_index++;
	}
	
	private static String commandPrompt() throws IOException{
		
		// prompt user
		System.out.println("Enter command > ");
		
		// save input into variable
		String inp = keyb.nextLine();
		return inp;
	}
	
	private static void processInput(Statement st, String input) throws IOException, SQLException {
		
		String inp;
		
		// handle input depending on command
		switch(input.toLowerCase().trim()){
		case "menu":
			System.out.println("Menu \n----------------\n 1) Insert\n 2) Delete\n 3) Update\n 4) View\n 5) Quit\n");
			inp = commandPrompt(); // prompt user again
			processInput(st, inp); // process input
			break;
		case "1":
			insert(st);
			break;
		case "2":
			delete(st);
			break;
		case "3":
			update(st);
			break;
		case "4":
			view(st);
			break;
		case "5":
			System.out.println("\n-----------------\nThanks for stopping by! :)");
			break;
		default:
			System.out.println("Invalid command");
			inp = commandPrompt(); // prompt user again
			processInput(st, inp); // process input
		}
	}
	
	private static void insert(Statement st) throws IOException, SQLException {
		
		// prompt user
		System.out.println("What group would you liked to add?");
		String group = commandPrompt();
		
		System.out.println("What company are they associated with?");
		String company = commandPrompt();
		
		// check parent table for valid record
		ResultSet rs = st.executeQuery("SELECT * FROM villa_company WHERE name = '" + company + "'");
		if (rs.next()) {
			// parent record does exist, just insert child record
			st.executeQuery("INSERT INTO villa_group VALUES (" + group_index + ", '" + group.trim() + "', " + rs.getString(1) + ")");
			group_index++;
		} else {
			// otherwise, insert parent record and child record
			st.executeQuery("INSERT INTO villa_company VALUES (" + comp_index + ", '" + company.trim() + "')");
			st.executeQuery("INSERT INTO villa_group VALUES(" + group_index + ", '" + group.trim() + "', " + comp_index + ")");
			comp_index++;
			group_index++;
		}
		
		// validate insertion
		System.out.println("\n----------------\nRecord successfully inserted\n----------------\n");
		
		// return to command prompt
		String inp = commandPrompt();
		processInput(st, inp);
		
	}
	
	private static void delete(Statement st) throws IOException, SQLException {
		
		// prompt user
		System.out.println("Please enter the primary key of the record you want to delete.\n");
		int input = Integer.parseInt(commandPrompt());
		
		// query database to find record that user inputed
		ResultSet rs = st.executeQuery("SELECT * FROM villa_group WHERE group_id = " + input);
        rs.next(); //because the primary key will ensure there will always be 1 row returned
        int c_id = Integer.parseInt(rs.getString(3)); //retrieve company_id
        
        // check if there are other records tied to this parent record
		rs = st.executeQuery("SELECT * FROM villa_group WHERE company_id = " + c_id);
		rs.next();
        
		if(rs.next() == false) {
        	// if there is only one record returned, delete the parent record
        	st.executeQuery("DELETE FROM villa_group WHERE group_id = " + input);
        	st.executeQuery("DELETE FROM villa_company WHERE company_id = " + c_id);
        } else {
        	//otherwise, just delete the child record
        	st.executeQuery("DELETE FROM villa_group WHERE group_id = " + input);
        }
		
		// validate deletion
		System.out.println("\n----------------\nRecord successfully deleted\n----------------\n");

		// return to command prompt
		String inp = commandPrompt();
		processInput(st, inp);
	}
	
	private static void update(Statement st) throws IOException, SQLException {
		
		// prompt user
		System.out.println("What company do you want to change? ");
		String company = commandPrompt();
		
		System.out.println("What do you want to change it to? ");
		String changeCompany = commandPrompt();
		
		// update record
		st.executeQuery("UPDATE villa_company SET name = '" + changeCompany 
				+ "' WHERE LOWER(name) ='" + company.toLowerCase().trim() + "'");
		
		// validate update
		System.out.println("\n----------------\nRecord successfully updated\n----------------\n");
		
		// return to command prompt
		String inp = commandPrompt();
		processInput(st, inp);
	}
	
	private static void view(Statement st) throws IOException, SQLException {
		
		// join both tables
		ResultSet rs = st.executeQuery("SELECT g.name, c.name FROM villa_group g, "
				+ "villa_company c WHERE g.company_id = c.company_id");
		
		while (rs.next()) {
			System.out.println(rs.getString(1) + ": " + rs.getString(2));
		}
		
		// return to command prompt
		String inp = commandPrompt();
		processInput(st, inp);
		
	}
	
	private static void dropTables(Statement st) throws SQLException {
		
		// drop tables
		try {
			st.executeQuery("DROP TABLE villa_group");
    	    st.executeQuery("DROP TABLE villa_company");
	    } catch (SQLException s){  }
	}
}

