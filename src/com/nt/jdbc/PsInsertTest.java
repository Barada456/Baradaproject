package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTest {
    private static final String INSERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";
	public static void main(String[] args) {		
		Scanner sc=null;
		int count=0;
		Connection con=null;
		PreparedStatement ps=null;
		int sno=0;
		String sname=null;
		String sadd=null;
		float avg=0.0f;
		int result=0;
		//read inputs
		try{
			sc=new Scanner(System.in);
			if(sc!=null) {
			System.out.println("Enter students count:");
			count=sc.nextInt();
		    }
			//register jdbc drivers
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
			
			//create prepared statement object having given query as precompiled sql query
			if(con!=null)
				ps=con.prepareStatement(INSERT_STUDENT_QUERY);
			
			//read each student details and set them precompiled param values for multiple times 
			if(ps!=null && sc!=null) {
				for(int i=1;i<=count;++i) {
						System.out.println("Enter "+i +" student details::");
						System.out.println("Student Number:");
						sno=sc.nextInt();
						System.out.println("Student Name:");
						sname=sc.next();
						System.out.println("student address");
						sadd=sc.next();
						System.out.println("student avg");
						avg=sc.nextFloat();
						
						//set each student details to query param values
						ps.setInt(1,sno);
						ps.setString(2,sname);
						ps.setNString(3, sadd);
						ps.setFloat(4,avg);
						
						//execute the query
						result=ps.executeUpdate();
						//process the result
						if(result==0)
							System.out.println(i+" student details are not inserted");
						else
							System.out.println(i+" student details are  inserted");
							
				}//for
			}//if
		}//try
		catch(SQLException se){
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
