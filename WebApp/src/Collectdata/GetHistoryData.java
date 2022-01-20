package Collectdata;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetHistoryData extends HttpServlet  {

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		 {
				ExecutorService executorService = Executors.newFixedThreadPool(3);
	            ArrayList<Future<String>> resultList = new ArrayList<>();
	            resultList.add(executorService.submit(new Callable<String>() {
					public String call() throws Exception {				
						try {
							String line ;
							BigInteger time_diff = BigInteger.valueOf(new Date().getTime()).subtract(new BigInteger(req.getParameter("time1")));
							List<String> myList = new ArrayList<>();
							BufferedReader br = new BufferedReader(new FileReader("C:/Users/anika.babu/Desktop/New folder/Output/Running_Process.txt")); 
							 br.readLine();
							while ((line = br.readLine()) != null) { 
								System.out.println(line);
							    String[] cols = line.split("\\|"); 
							    //if ( Integer.parseInt(cols[1]) > time_diff ) {
							    if ((new BigInteger(cols[0]).compareTo(time_diff)) >=0) {
							    	//myList.add(cols[0] + " : " +cols[1] );
							    	myList.add("{\"Time in milliseconds\" :  \"" + cols[0] + "\" , \"Value\" : \"" +cols[1]+ "\"}" );

					            }	    
							    
							} 
						br.close();
						String listString = String.join(", ", myList);
						return "No. of running Process : " +listString;
				        } catch (Exception e) {
				            System.out.println(" Something Wrong !!!");
				            e.printStackTrace();
				        }
						return "No result";
						
					}}));		
			
	            resultList.add(executorService.submit(new Callable<String>() {
					public String call() throws Exception {
						
						try {	
							String line ;
							BigInteger time_diff = BigInteger.valueOf(new Date().getTime()).subtract(new BigInteger(req.getParameter("time1")));
							List<String> myList1 = new ArrayList<>();
							BufferedReader br1 = new BufferedReader(new FileReader("C:/Users/anika.babu/Desktop/New folder/Output/Free_Memory.txt")); 
							br1.readLine();
							line = "";
							while ((line = br1.readLine()) != null) { 
								System.out.println(line);
							    String[] cols = line.split("\\|"); 
							    //if ( Integer.parseInt(cols[1]) > time_diff ) {
							    if ((new BigInteger(cols[0]).compareTo(time_diff)) >=0) {
							    	//myList.add(cols[0] + " : " +cols[1] );
							    	myList1.add("{\"Time in milliseconds\" :  \"" + cols[0] + "\" , \"Value\" : \"" +cols[1]+ "\"}" );

					            }	    
							    
							} 
							String listString = String.join(", ", myList1);
							br1.close();
							return "Free Memory  : " +listString;
				           

				        } catch (Exception e) {
				            System.out.println(" Something Wrong !!!");
				            e.printStackTrace();
				        }
						return "No result";
						
					}}));		
			       
	    //---------------------
	            List<String> myList_r = new ArrayList<>();
	            List<String> myList_f = new ArrayList<>();
	            String requiredString = "";
	            for (Future<String> s:resultList)
	            {
	         	while (!s.isDone()) { }
	         	try {
	         		if (s.get().contains("No. of running")) 
	         		{ requiredString = s.get().substring(s.get().indexOf("{") ,s.get().lastIndexOf("}")+1);

	         		myList_r.add( requiredString );
	         		}
	         		else
	         		{ requiredString = s.get().substring(s.get().indexOf("{") ,s.get().lastIndexOf("}")+1);

	         		myList_f.add(  requiredString  );
	         		}
					//System.out.println(myList);
		         	
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         	}
	            List<String> final_List = new ArrayList<>();
	            final_List.add("{\"No. of running process\" :"+ myList_r+"}");
	            final_List.add("{\"Free Memory Space\" :"+ myList_f+"}");
	            PrintWriter out = res.getWriter();
	            
	         	out.print(final_List);	        
	
	         	
	
		}
}
}
