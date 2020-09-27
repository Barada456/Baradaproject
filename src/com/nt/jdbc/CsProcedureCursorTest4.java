package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;

public class CsProcedureCursorTest4 {
private static final String CALL_PROCEDURE_QUERY="{CALL P_GET_EMPDETAILS_BY_DESGS(?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		String desg1=null,desg2=null;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		Boolean flag=false;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Desg1:");
				desg1=sc.next().toUpperCase();
				System.out.println("Enter Desg2:");
				desg2=sc.next().toUpperCase();
			}//if
		  //establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
			
		  //create jdbc callable statement object
		if(con!=null)
			cs=con.prepareCall(CALL_PROCEDURE_QUERY);
		
		
		if(cs!=null) {
			//register the out params with JDBC Types
			cs.registerOutParameter(3,OracleTypes.CURSOR);
		//set IN Params values
			cs.setString(1, desg1);
			cs.setString(2, desg2);
			
		//call PL/SQL Procedure Query
			cs.execute();
			
		//gather result values
			rs=(ResultSet)cs.getObject(3);
		
			if(rs!=null) {
			while(rs.next()) {
				flag=true;
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			}//while
		  }//if
			if(flag==true)
				System.out.println("Records Displayed");
			else
				System.out.println("Records are not available ");
		}//if
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
