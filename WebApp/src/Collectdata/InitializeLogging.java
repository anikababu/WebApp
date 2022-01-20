package Collectdata;

import java.util.Date;
import java.util.Timer;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitializeLogging extends HttpServlet {
	
	public void init() throws ServletException
    {
		 Date d=  new Date();
          System.out.println("----------");
          System.out.println("----------Example Initialized successfully ----------");
          System.out.println("----------");
          Timer time1 = new Timer(); // Instantiate Timer Object
          ScheduledTask_freespace st = new ScheduledTask_freespace(); // Instantiate SheduledTask class
  		  time1.schedule(st, d, 120000); 
  		  Timer time2 = new Timer(); 
  		  ScheduledTask_runningtask rt = new ScheduledTask_runningtask(); 
		  time2.schedule(rt, d, 180000); 
  		  
    }
	
	
}
