package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class CsFunctionTest3 {
private static final String CALL_FUNCTION_QUERY="{?=call FX_VIEW_DELETE_STUD_BY_SNO(?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Student No:");
				no=sc.nextInt();
			}//if
		//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:localhost:1521:orcl","HR","human");
		
		//create callable statement object
			if(con!=null)
				cs=con.prepareCall(CALL_FUNCTION_QUERY);
		
		    if(cs!=null) {
		    	//register OUT and Return param with JDBC Types
		    	cs.registerOutParameter(3,OracleTypes.CURSOR); //OUT params
		    	cs.registerOutParameter(1, Types.VARCHAR);
		    	
		    	//set Int param
		    	cs.setInt(2,no);
		    	
		    	//call PL/SQL Query
		    	cs.execute();
		    	
		    	//gather results from Resultset and OUT params
		    	rs=(ResultSet)cs.getObject(3);
		    	if(rs!=null) {
		    	  if(rs.next())
		    	  System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
		    	}
		    	System.out.println(cs.getString(1));
		    }
		    	
		}//try
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
