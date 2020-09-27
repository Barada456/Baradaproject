package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int sno=0;
		String newSadd=null;
		String newName=null;
		String query=null;
		float newAvg=0.0f;
		Connection con=null;
		Statement st=null;
		int count=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				//give inputs
				System.out.println("Enter Existing Sno:");
			    sno=sc.nextInt(); //gives 101
			    
			    System.out.println("Enter New Name");
			    newName=sc.next();//gives jogulu
			    
			    System.out.println("Enter New address");
			    newSadd=sc.next();//gives kdp
			    
			    System.out.println("Enter New avg");
			    newAvg=sc.nextFloat();//gives 78
			 
			}//if
			//converts input values as required for SQL query
			newName="'"+newName+"'";
			newSadd="'"+newSadd+"'";
			
			//register jdbc drivers
			//Class.forName("oracle.jdbc.driver.OracleDriver");
		    
			//esstablish connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
			
			//create JDBC connection object
			if(con!=null) 
				st=con.createStatement();
			
			//prepare sql query
			//update student set sname='jogulu',sadd='kdp',avg=78 where sno=101;
			query="UPDATE STUDENT SET SNAME="+newName+",SADD="+newSadd+",AVG="+newAvg+" WHERE SNO="+sno;
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
