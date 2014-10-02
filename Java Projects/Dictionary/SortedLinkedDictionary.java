import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.Serializable;

public class SortedLinkedDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V>, Serializable
                        
{
   private Node firstNode;
	private int currentSize;
	
	//constructor
	public SortedLinkedDictionary()
	{
	   firstNode = null;
		currentSize = 0;
	}	
	
	//method ADD
	public V add(K key, V value)
	{
	   V result = null;
		
		Node currentNode = firstNode;
		Node nodeBefore = null;
		
		while( (currentNode != null) && key.compareTo(currentNode.getKey()) > 0)
		{
		   nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
		}
		
		if ( (currentNode != null) && key.equals(currentNode.getKey()) )
		{ 
		   result = currentNode.getValue();
			currentNode.setValue(value);
		}
		
		else
		{
		   Node newNode = new Node(key, value); //create new Node
			currentSize++;
			
			if( nodeBefore == null)
			{ // add at beginning
			  newNode.setNextNode(firstNode);
			  firstNode = newNode;
			}
			else // add elsewhere in non-empty chain
			{
			   newNode.setNextNode(currentNode);
				nodeBefore.setNextNode(newNode);
			}
		}
		
		return result;
	}//end of ADD	
	
	//METHOD REMOVE
	public V remove(K key)
	{
	  V result = null;
	  Node currentNode = firstNode;
	  Node nodeBefore = null;
	  
		
		while( (currentNode != null) && key.compareTo(currentNode.getKey()) > 0)
		{
		   nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
		}
		
		if ( (currentNode != null) && key.equals(currentNode.getKey()) )
		{ 
		   currentSize--;
			Node NodeAfter = currentNode.getNextNode();
		   result = currentNode.getValue();
			nodeBefore.setNextNode(NodeAfter);
			currentNode = null;
		}
	   
		return result;
	}	//END OF REMOVE
	
	
	//METHOD GETVALUE
  public V getValue(K key)
  {
     V result = null;
	  Node currentNode = firstNode;
	
	  while( (currentNode != null) && key.compareTo(currentNode.getKey()) > 0)
	  {
			currentNode = currentNode.getNextNode();
	  }
		
	  if ( (currentNode != null) && key.equals(currentNode.getKey()) )
	  {    
		  result = currentNode.getValue();
	  }	  

        return result;
  }	//END OF GET VALUE	
  
  //METHOD CONTAINS
  public boolean contains(K key)	
  {
    boolean isSuccessfull = false;
	 Node currentNode = firstNode;
	
	 while( (currentNode != null) && key.compareTo(currentNode.getKey()) > 0)
	 {
			currentNode = currentNode.getNextNode();
	 }
	
	 if ( (currentNode != null) && key.equals(currentNode.getKey()) )
	 {
	   isSuccessfull = true;
	 }
	
	   return isSuccessfull;
   }// END OF CONTAINS	
	
	
	 //METHOD ISEMPTY
  public boolean isEmpty()
  {
	  boolean result = false;
	  
	  if(firstNode == null)
	  {
	      
		  result = true;
	  }
	  
		return result;
	}	// end of isEmpty
	

	
	//Method getKeyIterator	
	public Iterator<K> getKeyIterator()
	{
	  return new KeyIterator();
	}  	
	
	//Method getValueIterator	
	public Iterator<V> getValueIterator()
	{
	  return new ValueIterator();
	}  	
	
	
	//private classes****************************
	
	 //NODE
	 private class Node implements Serializable
    {  
     private K key;
	  private V value;
	  private Node next; // link to next node
	
	
	  private Node(K searchKey, V dataValue)
	  {
	    key = searchKey;
	    value = dataValue;
		 next = null;
     } //   end constructor
	  
	  private Node(K searchKey, V dataValue, Node nextNode)
	  {
	    value = dataValue;
		 key = searchKey;
	    next = nextNode;
     } //   end constructor
	  
	  
	  
     private K getKey()
	  {
	     return key;
	  }
	  
	  private V getValue()
	  {
	     return value;
	  }

	  
	  private void setValue(V newValue)
	  {
	     value = newValue;
	  }
	  
	  private Node getNextNode()
	  {
	     return next;
	  }
	  
	  private void setNextNode(Node nextNode)
	  {
	     next = nextNode;
	  }// end setNextNode
	 } // nedNod  	
	 
	 //Key Iterator
	 private class KeyIterator implements Iterator<K>
	 {
	   private Node nextNode;
		private KeyIterator()
		{
		  nextNode = firstNode;
		}
		
		public boolean hasNext()
		{
		  return nextNode != null;
		}
		
		public K next()
		{
		  K result;
		  
		  if(hasNext())
		  {
		    result = nextNode.getKey();
			 nextNode = nextNode.getNextNode();
		  }
		  else
		    throw new NoSuchElementException();
			 
		  return result;
		}
		
		public void remove()
		{
		  throw new UnsupportedOperationException();
		}
	}//end keyIterator	
	
	 //Value Iterator
	 private class ValueIterator implements Iterator<V>
	 {
	   private Node nextNode;
		private ValueIterator()
		{
		  nextNode = firstNode;
		}
		
		public boolean hasNext()
		{
		  return nextNode != null;
		}
		
		public V next()
		{
		  V result;
		  
		  if(hasNext())
		  {
		    result = nextNode.getValue();
			 nextNode = nextNode.getNextNode();
		  }
		  else
		    throw new NoSuchElementException();
			 
		  return result;
		}
		
		public void remove()
		{
		  throw new UnsupportedOperationException();
		}
	}//END OF VALUE ITERATOR    	 	      
		
}// class ends
									
			