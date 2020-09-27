package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest3 {

	public static void main(String[] args) {
		Scanner sc=null;
		int eno=0;
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Employee Number:");
			    eno=sc.nextInt();
			}//if
			
			
			//register JDBC driver
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
			
			//create statement object
			if(con!=null) 
				st=con.createStatement();
			
			//prepare SQL Query
			//select ename,empno,job,sal from emp where empno=7499;
			query="SELECT ENAME,EMPNO,JOB,SAL FROM EMP WHERE EMPNO="+eno;
		    
			//send and execute in DB s/w
			if(st!=null)
				rs=st.executeQuery(query);
			
			//process the SQL Query
			if(rs!=null) {
				if(rs.next()) 
					System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				else
					System.out.println("No records found");
			}//if
			 
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
