package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PostgreSQLInsertTest {
	private static final String INSERT_QUERY="INSERT INTO PRODUCT VALUES(nextval('PID_SEQ'),?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String pName=null;
		float pPrice=0.0f,pQty=0.0f;	
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Product Name:");
				pName=sc.next(); //gives WOODDESK
				
				System.out.println("Enter product price:");
				pPrice=sc.nextFloat();//gives 4000
				
				System.out.println("Enter Product Qty:");
				pQty=sc.nextFloat();//GIVES 10
				
			}//if
			
		    //register jdbc driver
		  // Class.forName(org.postgresql.Driver);
		   
		    
		   //establish connection
		    con=DriverManager.getConnection("jdbc:postgresql:advjavasql","postgres","root");
		     
		  //create statement
		    if(con!=null) 
		    	ps=con.prepareStatement(INSERT_QUERY);
		  //prepare query
		  //insert into student values(107,'Abhi','KDP',56);
		    
		    //SET VALUES TO QUERY PARAM
		    if(ps!=null) {
		    	ps.setString(1,pName);
		    	ps.setFloat(2,pPrice);
		    	ps.setFloat(3,pQty);
		    	
		    	//send and execute SQL query
		    	count=ps.executeUpdate();
		    }
		    //process the result
		    if(count==0)
		    	System.out.println("Records not inserted");
		    else
		    	System.out.println(+count+" no. of Records  inserted");
		   
		   		
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			//close jdbc objects
			 try {
				 if(ps!=null)
					 ps.close();
			 }
			 catch(SQLException se) {
				  se.printStackTrace();
				 
			 }
			 
			 try {
				 if(con!=null)
					 con.close();
			 }
			 catch(SQLException se) {
				 se.printStackTrace();
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
