package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CsProcedureMySQLTest2 {
	private static final String CALL_PROCEDURE_QUERY="{call P_GET_PRODUCT_DETAILS_BY_PNAME(?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		CallableStatement cs=null;
		String pname1=null,pname2=null;
		ResultSet rs=null;
		Boolean flag=false;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Product NAME1:");
				pname1=sc.next();
				System.out.println("Enter Product NAME2:");
				pname2=sc.next();
			}//if
			
			//establish connection
			con=DriverManager.getConnection("jdbc:mysql:///advjavasql","root","root");
		
			//create callable statement object
			if(con!=null)
				cs=con.prepareCall(CALL_PROCEDURE_QUERY);
			
			if(cs!=null) {
				//set IN Param values
				cs.setString(1,pname1);
				cs.setString(2,pname2);
				
				//call PL/SQL Query
				cs.execute();
				
				//gather ressults from OUT Params
				rs=cs.getResultSet();
				//process the result
				if(rs!=null) {
					while(rs.next()) {
						flag=true;
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));
					}
					if(flag==false)
						System.out.println("No Record Found");
				}
				
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
