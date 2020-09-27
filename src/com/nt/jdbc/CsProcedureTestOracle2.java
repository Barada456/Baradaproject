package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureTestOracle2 {
private static final String CALL_PROCEDURE_QUERY="{CALL P_getEmpDetails_By_EmpNo(?,?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int eNo=0;
		Connection con=null;
		CallableStatement cs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Emp Number: ");
				eNo=sc.nextInt();
		    }
			
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
			
			//create callable statement object
			if(con!=null)
			cs=con.prepareCall(CALL_PROCEDURE_QUERY);
			
			//set OUT params as JDBC Types
			if(cs!=null) {
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.INTEGER);
			}
			if(cs!=null)
				cs.setInt(1, eNo);
			
			//call PL/SQL procedure
			if(cs!=null)
				cs.execute();
			
			//gather results
			if(cs!=null) {
				System.out.println("Emp Name : "+cs.getString(2));
				System.out.println("Emp JOB : "+cs.getString(3));
				System.out.println("Emp Salary : "+cs.getInt(4));
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
