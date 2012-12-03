/**
* ╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
* ║ ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ ║
* ║ ▒▒▒▒▒▒ All code and information Copyright 2012 Crowning Touch Moving Services. All rights reserved. ▒▒▒▒▒▒ ║
* ║ ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ ║
* ╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
*/

package org.trs.logging.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Last changed: $LastChangedDate$
 * @author $Author$
 * @version $Revision$
 */
public class LogFile {
	
	public static void main(String[] arg0){
		writeToFile("Hello");
	}
	
	public static void writeToFile(String line){
		FileWriter fw = null;
		BufferedWriter bw = null;
		Date now = new Date();
		Format formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.S");
		String curTime = "";
		
		curTime = formatter.format(now);
		System.out.println("curTime.length() = " + curTime.length());
		while(curTime.length() <= 22){
			System.out.println("Adding a zero to the end.");
			curTime += "0";
		}
		
		curTime = "[" + curTime + "] ";
		
		try{
			fw = new FileWriter("FileName.log", true);
			bw = new BufferedWriter(fw);
			bw.newLine();
			bw.write(curTime + line);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
