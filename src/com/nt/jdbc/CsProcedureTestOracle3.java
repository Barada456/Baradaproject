package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureTestOracle3 {
private static final String CALL_PROCEDURE_QUERY="{CALL P_AUTHENTICATION(?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		String uName=null,uPass=null;
		Connection con=null;
		CallableStatement cs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter user name: ");
				uName=sc.nextLine();
				System.out.println("Enter user pass: ");
				uPass=sc.nextLine();
		    }
			
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
			
			//create callable statement object
			if(con!=null)
			cs=con.prepareCall(CALL_PROCEDURE_QUERY);
			
			//set OUT params as JDBC Types
			if(cs!=null) {
			cs.registerOutParameter(3, Types.VARCHAR);
			}
			if(cs!=null)
				cs.setString(1, uName);
			    cs.setString(2, uPass);
			//call PL/SQL procedure
			if(cs!=null)
				cs.execute();
			
			//gather results
			if(cs!=null) {
				System.out.println("Result Is : "+cs.getString(3));
			}
			
		}//TRY
		catch(SQLException se){
			if(se.getErrorCode()==1403)
				System.out.println("No record found with this eno..");
	    	se.printStackTrace();
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
	    finally {
	    	//close all jdbc ojects
	    	try {
	    		if(cs!=null)
	    			cs.close();
	    	}
	    	catch(SQLException se) {
	    		se.printStackTrace();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    	try {
	    		if(con!=null)
	    			con.close();
	    	}
	    	catch(SQLException se) {
	    		se.printStackTrace();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    	try {
	    		if(sc!=null)
	    			sc.close();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }//finally
		
	}//main

}//class
