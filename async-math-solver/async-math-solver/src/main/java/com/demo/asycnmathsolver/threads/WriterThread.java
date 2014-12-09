package com.demo.asycnmathsolver.threads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

/**
 * This thread any content to a file.
 */
public class WriterThread implements Runnable, IMathSolver {

	Queue<String> queue;
	BufferedWriter writer;
	String file;

	public WriterThread(String file, Queue<String> queue) throws IOException {
		this.writer = new BufferedWriter(new FileWriter(file));
		this.file = file;
		this.queue = queue;

	}

	public void run() {

		try {
			String str = null;
			while (!END_OF_Q.equals(str = queue.poll())) {
				if (str == null) {
					continue;
				} else {
					writer.write(str);
					writer.write("\n");
					writer.flush();
				}

			}
			writer.flush();
			 System.out.println("Done. Answer is in "+ file);
		    	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
