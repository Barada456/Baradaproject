package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public final class PsBLOBRetrieveMySQL {
	private static final String DATE_RETREIVE_QUERY="SELECT ARTISTID,ARTISTNAME,ARTISTADDRS,ARTISTINCOME,PHOTO,VIDEO FROM ARTIST_INFO WHERE ARTISTID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int id=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int aId=0;
		String aName=null;
		String aAddrs=null;
		float aIncome=0.0f;
		InputStream photoIS=null,videoIS=null;
		OutputStream photoOS=null,videoOS=null;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Artist Id:");
				id=sc.nextInt();
			}//if
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///advjavasql","root","root");
			
			//create preparedstatement
			if(con!=null)
				ps=con.prepareStatement(DATE_RETREIVE_QUERY);
			
			//SET VALUES
			if(ps!=null) {
				ps.setInt(1, id);
				
				//execute query
				rs=ps.executeQuery();
			}
			if(rs!=null) {
				while(rs.next()) {
					aId=rs.getInt(1);
					aName=rs.getString(2);
					aAddrs=rs.getString(3);
					aIncome=rs.getFloat(4);
					photoIS=rs.getBinaryStream(5);
					videoIS=rs.getBinaryStream(6);
					
					photoOS=new FileOutputStream("new_photo.jpg");
					videoOS=new FileOutputStream("new_video.mp4");
					
					IOUtils.copy(photoIS, photoOS);
					IOUtils.copy(videoIS, videoOS);
					
					System.out.println(aId+" "+aName+" "+aAddrs+" "+aIncome);
					System.out.println("Photo and video Copied to Destination File...Kindly check");
					
				}//while
			}//if
			else
				System.out.println("Record Not Found...Kindly insert the record first");
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
	    		if(photoIS!=null)
	    			photoIS.close();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    	try {
	    		if(videoIS!=null)
	    			videoIS.close();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	try {
	    		if(photoOS!=null)
	    			photoOS.close();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    	try {
	    		if(videoOS!=null)
	    			videoOS.close();
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

	}

}
