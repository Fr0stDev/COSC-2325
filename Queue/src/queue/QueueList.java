package queue;

public interface QueueList <T>
{
// max number of allowable elements in the Queue
 public static final int MAX_ELEMENTS=4;

/** pre: none
 * post: returns  number of elements on this Queue
 */
  public int size();

/** places item at the end of this Queue
 *pre: param element - item to be added
 *post: element is at the end of the queue throws new RuntimeException on full queue
 * @throws - RuntimeException if attempt to add to a full queue
 */
  public void add(T item);

/** pre: queue is NOT empty
   post: removes and returns the front item from this Queue if this Queue is
 *      not empty. 
 * @throws - RuntimeException if attempt to remove from an empty queue
 * @return - the object that was removed from the front of the queue.
 */
  public T remove();

/** pre: queue is not empty
  *returns the front item from this Queue if this Queue is
 * not empty.  DOES NOT REMOVE IT.
 * @throws - RuntimeException if attempt to getFirst for an empty queue
 * @return - object that is currently at the front of the queue
 */
public T getFirst();

/** pre: queue is not empty
  *returns the last item from this Queue if this Queue is
 * not empty.  DOES NOT REMOVE IT.
 * @throws - RuntimeException if attempt to getLast for an empty queue
 * @return - object that is currently at the end of the queue
 */
  public T getLast();

/** pre: none
    post: removes all items from this Queue, making it empty.
 */
  public void clear();
}