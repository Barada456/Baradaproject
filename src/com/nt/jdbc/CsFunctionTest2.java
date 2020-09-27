package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsFunctionTest2 {
 private static final String CALL_FUNCTION_QUERY="{?=call FX_GET_EMPDETAILS_BY_EMPNO(?,?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		CallableStatement cs=null;
		int no=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Emp Number:");
			    no=sc.nextInt();
			}//if
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
			
		   //create callable statement object
			if(con!=null)
			cs=con.prepareCall(CALL_FUNCTION_QUERY);
			
			if(cs!=null) {
				//register OUT and Return Params with JDBC types
				cs.registerOutParameter(1, Types.INTEGER); //return param
				cs.registerOutParameter(3,Types.VARCHAR);//OUT params
				cs.registerOutParameter(4, Types.VARCHAR);//outparams
				cs.registerOutParameter(5, Types.INTEGER);//out params
				
				
				//set INPUT param value
				cs.setInt(2,no);
				
				//call PL/SQL query
				cs.execute();
				
				//gather results
				System.out.println(cs.getString(3)+" "+cs.getString(4)+" "+cs.getInt(5)+" "+cs.getInt(1));
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
