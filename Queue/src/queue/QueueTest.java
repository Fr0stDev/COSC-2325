package queue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import queue.QueueEngine;
import queue.QueueEngineImpl;
import queue.QueueList;
import queue.QueueListImpl;

public class QueueTest {
	@Before
	public void setUp() throws Exception {
		String username = System.getProperty("user.name");
		System.out.println("****  BEGIN OUTPUT for " + username + "  ****");
	}

	@After
	public void tearDown() throws Exception {
		String username = System.getProperty("user.name");
		System.out.println("****  END OUTPUT for " + username+ "  ****");
	}
	@Test
	public void testQueue() {
		System.out.println("Now testing QueueList...");
		String first="";
		int first1 = 0;
		QueueList<String> queue = new QueueListImpl<String>();
		assertEquals(0, queue.size());
		QueueList<Integer> queue1 = new QueueListImpl<Integer>();
		assertEquals(0, queue1.size());
		queue.clear();
		queue1.clear();
		assertEquals(0, queue.size());
		assertEquals(0, queue1.size());
		
		try {
			 first = queue.getFirst();
			System.out.println("FAILURE  should throw a new RuntimeException for calling getFirst() on an empty queue");
		}catch(RuntimeException e)
		{
			System.out.println("SUCCESS RuntimeException thrown and caught when calling getFirst() on an empty queue");	
		}
		try {
			 first1 = queue1.getFirst();
			System.out.println("FAILURE  should throw a new RuntimeException for calling getFirst() on an empty queue");
		}catch(RuntimeException e)
		{
			System.out.println("SUCCESS RuntimeException thrown and caught when calling getFirst() on an empty queue");	
		}
		try {
			 first = queue.getLast();
			System.out.println("FAILURE  should throw a new RuntimeException for calling getLast() on an empty queue");
		}catch(RuntimeException e)
		{
			System.out.println("SUCCESS RuntimeException thrown and caught when calling getLast() on an empty queue");	
		}
		try {
			 first1 = queue1.getLast();
			System.out.println("FAILURE  should throw a new RuntimeException for calling getLast() on an empty queue");
		}catch(RuntimeException e)
		{
			System.out.println("SUCCESS RuntimeException thrown and caught when calling getLast() on an empty queue");	
		}
		System.out.println("SUCCESS, testQueueEngine Finished");
		try {
			 first = queue.remove();
			System.out.println("FAILURE  should throw a new RuntimeException for calling remove() on an empty queue");
		}catch(RuntimeException e)
		{
			System.out.println("SUCCESS RuntimeException thrown and caught when calling remove() on an empty queue");	
		}
		try {
			 first1 = queue1.remove();
			System.out.println("FAILURE  should throw a new RuntimeException for calling remove() on an empty queue");
		}catch(RuntimeException e)
		{
			System.out.println("SUCCESS RuntimeException thrown and caught when calling remove() on an empty queue");	
		}
		System.out.println("SUCCESS, testQueueList Finished");
		queue.add("#1");
		queue.add("#2");
		queue.add("#3");
		queue.add("#4");
		try {
			 queue.add("#5");
			System.out.println("FAILURE  should throw a new RuntimeException for calling add() on a full queue");
		}catch(RuntimeException e)
		{
			System.out.println("SUCCESS RuntimeException thrown and caught when add() on a full queue");	
		}
		queue1.add(1);
		queue1.add(2);
		queue1.add(3);
		queue1.add(4);
		try {
			 queue1.add(5);
			System.out.println("FAILURE  should throw a new RuntimeException for calling add() on a full queue");
		}catch(RuntimeException e)
		{
			System.out.println("SUCCESS RuntimeException thrown and caught when add() on a full queue");	
		}
		first=queue.remove();
		assertEquals("#1", first);
		assertEquals(3, queue.size());
		first=queue.remove();
		assertEquals("#2", first);
		assertEquals(2, queue.size());
		first=queue.remove();
		assertEquals("#3", first);
		assertEquals(1, queue.size());
		first=queue.remove();
		assertEquals("#4", first);
		assertEquals(0, queue.size());
		first1=queue1.remove();
		assertEquals(1, first1);
		assertEquals(3, queue1.size());
		first1=queue1.remove();
		assertEquals(2, first1);
		assertEquals(2, queue1.size());
		first1=queue1.remove();
		assertEquals(3, first1);
		assertEquals(1, queue1.size());
		first1=queue1.remove();
		assertEquals(4, first1);
		assertEquals(0, queue1.size());
	}
	@Test
	public void testQueueEngine()
	{
		System.out.println("Now Testing QueueEngine...");
		QueueEngine engine = new QueueEngineImpl();
		assertEquals(0, engine.size());
		String value ="";
		try 
		 {
			value = engine.getFirst();
			System.out.println("FAILURE RuntimeException should be thrown when calling getFirst() on an empty queue");
		 } catch(RuntimeException e) {
			 System.out.println("SUCCESS RuntimeException thrown when calling getFirst() on an empty queue");
		 }
		try 
		 {
			value = engine.getLast();
			System.out.println("FAILURE RuntimeException should be thrown when calling getLast() on an empty queue");
		 } catch(RuntimeException e) {
			 System.out.println("SUCCESS RuntimeException thrown when calling getLast() on an empty queue");
		 }
		engine.clear();
		assertEquals(0, engine.size());
		String item = "Item 1";
		engine.add(item);
		assertEquals(1, engine.size());
		assertEquals(item, engine.getFirst());
		assertEquals(item, engine.getLast());
		String item2 = "";
		item2 = engine.remove();
		assertEquals(0, engine.size());
		try 
		 {
			value = engine.getFirst();
			System.out.println("FAILURE RuntimeException should be thrown when calling getFirst() on an empty queue");
		 } catch(RuntimeException e) {
			 System.out.println("SUCCESS RuntimeException thrown when calling getFirst() on an empty queue");
		 }
		try 
		 {
			value = engine.getLast();
			System.out.println("FAILURE RuntimeException should be thrown when calling getLast() on an empty queue");
		 } catch(RuntimeException e) {
			 System.out.println("SUCCESS RuntimeException thrown when calling getLast() on an empty queue");
		 }
		item2 = "Item 2";
		engine.add(item2);
		engine.add(item);
		assertEquals(2, engine.size());
		String item3 = "Item 3";
		String item4 = "Item 4";
		engine.add(item3);
		assertEquals("Item 3", engine.getLast());
		engine.add(item4);
		try {
			engine.add("Item 5");
			System.out.println("FAILURE  should throw a new RuntimeException for calling add() on a full queue");
		}catch(RuntimeException e)
		{
			System.out.println("SUCCESS RuntimeException thrown and caught when calling add() on a full queue");	
		}
		assertEquals("Item 4", engine.getLast());
		assertEquals(4, engine.size());
		try {
			engine.add("Item 5");
			System.out.println("FAILURE  should throw a new RuntimeException for calling add() on a full queue");
		}catch(RuntimeException e)
		{
			System.out.println("SUCCESS RuntimeException thrown and caught when calling add() on a full queue");	
		}
		assertEquals(4, engine.size());
		System.out.println("SUCCESS, testQueueEngine Finished");
		String front = engine.remove();
		assertEquals("Item 2", front);
		assertEquals(3, engine.size());
		front = engine.remove();
		assertEquals(front, "Item 1");
		assertEquals(2, engine.size());
		front = engine.remove();
		assertEquals(front, "Item 3");
		assertEquals(1, engine.size());
		front = engine.remove();
		assertEquals(front, "Item 4");
		assertEquals(0, engine.size());
		engine.add(item4);
		assertEquals(1, engine.size());
		engine.add(item3);
		assertEquals(2, engine.size());
		assertEquals(item3, engine.getLast());
		assertEquals(item4, engine.getFirst());
		engine.clear();
		assertEquals(0, engine.size());
		
		try {
			front = engine.getLast();
			System.out.println("FAILURE  should throw a new RuntimeException for calling getLast() on an empty queue");
		}catch(RuntimeException e)
		{
			System.out.println("SUCCESS RuntimeException thrown and caught when calling getLast() on an empty queue");	
		}
		try {
			front = engine.getFirst();
			System.out.println("FAILURE  should throw a new RuntimeException for calling getFirst() on an empty queue");
		}catch(RuntimeException e)
		{
			System.out.println("SUCCESS RuntimeException thrown and caught when calling getFirst() on an empty queue");	
		}
		System.out.println("SUCCESS, testQueueEngine Finished");
	}

}
