package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MysqlInsertTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int sno=0;
		String name=null,addrs=null;
		float avg=0.0f;	
		Connection con=null;
		Statement st=null;
		int count=0;
		String query=null;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Student Number: ");
				sno=sc.nextInt(); //gives 1001
				
				System.out.println("Enter Student Name:");
				name=sc.next(); //gives ramesh
				
				System.out.println("Enter Studnet address:");
				addrs=sc.next();//gives delhi
				
				System.out.println("Enter average:");
				avg=sc.nextFloat();//fives 56.77
				
			}//if
			//convert input values as required for the end user
			name="'"+name+"'"; //gives 'ramesh'  
		    addrs="'"+addrs+"'"; //gives 'delhi'
		    
		    
		    //register jdbc driver
		    Class.forName("com.mysql.cj.jdbc.Driver");
		   
		    
		   //establish connection
		    con=DriverManager.getConnection("jdbc:mysql:///advjavasql","root","root");
		     
		  //create statement
		    if(con!=null) 
		    	st=con.createStatement();
		  //prepare query
		  //insert into student values(107,'Abhi','KDP',56);
		    query="INSERT INTO STUDENT VALUES("+sno+","+name+","+addrs+","+avg+")";
		    System.out.println(query);
		    
		    
		  //send and execute SQL query
		    if(st!=null )
		        count=st.executeUpdate(query);
		    
		  //process the result
		    if(count==0)
		    	System.out.println("Records not inserted");
		    else
		    	System.out.println(+count+" no. of Records  inserted");
		   
		   		
		}//try
		catch(SQLException se) {
			System.out.println(se.getErrorCode());
			
			if(se.getErrorCode()==1)
				 System.out.println("Dont give multiple entries for primary key constraint..");
			 else if(se.getErrorCode()==12899)
				 System.out.println("You can't enter values more than column size");
			 else if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				 System.out.println("SQL query Syntax Error");
			 else
				 System.out.println("Unknown JDBC problem");
			 se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			//close jdbc objects
			 try {
				 if(st!=null)
					 st.close();
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
