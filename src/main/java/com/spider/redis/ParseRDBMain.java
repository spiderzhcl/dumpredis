/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spider.redis;

import com.spider.redis.dump.ParseRDB;
import com.spider.redis.dump.ParseRDB.Entry;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 *
 * @author chunlei
 */
public class ParseRDBMain {

    public static void main(String[] args) {
        final AtomicInteger count = new AtomicInteger();
//        String filePath = "C:\\Users\\chunlei\\Downloads\\dump.rdb";
        String filePath = "d:/tmp/dump_33005.rdb";
        if (args.length >= 1) {
            filePath = args[0];
        }
        String outputfile = "d:/tmp/log.log";
        if (args.length >= 2) {
            outputfile = args[1];
        }
        ParseRDB rdb = new ParseRDB();
        rdb.init(new File(filePath));
        BufferedWriter bw = getOutputFile(outputfile);
        Entry entry = rdb.next();
        try {
            Integer counts = 1;
            while (entry != null) {
            	
                bw.write(entry.toString());
                bw.newLine();
                boolean co=false;
                int start=0;
                while(!co){
                	try {
                        entry = rdb.next();
                        co=true;
                    } catch (Exception e) {
                    	int f=0;
                    	StackTraceElement[] els=e.getStackTrace();
                    	if(els!=null){
                    		f=els.length;
                    		for(StackTraceElement s:els){
                    			String v=s.getClassName()+s.getLineNumber();
                    			System.out.println(":::::::::::::::::"+v);
                    		}
                    	}
                    	System.out.println("------"+f);
                       //e.printStackTrace();
                        if((start++)>10000){
                        	break;
                        }
                    }
                }
                counts++;
                
                if (counts % 10000 == 0) {
                    bw.flush();
                    System.out.println("success flush " + counts);
                }
            }
            System.out.println("total save " + counts);
        } catch (IOException ex) {
        	 ex.printStackTrace();
            Logger.getLogger(ParseRDBMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
        	ex.printStackTrace();
            Logger.getLogger(ParseRDBMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
        	ex.printStackTrace();
            Logger.getLogger(ParseRDBMain.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bw.flush();
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(ParseRDBMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        rdb.close();
    }

    public static Formatter getFormatter() {
        Formatter formater = new Formatter() {
            public String format(LogRecord rec) {
                StringBuilder buf = new StringBuilder(1000);
                buf.append(formatMessage(rec));
                buf.append('\n');
                return buf.toString();
            }
        };

        //                Integer limit = 10 * 1024 * 1024 * 1024;  //1g
//                Logger log = Logger.getLogger(ParseRDBMain.class.getName());
//                FileHandler fileHandler = new FileHandler(outputfile, limit, 10);
//                fileHandler.setFormatter(getFormatter());
//                log.addHandler(fileHandler);
//                log.setUseParentHandlers(false);
//                log.info(entry.toString());
////                System.out.println(entry.toString());
        return formater;
    }

    public static BufferedWriter getOutputFile(String file) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            return bw;
        } catch (IOException ex) {
            Logger.getLogger(ParseRDBMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
