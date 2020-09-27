package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureMySQLTest1 {
	private static final String CALL_PROCEDURE_QUERY="{call P_GET_PRODUCT_DETAILS_BY_PID(?,?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int pno=0;
		Connection con=null;
		CallableStatement cs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Product Number:");
				pno=sc.nextInt();
			}//if
			
			//establish connection
			con=DriverManager.getConnection("jdbc:mysql:///advjavasql","root","root");
		
			//create callable statement object
			if(con!=null)
				cs=con.prepareCall(CALL_PROCEDURE_QUERY);
			
			if(cs!=null) {
				//register OUT params with JDBC Types
				cs.registerOutParameter(2,Types.VARCHAR);
				cs.registerOutParameter(3, Types.FLOAT);
				cs.registerOutParameter(4, Types.FLOAT);
				
				//set IN Param values
				cs.setInt(1,pno);
				
				//call PL/SQL Query
				cs.execute();
				
				//gather ressults from OUT Params
				System.out.println(cs.getString(2)+" "+cs.getFloat(3)+" "+cs.getFloat(4));
				
				
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
