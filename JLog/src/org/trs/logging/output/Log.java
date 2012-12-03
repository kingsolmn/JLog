/**
* ╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
* ║ ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ ║
* ║ ▒▒▒▒▒▒         All code and information Copyright 2012 Steve Palacios. All rights reserved.         ▒▒▒▒▒▒ ║
* ║ ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ ║
* ╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
*/
package org.trs.logging.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Last changed: $LastChangedDate$
 * @author $Author$
 * @version $Revision$
 */
public class Log {

	private static final boolean DEBUG = true;
	
	private   static final String LOG_FILE_NAME = "";
	private   static final String LOG_FILE_DIR  = "";
	private   static final String LOG_MSG_HEADER = "Log: ";
	
	private   static final String LEVEL_VERBOSE = "V";
	private   static final String LEVEL_DEBUG   = "D";
	private   static final String LEVEL_INFO    = "I";
	private   static final String LEVEL_WARNING = "W";
	private   static final String LEVEL_ERROR   = "E";
	
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
		
//		try {
//			fw = new FileWriter(LOG_FILE_DIR + LOG_FILE_NAME, true);
//			bw = new BufferedWriter(fw);
//			
//			bw.write(LOG_MSG_HEADER + " | W | " + message);
//			bw.close();
//		} catch (IOException e) {
//			if (DEBUG) {
//				e.printStackTrace();
//			}
//		}
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
	private static void out(String level, String message){
		if(DEBUG){
			System.out.println(LOG_MSG_HEADER + " <" + level + "> " + message);
		}
	}

	/**
	 * @param string
	 */
	public static void i(String message) {
		out(LEVEL_INFO, message);
		
	}
}
