package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest2 {

	public static void main(String[] args) {
		Scanner sc=null;
		String query=null;
		Connection con=null;
		Statement st=null;
		int incSal=0;
		int count=0;
		float hikeSal=0.0f;
		try {
			sc=new Scanner(System.in);
			System.out.println("Enter How much percentage of salary you want to Hike: ");
			incSal=sc.nextInt();
			hikeSal=(float)incSal/100;
			
			//register jdbc drivers
			//Class.forName("oracle.jdbc.driver.OracleDriver");
		    
			//esstablish connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
			
			//create JDBC connection object
			if(con!=null) 
				st=con.createStatement();
			
			//prepare sql query
			// update emp set sal=sal+(sal*0.2) where job='SALESMAN';
			query="UPDATE EMP SET SAL=sal+(sal*"+hikeSal+") WHERE JOB='SALESMAN'";
			System.out.println(query);
			
			//send and execute query in sql s/w
			count=st.executeUpdate(query);
			
			if(count==0)
				System.out.println("0 Records updated");
			else
				System.out.println(+count+" No of records updated");
			
		
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
			if(st!=null)
				st.close();
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
