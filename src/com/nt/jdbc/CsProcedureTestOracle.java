package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;


public class CsProcedureTestOracle {
private static final String CALL_PROCEDURE_QUERY="{ CALL P_FIRST(?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int fNo=0,sNo=0;
		Connection con=null;
		CallableStatement cs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter First Number:");
				fNo=sc.nextInt();
				System.out.println("Enter Second Number:");
				sNo=sc.nextInt();
			}
			
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
			
			//create callablestatement object
			if(con!=null)
			cs=con.prepareCall(CALL_PROCEDURE_QUERY);
			
			//register OUT params with JDBC type
			if(cs!=null)
				cs.registerOutParameter(3, Types.INTEGER);
			
			//set values to IN Params
			if(cs!=null) {
				cs.setInt(1, fNo);
				cs.setInt(2, sNo);
			}
			
			//call PL/SQL Procedure
			if(cs!=null) {
				cs.execute();
			}
			
			//gather results from OUT param
			if(cs!=null) {
				System.out.println("Sum is : "+cs.getInt(3));
			}
			
		}//try
		catch(SQLException se){
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
