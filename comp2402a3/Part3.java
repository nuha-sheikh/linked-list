package comp2402a3;
// Thanks to Pat Morin for the skeleton of this file!

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author morin
 * @author sharp
 *
 */
public class Part3 {

	/**
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */

	 /*
	 3a: banana and peach
	 3b: ban and app
	 */


	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
    //TODO: Your code goes here -- see Assignment 1 for examples

		TreeSet<String> ts = new TreeSet<String>(); //ceiling(line)
		String str = "";

		for( String line = r.readLine(); line != null; line = r.readLine() ) {
			str = ts.ceiling(line);
			  if((str != null) && (str.startsWith(line))){
							w.println(line);
				}
				ts.add(line);
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
      if (args.length == 0) {
        r = new BufferedReader(new InputStreamReader(System.in));
        w = new PrintWriter(System.out);
      } else if (args.length == 1) {
        r = new BufferedReader(new FileReader(args[0]));
        w = new PrintWriter(System.out);
      } else {
        r = new BufferedReader(new FileReader(args[0]));
        w = new PrintWriter(new FileWriter(args[1]));
      }
      long start = System.nanoTime();
      doIt(r, w);
      w.flush();
      long stop = System.nanoTime();
      System.out.println("Execution time: " + 1e-9 * (stop-start));
    } catch (IOException e) {
      System.err.println(e);
      System.exit(-1);
    }
  }
}
