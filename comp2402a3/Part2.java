package comp2402a3;
// Thanks to Pat Morin for this file!

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.ArrayDeque;

public class Part2 {

	/**
	 * Your code goes here - see Part0 for an example
   * @param x the number of lines to read in
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(int x, BufferedReader r, PrintWriter w)
      throws IOException {
      //TODO: Your code goes here -- see Assignment 2 for examples
			//[apple, banana, orange, banana, pear, peach, plum, peach, avocado]

			Deque<String> d = new ArrayDeque<>(x+1);
			boolean f = true;

			// First read in the file, store last x lines in deque
			for( String line = r.readLine(); line != null; line = r.readLine() ) {
				d.addLast(line);
				// If we now have x+1 lines, remove the oldest one in O(1) time
				if( d.size() > x ) {
					d.removeFirst();
				}
				if( d.size() == x ) {
					for(String el: d){
						if(line.compareTo(el) < 0){
							f = false;
							break;
						}
					}
					if( f == true ){
						w.println(line);
					}
				}
				f = true;
			}
  }

  /**
   * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
   * and System.out or from filenames specified on the command line, then call doIt.
   * @param args
   */
  public static void main(String[] args) {
    try {
      BufferedReader r;
      PrintWriter w;
      int x;
      if (args.length == 0) {
        x = 3;
        r = new BufferedReader(new InputStreamReader(System.in));
        w = new PrintWriter(System.out);
      } else if( args.length == 1) {
        x = Integer.parseInt(args[0]);
        r = new BufferedReader(new InputStreamReader(System.in));
        w = new PrintWriter(System.out);
      } else if (args.length == 2) {
        x = Integer.parseInt(args[0]);
        r = new BufferedReader(new FileReader(args[1]));
        w = new PrintWriter(System.out);
      } else {
        x = Integer.parseInt(args[0]);
        r = new BufferedReader(new FileReader(args[1]));
        w = new PrintWriter(new FileWriter(args[2]));
      }
      long start = System.nanoTime();
      doIt(x, r, w);
      w.flush();
      long stop = System.nanoTime();
      System.out.println("Execution time: " + 1e-9 * (stop-start));
    } catch (IOException e) {
      System.err.println(e);
      System.exit(-1);
    }
  }
}
