package queue;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public class QueueEngineImpl implements QueueEngine {
	
	private QueueList<String> myQueue;
	private EventListenerList eventListenerList;
    private final ChangeEvent CHANGE_EVENT = new ChangeEvent(this);

	public QueueEngineImpl() {
		this.eventListenerList = new EventListenerList();
		this.myQueue = new QueueListImpl<String>();
	}
	
	//pre: the queueEngine is not full if so throws RuntimeException
    //post: item is added to the rear (end) of the queueEngine
	public void add(String item) {
		myQueue.add(item);
        this.fireChangeEvent(CHANGE_EVENT);
	}

	//pre: this queueEngine is not empty
	//post: the first item is removed and returned throws new RuntimeException on empty queue
	public String remove() {
        String ret = myQueue.remove();
        this.fireChangeEvent(CHANGE_EVENT);
        return ret;
		
	}

	//pre: this queueEngine is not empty
	//post: the first item is returned, but NOT REMOVED throws new RuntimeException on empty queue
	public String getFirst() {
		return myQueue.getFirst();
	}

	//pre: this queueEngine is not empty
	//post: the LAST item is returned, but NOT REMOVED throws new RuntimeException on empty queue
	public String getLast() {
		return myQueue.getLast();
	}

	//pre: none
	//post: returns number of items in this queueEngine
	public int size() {
		return myQueue.size();
	}

	//pre: none
	//post: all items removed from this queueEngine, the size is 0
	public void clear() {
        
		myQueue.clear();
		this.fireChangeEvent(CHANGE_EVENT);
		
	}
	
	// To String
	public String toString() {
		return myQueue.toString();
	}

	// boiler plate standard methods for use with a model - adds change listener instance to this model's listener list
	public void addChangeListener(ChangeListener changeListener) {
        eventListenerList.add(ChangeListener.class, changeListener);
		
	}

	// removes change listener from this model's listener list.
	public void removeChangeListener(ChangeListener changeListener) {
        eventListenerList.remove(ChangeListener.class, changeListener);
		
	}

	// raises or fires off a change event so that any listeners can hear it and thus update their situation (ie the view)
	public void fireChangeEvent(ChangeEvent event) {
		// Guaranteed to return a non-null array
		Object[] listeners = eventListenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length-2; i>=0; i-=2) {
			if (listeners[i]==ChangeListener.class) {
				((ChangeListener)listeners[i+1]).stateChanged(event);
			}
		}
	}
}
