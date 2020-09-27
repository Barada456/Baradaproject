package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsSelectTest3 {
    private static final String EMP_COUNT_QUERY="SELECT ENAME,EMPNO,JOB,SAL FROM EMP WHERE EMPNO=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int eno=0;
		Connection con=null;
		
		
		ResultSet rs=null;
		PreparedStatement ps=null;
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
			
			//create preparedstatement object
			if(con!=null) 
				ps=con.prepareStatement(EMP_COUNT_QUERY);
			
			//prepare SQL Query
			//select ename,empno,job,sal from emp where empno=7499;
			
		    
			//send and execute in DB s/w
			if(ps!=null) {
				ps.setInt(1,eno);
				rs=ps.executeQuery();
			}
				
			
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
          	  if(ps!=null)
          		ps.close();
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
