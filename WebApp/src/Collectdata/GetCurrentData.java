package Collectdata;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class GetCurrentData extends HttpServlet   {
	public void service(HttpServletRequest req, HttpServletResponse res) {
		try
        {
			ExecutorService executorService = Executors.newFixedThreadPool(4);
            ArrayList<Future<String>> resultList = new ArrayList<>();
            resultList.add(executorService.submit(new Callable<String>() {
				public String call() throws Exception {
					
					try {
			            String command = "wmic logicaldisk get freespace";
			            String result = "";

			            Process process = Runtime.getRuntime().exec(command);
			            BufferedReader reader = new BufferedReader(
			                    new InputStreamReader(process.getInputStream()));
			            reader.readLine();
			            reader.readLine();
			            result = reader.readLine();
			            reader.close();
			            return "{\"Free Space\" :\"" + result+"\"}";

			        } catch (Exception e) {
			            System.out.println(" Something Wrong !!!");
			            e.printStackTrace();
			        }
					return "No result";
					
				}}));		
		
            resultList.add(executorService.submit(new Callable<String>() {
				public String call() throws Exception {
					try {
			            String command = "tasklist";
			            Process process = Runtime.getRuntime().exec(command);
			            BufferedReader reader = new BufferedReader(
			                    new InputStreamReader(process.getInputStream()));
			            String line;
			            Integer counter = -2;
			            while ((line = reader.readLine()) != null) {
			                //System.out.print(line);
			                counter += 1;
			            }
			            reader.close();
			            return "{\"No. of Process\" :\"" + Integer.toString(counter)+"\"}";

			            

			        } catch (Exception e) {
			            System.out.println("Something Wrong !!! ");
			            e.printStackTrace();
			        }
					return "No Result";
				}}));		
			/*	
            resultList.add(executorService.submit(new Callable<String>() {
				public String call() throws Exception {
					try {
						String content;
			            String command = "wmic cpu get loadpercentage";
			            Process process = Runtime.getRuntime().exec(command);
			            BufferedReader reader = new BufferedReader(
			                    new InputStreamReader(process.getInputStream()));
			            reader.readLine();
			            reader.readLine();
			            content =  reader.readLine();
			            reader.close();
			            return "Cpu Utilization :" + content;
			            
			        } catch (Exception e) {
			            System.out.println("Something Wrong !!! ");
			            e.printStackTrace();
			        }
					return "No Result";
				}}));	
            	*/
            
           List<String> myList = new ArrayList<>();
           for (Future<String> s:resultList)
           {
        	while (!s.isDone()) { }
        	myList.add(s.get());
        	}
        	//System.out.println(myList);
        	PrintWriter out = res.getWriter();
        	out.print(myList);
        }
        
        catch (Exception e)
        {
            System.out.println(" Something Wrong ");
            e.printStackTrace();
        }
	}

}
