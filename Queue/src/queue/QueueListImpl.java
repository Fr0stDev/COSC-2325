package queue;

public class QueueListImpl<T> implements QueueList<T> {
	
	private int count;
	private Node<T> first;
	private Node<T> last;

	public QueueListImpl() {
		this.clear();
	}
	
	/** pre: none
	 * post: returns  number of elements on this Queue
	 */
	public int size() {
		// TODO Auto-generated method stub
		return this.count;
	}

	/** places item at the end of this Queue
	 *pre: param element - item to be added
	 *post: element is at the end of the queue throws new RuntimeException on full queue
	 * @throws - RuntimeException if attempt to add to a full queue
	 */
	public void add (T item) {
		if (this.size() == MAX_ELEMENTS) {
			throw new RuntimeException();
		}
		
		Node<T> node = new Node<T>();
		node.data = item;

		if (last != null) {
			last.link = node;
			last = node;
			count++;	
		}

		else {
			last = node;
			first = node;
			count++;
		}
	}

	/** pre: queue is NOT empty
	   post: removes and returns the front item from this Queue if this Queue is
	 *      not empty. 
	 * @throws - RuntimeException if attempt to remove from an empty queue
	 * @return - the object that was removed from the front of the queue.
	 */
	public T remove() {
		if (this.size() == 0) {
			throw new RuntimeException();
		}
		T retValue = this.first.data;
		this.first = this.first.link;

		if (this.first == null) {
			this.last = null;
		}

		this.count-- ;
		return retValue;
	}

	/** pre: queue is not empty
	  *returns the front item from this Queue if this Queue is
	 * not empty.  DOES NOT REMOVE IT.
	 * @throws - RuntimeException if attempt to getFirst for an empty queue
	 * @return - object that is currently at the front of the queue
	 */
	public T getFirst() {
		return this.first.data;
	}

	/** pre: queue is not empty
	  *returns the last item from this Queue if this Queue is
	 * not empty.  DOES NOT REMOVE IT.
	 * @throws - RuntimeException if attempt to getLast for an empty queue
	 * @return - object that is currently at the end of the queue
	 */
	public T getLast() {
		return this.last.data;
	}

	/** pre: none
     *post: removes all items from this Queue, making it empty.
	 */
	public void clear() {
		this.count = 0;
		this.first = null;
		this.last = null;
	}

	/**
	 * To String.
	 */
	public String toString() {
		String temp = "";

		if (this.count == 0) {
			return "The queue is empty";
		}

		Node<T> node = this.first;
		while (node != null) {
			temp += node.data + "\n";
			node = node.link;
		}
		return temp;
	}

}
