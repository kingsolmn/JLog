/**
* ╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
* ║ ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ ║
* ║ ▒▒▒▒▒▒         All code and information Copyright 2013 Steve Palacios. All rights reserved.         ▒▒▒▒▒▒ ║
* ║ ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ ║
* ╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
*/
package org.trs.logging.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;

/**
 * Last changed: $LastChangedDate$
 * @author $Author$
 * @version $Revision$
 */
public class Log {

//	private   static final boolean DEBUG = true;
	
	private   static final int     THREAD_NAME_LENGTH = 20;
	
	private   static final String  LOG_FILE_NAME = "TRSLogFile.log";
	private   static final String  LOG_FILE_DIR  = System.getProperty("user.home") + "\\";
//	private   static final String  LOG_MSG_HEADER = "Log: ";	
	private   static final String  LEVEL_VERBOSE = "V";
	private   static final String  LEVEL_DEBUG   = "D";
	private   static final String  LEVEL_INFO    = "I";
	private   static final String  LEVEL_WARNING = "W";
	private   static final String  LEVEL_ERROR   = "E";
	
	
	private   static       FileWriter fw;
	private   static       BufferedWriter bw;
	
	
	/**
	 * 
	 * @param The message to write.
	 */
	public static void v(String message){
		out(LEVEL_VERBOSE, message);
		
	}
	
	/**
	 * 
	 * @param The message to write.
	 */
	public static void d(String message) {
		out(LEVEL_DEBUG, message);
		
	}
	
	/**
	 * Records a message that is to be displayed when executing and a warning level message should be generated.
	 * @param The message to write.
	 */
	public static void w(String message){
		out(LEVEL_WARNING, message);
	}
	
	/**
	 * 
	 * @param The message to write.
	 */
	public static void e(String message){
		out(LEVEL_ERROR, message);
		
	}
	
	/**
	 * Writes the given message to the "System.out" stream if the "DEBUG" flag is set in the "Constants" file.
	 * @param The message to write.
	 */
	private static void out(final String level, final String message){
		final String thread = thread();
		if(level == LEVEL_ERROR || level == LEVEL_WARNING){
			System.err.println(time() + " [" + thread + "] " + " <" + level + "> " + message);
		}else{
			System.out.println(time() + " [" + thread + "] " + " <" + level + "> " + message);
		}
		
		(new Thread("TRS LogWriter"){
			public void run(){
				try {
					fw = new FileWriter(LOG_FILE_DIR + LOG_FILE_NAME, true);
					bw = new BufferedWriter(fw);

					try {
						bw.write(time() + " [" + thread + "] " + " <" + level + "> " + message);
					} catch (Exception e) {
						fw = new FileWriter(LOG_FILE_DIR + LOG_FILE_NAME, true);
						bw = new BufferedWriter(fw);
						try {
							bw.write(time() + " [" + thread + "] " + " <" + level + "> " + message);
						} catch (Exception e2) {}
					}
					try {
						bw.newLine();
					} catch (Exception e) {
						fw = new FileWriter(LOG_FILE_DIR + LOG_FILE_NAME, true);
						bw = new BufferedWriter(fw);
						try {
							bw.newLine();
						} catch (Exception e2) {}
					}
					bw.close();
				} catch (Exception e) {
					out(level, message);
				}
				return;
			}
		}).start();
	}

	/**
	 * @param string
	 */
	public static void i(String message) {
		out(LEVEL_INFO, message);
		
	}
	
	private static String thread(){
		char[] rawThreadName = Thread.currentThread().getName().toCharArray();
		String outPut = "";
		int threadNameLength = Thread.currentThread().getName().length();
		for(int i = 0; i < THREAD_NAME_LENGTH; i++){
			int temp = outPut.length();
			if (temp < threadNameLength) {
				outPut += rawThreadName[i];
			}else{
				outPut += " ";
			}
		}
		
		return outPut;	
//		return Thread.currentThread().getName();
	}
	
	private static String time(){
		final DateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss.S "); 
		Date _date = new Date();  
		
		String curTime = dateTimeFormat.format(_date);
//		System.out.println("curTime.length() = " + curTime.length());
		while(curTime.length() <= 22){
//			System.out.println("TRS Logging: Adding a zero to the end.");
			curTime += "0";
		}
		
		curTime = "[" + curTime + "] ";
		return curTime;
	}
}
