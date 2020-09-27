package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsFunctionTest {
 private static final String CALL_FUNCTION_QUERY="{?=call FX_CALL_STUD_DETAILS_BY_SNO(?,?,?)}";
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con=null;
		int no = 0;
		CallableStatement cs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
			System.out.println("Enter Student No:");
			no = sc.nextInt();
		    }
			
			//establish connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
			
			//create callable statement object
			if(con!=null)
				cs=con.prepareCall(CALL_FUNCTION_QUERY);
			
			if(cs!=null) {
			//register OUT and Return Params with JDBC types
			cs.registerOutParameter(1, Types.FLOAT); //return Params
		    cs.registerOutParameter(3, Types.VARCHAR); //OUT Params
		    cs.registerOutParameter(4, Types.VARCHAR); //OUT Params 
		    
		    //set input values
		    cs.setInt(2, no);
		    
		    //execute PL/SQL Query
		    cs.execute();
		    
		    //gather results from OUT params
		    System.out.println("Student Name is:"+cs.getString(3));
		    System.out.println("Student Addrs is:"+cs.getString(4));
		    System.out.println("Student Avg is :"+cs.getFloat(1));
			}//if
		}//try
			catch(SQLException se){
				if(se.getErrorCode()==1403) {
				System.out.println("NO DATA FOUND");
				se.printStackTrace();
				}
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
