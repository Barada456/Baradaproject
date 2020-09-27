package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableRSTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
			
			//create statement object
			if(con!=null) {
			    st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			    					   ResultSet.CONCUR_UPDATABLE);
			}
			//create resultset object
			if(st!=null) {
				rs=st.executeQuery("SELECT SNAME,SADD,AVG FROM STUDENT");
			}
		    
			if(rs!=null) {
				System.out.println("Top to Bottom:");
				System.out.println();
				while(rs.next()) {
					System.out.println(rs.getRow()+"--->"+rs.getString(1)+"    "+rs.getString(2)+"    "+rs.getFloat(3));
				}
				System.out.println();
				System.out.println(" Bottom to Top:");
				System.out.println();
				while(rs.previous()) {
					System.out.println(rs.getRow()+"--->"+rs.getString(1)+"     "+rs.getString(2)+"    "+rs.getFloat(3));
				}
				System.out.println();
				rs.first();
				System.out.println(rs.getRow()+"--->"+rs.getString(1)+"     "+rs.getString(2)+"    "+rs.getFloat(3));
				System.out.println();
				rs.last();
				System.out.println(rs.getRow()+"--->"+rs.getString(1)+"     "+rs.getString(2)+"    "+rs.getFloat(3));
				
				System.out.println();
				rs.absolute(5);
				System.out.println(rs.getRow()+"--->"+rs.getString(1)+"     "+rs.getString(2)+"    "+rs.getFloat(3));
				
				System.out.println();
				rs.absolute(-5);
				System.out.println(rs.getRow()+"--->"+rs.getString(1)+"     "+rs.getString(2)+"    "+rs.getFloat(3));
				
				System.out.println();
				rs.absolute(3);
				System.out.println(rs.getRow()+"--->"+rs.getString(1)+"     "+rs.getString(2)+"    "+rs.getFloat(3));
				
				System.out.println();
				rs.absolute(-3);
				System.out.println(rs.getRow()+"--->"+rs.getString(1)+"     "+rs.getString(2)+"    "+rs.getFloat(3));
				
				System.out.println();
				rs.relative(2);
				System.out.println(rs.getRow()+"--->"+rs.getString(1)+"     "+rs.getString(2)+"    "+rs.getFloat(3));
				
				System.out.println();
				rs.relative(-4);
				System.out.println(rs.getRow()+"--->"+rs.getString(1)+"     "+rs.getString(2)+"    "+rs.getFloat(3));
				
				
				
				
			}
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
          
        }//finally

	}//metthod

}//class
