package Collectdata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.TimerTask;

public class ScheduledTask_freespace  extends TimerTask {
	 
	    @Override
	    public void run() {
	        try {
	        	
	        	String content = "";
	        	
	            String command = "wmic logicaldisk get freespace";

	            Process process = Runtime.getRuntime().exec(command);
	            BufferedReader reader = new BufferedReader(
	                    new InputStreamReader(process.getInputStream()));
	            reader.readLine();
	            reader.readLine();
	            content = String.valueOf(new Date().getTime()) + "|" + reader.readLine();
	            //System.out.println( ">>Memory Free Space :: " + reader.readLine());
	            reader.close();
	            BufferedWriter f_writer= new BufferedWriter(new FileWriter(
                    "C:/Users/anika.babu/Desktop/New folder/Output/Free_Memory.txt",true));
	            f_writer.newLine();
	            f_writer.write(content);
	            f_writer.close();
	            
	        } catch (Exception e) {
	            System.out.println("U r Doing Something Wrong ");
	            e.printStackTrace();
	        }
	    }
}
