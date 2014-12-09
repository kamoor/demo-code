package com.demo.asycnmathsolver.threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ReaderThread implements Runnable, IMathSolver {

	String file;
	String outputFile;
	Queue<String> queue;
	ScriptEngine engine; 
	 

	public ReaderThread(String file, String oFile, Queue<String> queue) {
		this.file = file;
		this.outputFile = oFile;
		this.queue = queue;
		this.engine = new ScriptEngineManager().getEngineByName("JavaScript");
	}

	public void run() {

		BufferedReader reader = null;
		try {
			String line;
			reader = new BufferedReader(new FileReader(new File(file)));
			new Thread(new WriterThread(outputFile, queue)).start();
			while ((line = reader.readLine()) != null) {
					String result = eval(line);
					if(result != null){
						queue.add(result);
					}
					
			}
			queue.add(END_OF_Q);

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private String eval(String str){
		try{
			return str + " = " + engine.eval(str).toString();
		}catch(ScriptException e){
			return str + " is not a valid expression ";
		}
		
    } 

}
