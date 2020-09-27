package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PSBLOBInsertTestOracle {
	
	/*
	 * CREATE TABLE "HR"."ARTIST_INFO" ( "ARTISTID" NUMBER(*,0) NOT NULL ENABLE,
	 * "ARTISTNAME" VARCHAR2(20 BYTE), "COLUMN1" VARCHAR2(20 BYTE), "COLUMN2"
	 * FLOAT(126), "PHOTO" BLOB, "COLUMN3" BLOB, CONSTRAINT "ID" PRIMARY KEY
	 * ("ARTISTID"))
	 */
	private static final String ARTIST_INSERT_QUERY="INSERT INTO ARTIST_INFO VALUES(ARTIST_ID_SEQ.NEXTVAL,?,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String aName=null,aAddrs=null,photoLocation=null,videoLocation=null;
		float aIncome=0.0f;
		InputStream photoIS=null,videoIS=null;
		Connection con=null;
		PreparedStatement  ps=null;
		int count=0;
		//read inputs
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				 System.out.println("Enter Artist Name: ");
				 aName=sc.next();
				 System.out.println("Enter Artist Addrs:");
				 aAddrs=sc.next();
				 System.out.println("Enter Artist income:");
				 aIncome=sc.nextFloat();
				 System.out.println("Enter Artist Photo:");
				 photoLocation=sc.next();
				 System.out.println("Enter Artist Video:");
				 videoLocation=sc.next();	
			}//if
			//CREATE INPUT STREAMS REPRESENTING PHOTO FILE AND VIDEO FILE
			photoIS=new FileInputStream(photoLocation);
			videoIS=new FileInputStream(videoLocation);
			
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
			//create prepared statement
			if(con!=null)
				ps=con.prepareStatement(ARTIST_INSERT_QUERY);
			//set values to query param
			if(ps!=null) {
				ps.setString(1,aName);
				ps.setString(2,aAddrs);
				ps.setFloat(3,aIncome);
				ps.setBinaryStream(4,photoIS);
				ps.setBinaryStream(5,videoIS);
				
				
				//execute query
				count=ps.executeUpdate();
			}
			if(count!=0)
				System.out.println("Record Inserted");
			else
				System.out.println("Record not inserted");
			
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
		    		if(photoIS!=null)
		    			photoIS.close();
		    	}
		    	catch(Exception e) {
		    		e.printStackTrace();
		    	}
		    	
		    	try {
		    		if(videoIS!=null)
		    			videoIS.close();
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
		
	}//main(-)
//class
}
