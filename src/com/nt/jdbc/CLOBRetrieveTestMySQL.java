package com.nt.jdbc;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class CLOBRetrieveTestMySQL {
	private static final String CLOB_RETRIEVE_QUERY="SELECT REGNO,NAME,ADDRS,QLFY,RESUME FROM NAUKRI_PROFILE_INFO WHERE REGNO=?";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		
		String aName=null,aAddrs=null,aQlfy=null;
		int aReg=0;
		Reader aResume=null;
		Writer writer=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int regNo=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Applicant Reg No:");
				regNo=sc.nextInt();
				
			}//if
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///advjavasql","root","root");
			
			
			//create prepared statement
			if(con!=null)
			ps=con.prepareStatement(CLOB_RETRIEVE_QUERY);
			
			//SET VALUES
			if(ps!=null) {
				ps.setInt(1,regNo);	
				
				//execute query
				rs=ps.executeQuery();
			} 
			
			if(rs!=null) {
				if(rs.next()) {
			    aReg=rs.getInt(1);
				aName=rs.getString(2);
				aAddrs=rs.getString(3);
				aQlfy=rs.getString(4);
				aResume=rs.getCharacterStream(5);
				
				System.out.println(aReg+" "+aName+" "+aAddrs+" "+aQlfy);
				
				//create writer stream pointing to an file
				if(aResume!=null)
				writer=new FileWriter("new_resume.text");
				//copy reader resume file to writer destination file
				if(aResume!=null && writer!=null) {
					IOUtils.copy(aResume, writer);
					System.out.println("Resume Retrieved Succesfully");
				}//if
			 }//if
				else
					System.out.println("Record Not Found");
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
	    		if(rs!=null)
	    			rs.close();
	    	}
	    	catch(SQLException se) {
	    		se.printStackTrace();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
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
	    	
	    	try {
	    		if(aResume!=null)
	    			aResume.close();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    	try {
	    		if(writer!=null)
	    			writer.close();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }//finally
	}//main

}//class
