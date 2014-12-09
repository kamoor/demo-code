package com.demo.asycnmathsolver;

import java.util.LinkedList;

import com.demo.asycnmathsolver.threads.ReaderThread;

/**
 * Hello world!
 *
 */
public class MathSolver 
{
    public static void main( String[] args )
    {
    	try{
    		  System.out.println("About to process file "+ args[0]);
    		  String outputFile = args[0] + "_output.txt";
    		  new Thread(new ReaderThread(args[0], outputFile, new LinkedList<String>())).start();
    		  System.out.println("Process started.");
    	}catch(Exception e){
    			System.out.println("Error while running this code " + e.getMessage());
    			System.out.println("Run this code with absolute file name given a first argument: Example java MathSovler /tmp/test-file.txt");
    	}
     
       
    }
}
