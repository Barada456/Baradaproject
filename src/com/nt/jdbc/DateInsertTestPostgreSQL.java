package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsertTestPostgreSQL{
	private static final String INSERT_DATE_QUERY="INSERT INTO PERSON_DATE_TAB VALUES(nextval('PID_SEQ'),?,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
	    String pname=null,padd=null,dob=null,dom=null,doj=null;
	    Connection con=null;
	    PreparedStatement ps=null;
	    SimpleDateFormat sdf1=null;
	    SimpleDateFormat sdf2=null;
	    java.util.Date udob=null;
	    java.util.Date udom=null;
	    java.sql.Date sdob=null;
	    java.sql.Date sdom=null;
	    java.sql.Date sdoj=null;
	    int count=0;
	    try {
			sc=new Scanner(System.in);
			System.out.println("Enter Person Name::");
			pname=sc.next();
			System.out.println("Enter Person Address::");
			padd=sc.next();
			System.out.println("Enter Person DOB(dd-MM-yyyy)::");
			dob=sc.next();
			System.out.println("Enter Person DOM(MM-dd-yyyy)::");
			dom=sc.next();
			System.out.println("Enter Person DOJ(yyyy-MM-dd)::");
			doj=sc.next();
		
		
		//CONVERT STRING VALUES TO java.util.Date format
		sdf1=new SimpleDateFormat("dd-MM-yyyy");
		if(sdf1!=null) 
			udob=sdf1.parse(dob);
		
		sdf2=new SimpleDateFormat("dd-MM-yyyy");
		if(sdf2!=null) 
			udom=sdf2.parse(dom);
		
		//convert java.util.Date class objects to java.sql.Date class objs
		if(udob!=null)
			sdob=new java.sql.Date(udob.getTime());
		
		if(udom!=null)
			sdom=new java.sql.Date(udom.getTime());
		
		//convert string date value(yyyy-MM-dd)
		sdoj=java.sql.Date.valueOf(doj);
		
		//load jdbc drivers
		/*
		 * Class.forName("com.mysql.cj.jdbc.driver");
		*/
		
		//Establish Connection
		con=DriverManager.getConnection("jdbc:postgresql:advjavasql","postgres","root");
		
		//create statement objects
		if(con!=null) {
			ps=con.prepareStatement(INSERT_DATE_QUERY);
		}
		
		if(ps!=null) {
			ps.setString(1, pname);
			ps.setString(2,padd);
			ps.setDate(3,sdob);
			ps.setDate(4,sdom);
			ps.setDate(5,sdoj);
		}
		//execute the query
		if(ps!=null)
			count=ps.executeUpdate();
		
		//process the result
		if(count==0)
			System.out.println("Record not inserted");
		else
			System.out.println(count+"Record inserted..");
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
