package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PostgresSQLSelectTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		try {
			//register JDBC driver
			 Class.forName("org.postgresql.Driver");
			
			//establish connection
			con=DriverManager.getConnection("jdbc:postgresql:advjavasql","postgres","root");
			
			//create statement object
			if(con!=null) 
				st=con.createStatement();
			
			//prepare SQL Query
			//select ename,empno,job,sal from emp where empno=7499;
			query="SELECT PID,PNAME,PRICE,QTY FROM PRODUCT";
		    
			//send and execute in DB s/w
			if(st!=null)
				rs=st.executeQuery(query);
			
			//process the SQL Query
			if(rs!=null) {
				while(rs.next()) 
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));
			}//if
			else
				System.out.println("No records found");
			 
		}//try
		catch(SQLException se) {  //known exceptions
			se.printStackTrace();
		}
		catch(Exception e) {      //unknown exceptions
			e.printStackTrace();
		}
        finally {
        	//close JDBC objects	
        	try { 
        	  if(rs!=null)
        		rs.close();
        	}//try
        	catch(SQLException se) {
        		se.printStackTrace();
        	}
        
        	try { 
          	  if(st!=null)
          		st.close();
          	}//try
        	catch(SQLException se) {
          		se.printStackTrace();
        	}
          
        	try { 
          	  if(con!=null)
              con.close();
          	}//try
        	catch(SQLException se) {
          		se.printStackTrace();
        	}
          	
        	try { 
          	  if(sc!=null)
          		sc.close();
          	}//try
        	catch(Exception e) {
          		e.printStackTrace();
          	}
        }//finally

	}//main()

}//class
