package com.nt.jdbc;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBInsertTestOracle {
	private static final String CLOB_INSERT_QUERY="INSERT INTO NAUKRI_PROFILE_INFO(REGNO,NAME,ADDRS,QLFY,RESUME) VALUES(NAUKRI_PROFILE_SEQ.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String aName=null,aAddrs=null,aQlfy=null,aResume=null;
		Connection con=null;
		Reader aReader=null;
		PreparedStatement ps=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Applicant Name:");
				aName=sc.next();
				System.out.println("Enter Applicant Addrs:");
				aAddrs=sc.next();
				System.out.println("Enter Applicant Qualifications:");
				aQlfy=sc.next();
				System.out.println("Enter Applicant Resume Location:");
				aResume=sc.next();
			}//if
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
			
			//CREATE READER STREAM REPRESENTING TEXT FILE DATA
			aReader=new FileReader(aResume);
			  
			//create prepared statement
			if(con!=null)
			ps=con.prepareStatement(CLOB_INSERT_QUERY);
			
			//SET VALUES
			if(ps!=null) {
				ps.setString(1,aName);	
				ps.setString(2,aAddrs);
				ps.setString(3,aQlfy);
				ps.setCharacterStream(4,aReader);
				
				//execute query
				count=ps.executeUpdate();
			} 
			if(count==0)
				System.out.println("Records not found");
			else
				System.out.println("Records inserted");
			
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
	    		if(ps!=null)
	    			ps.close();
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
