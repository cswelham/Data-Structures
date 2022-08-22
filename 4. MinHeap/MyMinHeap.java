//Connor Welham
//ID: 1508018

import java.util.Arrays;

public class MyMinHeap implements MinHeapFace
{
	private static final int DEFAULT_CAPACITY = 20;
	private int[] heap;
	private int size;
		
	public MyMinHeap()
	{
		size = 0;
		heap = new int[DEFAULT_CAPACITY + 1];
		heap[0] = Integer.MIN_VALUE;
	}
	
	//Returns position of parent
	private int parent (int x)
	{
		return x / 2;
	}
	
	//Returns position of left child
	private int leftChild(int x)
	{
		return (2 * x);
	}
	
	//Returns position of right child
	private int rightChild(int x)
	{
		return (2 * x) + 1;
	}
	
	//Returns true if node is a leaf
	private boolean isleaf(int x)
	{
		//Casted size to float for boundary case when size = 3 and x = 1
		if (x >= ((float)size / 2) && x <= (float)size)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Swaps two nodes of the heap
	private void swap(int x, int y)
	{
		int temp;
		temp = heap[x];
		heap[x] = heap[y];
		heap[y] = temp;
	}
	
	//Function that downheaps the heap at a position
	private void downHeap(int x)
	{
		//If node is non-leaf and greater than any of its children
		if(isleaf(x) == false)
		{
			if (heap[x] > heap[leftChild(x)] || heap[x] > heap[rightChild(x)])
			{
				//Swap left child and down heap left child
				if (heap[leftChild(x)] < heap[rightChild(x)])
				{
					swap(x, leftChild(x));
					downHeap(leftChild(x));
				}
				
				//Swap right child and down heap right child
				else
				{
					swap(x, rightChild(x));
					downHeap(rightChild(x));
				}
			}
		}
		//Case where only two nodes in tree
		else if (size() == 2)
		{
			if (heap[1] > heap[leftChild(1)])
			{
				swap(x, leftChild(1));
			}
			else if (heap[1] > heap[rightChild(1)])
			{
				swap(1, rightChild(1));
			}
		}	
	}
	
	//Maintains heap property while inserting element
	private void upHeap()
	{
		int index = this.size;
		
		while (heap[index] < heap[parent(index)])
		{
			swap(index, parent(index));
			index = parent(index);
		}
	}
	
	//Add a node to heap
	public void add(int x)
	{
		if (size >= heap.length - 1)
		{
			heap = this.resize();
		}
		heap[++size] = x;
		upHeap();

	}
	
	//Increases the size of the array by 100%
	private int[] resize()
	{
		return Arrays.copyOf(heap, heap.length * 2);
	}
	
	//Remove top of heap
	public void remove()
	{
		if (isEmpty() == false)
		{
			heap[1] = heap[size--];	
			if (isEmpty() == false)
			{
				downHeap(1);
			}	
		}	
	}
	
	//Replace the top of heap with value
	public void replace(int x)
	{
		if (isEmpty() == true)
		{
			this.add(x);
		}
		else
		{
			heap[1] = x;
			downHeap(1);		
		}
	}
	
	//Return top of heap
	public int get()
	{
		if (isEmpty() == true)
		{
			System.err.println("Heap is empty.");
			return -1;
		}
		else
		{
			return heap[1];
		}		
	}
	
	//Clear contents of heap
	public void clear()
	{
		heap = new int[20];
		size = 0;	
	}
	
	//Return if heap is empty
	public boolean isEmpty()
	{
		if (size() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Return size of heap
	public int size()
	{
		return this.size;
	}
	
	//Prints contents of the heap
	public void print()
	{
		for (int i = 1; i <= size / 2; i++)
		{
			//Checking if only two nodes in tree
			if ((2*i+1) > size)
			{
				System.out.println(" PARENT : " + heap[i] + 
				" LEFT CHILD : " + heap[leftChild(i)] + 
				" RIGHT CHILD : 0");
			}
			else
			{
				System.out.println(" PARENT : " + heap[i] + 
				" LEFT CHILD : " + heap[leftChild(i)] + 
				" RIGHT CHILD : " + heap[rightChild(i)]);
			}
		}
	}	
} 
