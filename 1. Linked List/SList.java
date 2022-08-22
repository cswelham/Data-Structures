//Used during testing of scanner with file
import java.io.File;
import java.util.Scanner;

//Creating a Linked List using java classes
public class SList 
{
	Node head;
	
	//Node for Linked List
	static class Node 
	{
		//Creates the key for string in node
		String key;
		//Which node is next in series
		Node next;
		
		Node(String k)
		{
			key = k;
			next = null;
		}
	}
	
	//Method to add a new string s to the linked list	
	public void add(String s)
	{	
		//Creates a new node
		Node newNode = new Node(s);
		//Sets head as next node
		newNode.next = this.head;
		//Sets head as the new node created
		this.head = newNode;
	}
	
	//Method for determining if a string is in linked list
	public boolean has(String s)
	{
		Node current = this.head; 
		Node prev = null;
		//Go through each node checking the key
		while (current != null && current.key.equals(s) == false)
		{
			//Move to next node
			prev = current;
			current = current.next;
		}
		
		//If current is null key was not found
		if (current == null)
		{
			//Return that key is not found
			return false;
		}
		//Else key is found
		else
		{
			//Return key is found
			return true;
		}
	}
	
	//Method for removing a string from the linked list
	public void remove(String s)
	{
		//Check if string is in list
		boolean result = has(s);
		
		//If key is not in list
		if (result == true)
		{
			//Get what current node head is
			Node current = this.head, prev = null;
			
			//If head node is node to be deleted
			if (current != null && current.key.equals(s))
			{
				//Move head
				this.head = current.next;
				return;
			}
			
			//If key is not in head
			while (current != null && current.key.equals(s) == false)
			{
				//Move to next node
				prev = current;
				current = current.next;
			}
			
			//If current is not null it means we have found where node is
			if (current != null)
			{
				//Unlink current node from linked list
				prev.next = current.next;
			}
		}		
	}
	
	//Method for returning length of linked list
	public int length()
	{
		//Sets current node at head and sets previous node to null
		Node current = this.head;
		Node prev = null;
		int count = 0;
		
		//For each node in linked list
		while (current != null)
		{
			//Move onto next node
			prev = current;
			current = current.next;
			//Add one to count
			count++;
		}
		
		//Return length of linked list
		return count;
	}
	
	//Method that returns whether the linked list is empty
	public boolean isEmpty()
	{
		//Get length of linked list
		int count = this.length();
		
		//If count is 0
		if (count == 0)
		{
			//Return true
			return true;
		}
		//Else return false
		else
		{
			return false;
		}
		
	}
	
	//Method for printing each key in the linked list
	public void dump()
	{
		//Sets current node to first node
		Node current = this.head;
		
		//For each key in the linked list
		while (current != null)
		{
			//Print the current node
			System.out.println(current.key);
			//Go to next node
			current = current.next;
		}
	}
}

