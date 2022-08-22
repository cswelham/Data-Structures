//Connor Welham
//1508018

//Creating a queue class using a dynamic linked list
public class ClientQueue
{
	Client head;
	
	//Client in the list
	static class Client 
	{
		//Creates the attributes that a client has
		int arrTime;
		int useTime;
		//Which client is next in series
		Client next;
		
		Client(int t, int u)
		{
			arrTime = t;
			useTime = u;
			next = null;
		}
	}
	
	//Method to add a new client to the queue	
	public void enqueue(int t, int u)
	{	
		try
		{
			//Creates a new client
			Client newClient = new Client(t, u);
			
			//If the queue is empty
			if (this.head == null)
			{
				//Set the new client as the head of the queue
				this.head = newClient;
			}
			
			else
			{
				//Go to the last client
				Client last = this.head;
				//If the next client is not null
				while(last.next != null)
				{
					//Set last to the next client in the queue
					last = last.next;
				}
				
				//Insert the new client as the last client
				last.next = newClient;
			}
		}
		catch (Exception e)
		{
			System.out.println("Incorrect details. Please try again.");
		}
	}
	
	//Method for removing the client at the head of the queue
	public void dequeue()
	{
		//Check that queue has clients in
		if (!this.isEmpty())
		{
			//Get what the current head of queue is
			Client current = this.head, prev = null;
			
			//Move head
			this.head = current.next;
		}							
	}
	
	//Method for returning the value of arrTime for the client at head of queue
	public int peekArrive()
	{
		//If queue is empty return -1
		if (this.isEmpty())
		{
			return -1;
		}
		else
		{
			return this.head.arrTime;
		}
	}
	
	//Method for returning the value of useTime for the client at head of queue
	public int peekUsage()
	{
		//If queue is empty return -1
		if (this.isEmpty())
		{
			return -1;
		}
		else
		{
			return this.head.useTime;
		}
	}
	
	//Method for returning length of the queue
	public int length()
	{
		//Sets current client at head and sets previous client to null
		Client current = this.head;
		Client prev = null;
		int count = 0;
		
		//For each client in the queue
		while (current != null)
		{
			//Move onto next client
			prev = current;
			current = current.next;
			//Add one to count
			count++;
		}
		
		//Return length of queue
		return count;
	}
	
	//Method that returns whether the queue is empty
	public boolean isEmpty()
	{
		//Get length of the queue
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
}

