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
import java.util.Iterator;

public class Part4 {

	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	 // ban
	 // ran
	 // range
	 // app
	public static void doIt(BufferedReader r, PrintWriter w)
      throws IOException {
    //TODO: Your code goes here -- see Assignment 1 for examples

		// TreeSet<String> ts = new TreeSet<String>(); //ceiling(line)
		// String str = "";
		//
		// for( String line = r.readLine(); line != null; line = r.readLine() ) {
		// 	str = ts.ceiling(line);
		// 	System.out.println(line);
		// 	System.out.println(str);
		// 	System.out.println("---");
		// 	  if((str != null) && ( (str.startsWith(line)) || str.substring(1).startsWith(line) ) ){
		// 					w.println(line);
		// 		}
		// 		ts.add(line);
		// }

// 		ban
// ran
// range
// app

		SortedSet<String> set = new TreeSet<String>();
		SortedSet<String> ts;

		for (String line = r.readLine(); line != null; line = r.readLine()) {
				int length = line.length() - 1;
				ts = set.tailSet(line);
				if ( ( ts.isEmpty() == true ) || ( ts.first().startsWith(line) == false ) ) {
						for(int i = 0; i < length; i++) {
								set.add(line.substring(i));
						}
				}
				else{
						for(int j = 0; j < length; j++) {
								set.add(line.substring(j));
						}
						w.println(line);
				}
		}

		// SortedSet<String> ts = new TreeSet<String>();
		//
		// for( String line = r.readLine(); line != null; line = r.readLine() ) {
		// 	Iterator it = ts.iterator();
		// 	while( it.hasNext() ){
		// 		String temp = (it.next()).toString();
		// 		//w.println(temp +" "+ line);
		// 	  //w.println(temp.indexOf(line));
		// 		if(temp.indexOf(line) != -1){
		// 			w.println(line);
		// 			break;
		// 		}
		// 	}
		// 	//w.println("-----");
		// 	ts.add(line);
		// 		// for( String el: ts ){
		// 		// 	if(el.substring(1).startsWith(line) || el.startsWith(line) ){
		// 		// 		w.println(line);
		// 		// 		break;
		// 		// 	}
		// 		// }
		// 		// ts.add(line);
		// }
		//iterator , check is has next
		//index of(checks if there is a substring) > 0 , println and break out
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
