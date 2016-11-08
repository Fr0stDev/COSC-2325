package queue;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public interface QueueEngine {
	public final static int MAX = QueueList.MAX_ELEMENTS;
	
	//pre: the queueEngine is not full if so throws RuntimeException
    //post: item is added to the rear (end) of the queueEngine
	public void add(String item);
	
	//pre: this queueEngine is not empty
	//post: the first item is removed and returned throws new RuntimeException on empty queue
	public String remove();
	
	//pre: this queueEngine is not empty
	//post: the first item is returned, but NOT REMOVED throws new RuntimeException on empty queue
	public String getFirst();
	
	//pre: this queueEngine is not empty
	//post: the LAST item is returned, but NOT REMOVED throws new RuntimeException on empty queue
	public String getLast();
	
	//pre: none
	//post: returns number of items in this queueEngine
	public int size();
	
	//pre: none
	//post: all items removed from this queueEngine, the size is 0
	public void clear();
	
	// boiler plate standard methods for use with a model - adds change listener instance to this model's listener list
	public void addChangeListener(ChangeListener stackEngineListener);
	
	// removes change listener from this model's listener list.
	public void removeChangeListener(ChangeListener stackEngineListener);
	
	// raises or fires off a change event so that any listeners can hear it and thus update their situation (ie the view)
	public void fireChangeEvent(ChangeEvent event);
}