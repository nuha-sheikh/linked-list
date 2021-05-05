package comp2402a3;
// Thanks to Pat Morin for the skeleton of this file.

import java.util.ListIterator;
import java.util.Random;

/**
 * An implementation of the List interface as a doubly-linked circular list. A
 * dummy node is used to simplify the code.
 *
 * @author morin
 * @author sharp
 *
 * @param <T>
 *            the type of elements stored in the list
 */
public class DLList<T> implements MyList<T> {
	class Node {
		T x;
		Node prev, next;
	}

	/**
	 * Number of nodes in the list
	 */
	int n;

	/**
	 * The dummy node. We use the convention that dummy.next = first and
	 * dummy.prev = last
	 */
	protected Node dummy;

	public DLList() {
		dummy = new Node();
		dummy.next = dummy;
		dummy.prev = dummy;
		n = 0;
	}

	/**
	 * Add a new node containing x before the node p
	 *
	 * @param w
	 *            the node to insert the new node before
	 * @param x
	 *            the value to store in the new node
	 * @return the newly created and inserted node
	 */
	protected Node addBefore(Node w, T x) {
		Node u = new Node(); //insert node w before x
		u.x = x;
		u.prev = w.prev;
		u.next = w;
		u.next.prev = u;
		u.prev.next = u;
		n++;
		return u;
	}

	/**
	 * Remove the node p from the list
	 *
	 * @param w
	 *            the node to remove
	 */
	protected void remove(Node w) {
		w.prev.next = w.next;
		w.next.prev = w.prev;
		n--;
	}

	/**
	 * Get the i'th node in the list
	 *
	 * @param i
	 *            the index of the node to get
	 * @return the node with index i
	 */
	protected Node getNode(int i) {
		Node p = null;
		if (i < n / 2) {
			p = dummy.next;
			for (int j = 0; j < i; j++)
				p = p.next;
		} else {
			p = dummy;
			for (int j = n; j > i; j--)
				p = p.prev;
		}
		return p;
	}

	public int size() {
		return n;
	}

	public boolean add(T x) {
		addBefore(dummy, x);
		return true;
	}

	public T remove(int i) {
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		Node w = getNode(i);
		remove(w);
		return w.x;
	}

	public void add(int i, T x) {
		if (i < 0 || i > n) throw new IndexOutOfBoundsException();
		addBefore(getNode(i), x);
	}

	public T get(int i) {
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		return getNode(i).x;
	}

	public T set(int i, T x) {
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		Node u = getNode(i);
		T y = u.x;
		u.x = x;
		return y;
	}

	public void clear() {
		dummy.next = dummy;
		dummy.prev = dummy;
		n = 0;
	}

  public MyList<T> shuffle(MyList<T> other ) {
    // Create a new DLList
    DLList<T> merged = new DLList<T>();
    int min = Math.min(this.size(), other.size());

		ListIterator<T> it = new Iterator(this, 0);
		ListIterator<T> it2 = new Iterator((DLList<T>) other, 0);

		while(it.nextIndex() < min){
			merged.add(it.next());
      merged.add(it2.next());
		}

		String longerList = "this";
		if( this.size() < other.size() ) {
      longerList = "other";
  	}

		System.out.println(merged);

		if( (this.size() + other.size()) != merged.size()){
			if(longerList == "this"){
				while(it.hasNext()){
						merged.add(it.next());
				}
			}else{
				while(merged.size() < (this.size() + other.size()) ){
						merged.add(it2.next());
					}
			}
		}
    return merged;
  }

  public MyList<T> chop( int i ) {

		DLList<T> other = new DLList<T>();

		int size = this.n;
		Node lastNode = this.getNode(size-1);
		Node firstNode = this.getNode(0);

		if( i >= this.size()){ //remove

		}else if( i == 0 ){

			lastNode.next = other.dummy;
			other.dummy.prev = lastNode;
			other.dummy.next = firstNode;
			this.clear();

			this.n = 0;
			other.n = size;

		}else{
			Node chopNode = this.getNode(i);
			Node beforeChopNode = this.getNode(i-1);

			lastNode.next = other.dummy;
			other.dummy.prev = lastNode;
			other.dummy.next = chopNode;

			beforeChopNode.next = this.dummy;
			this.dummy.prev = beforeChopNode;
			this.dummy.next = firstNode;

			this.n = i;
			other.n = size-i;
		}
		// System.out.println("This size: "+ this.size());
		// System.out.println("Other size: "+ other.size());

    return other;
  }

  public void shrink() {
    // TODO: implement shrink
		if( this.size() == 0 ){
			return;
		}

		ListIterator<T> it = new Iterator(this, 0);
			T current = it.next();
			T next = it.next();

			while(next != null && current != null ){
				if(current.equals(next)){
					it.remove();
					current = it.previous();
					it.remove();

					next = it.previous();
					next = it.next();
				}else{
					current = next;
					next = it.next();
				}
			}
  }

  public void reverse() {
    // YOU DO NOT HAVE TO IMPLEMENT THIS IN THIS ASSIGNMENT.
    // (You have to implement it in the SkiplistList class, however.)
    // The server will not test this method.
  }

  public String toString() {
    StringBuilder retStr = new StringBuilder();
    retStr.append("[");
    Node u = dummy.next;
    while( u != dummy ) {
      retStr.append(u.x);
      u = u.next;
      if( u != dummy ) {
        retStr.append(", ");
      }
    }
    retStr.append("]");
    return retStr.toString();
  }

	class Iterator implements ListIterator<T> {
		/**
		 * The list we are iterating over
		 */
		DLList<T> l;

		/**
		 * The node whose value is returned by next()
		 */
		Node p;

		/**
		 * The last node whose value was returned by next() or previous()
		 */
		Node last;

		/**
		 * The index of p
		 */
		int i;

		Iterator(DLList<T> il, int ii) {
			l = il;
			i = ii;
			p = l.getNode(i);
		}

		public boolean hasNext() {
			return p != dummy;
		}

		public T next() {
			T x = p.x;
			last = p;
			p = p.next;
			i++;
			return x;
		}

		public int nextIndex() {
			return i;
		}

		public boolean hasPrevious() {
			return p.prev != dummy;
		}

		public T previous() {
			p = p.prev;
			last = p;
			i--;
			return p.x;
		}

		public int previousIndex() {
			return i - 1;
		}

		public void add(T x) {
			DLList.this.addBefore(p, x);
		}

		public void set(T x) {
			last.x = x;
		}

		public void remove() {
			if (p == last) {
				p = p.next;
			}
			DLList.this.remove(last);
		}

	}
}
