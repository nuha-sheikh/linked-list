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
import java.util.List;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;


public class Part1 {

	/**
	 * Your code goes here - see Part0 for an example
   * @param x the number of lines to read in
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(int x, BufferedReader r, PrintWriter w)
      throws IOException {

			Deque<String> d = new ArrayDeque<>(x+1);
			SortedSet<String> ts = new TreeSet<String>();

      for( String line = r.readLine(); line != null; line = r.readLine() ) {
				if(!d.contains(line)){ //O(n), set (logx time), //if x = 0 (break;), x
					d.addLast(line);
					ts.add(d.getLast());

	        if( d.size() > x ) {
						ts.remove(d.removeFirst());
	        }
				}
      }
			//if(a.contins(line)){continue;}

			while( ts.size() != 0 ){
				w.println( ts.first() );
				ts.remove(ts.first());
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
