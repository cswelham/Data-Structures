//Connor Welham
//1508018

import java.io.File;
import java.util.Scanner;

class BankBST
{
	static account root;
	
	//Creates string for account numbers
	static String keyString = "";		
	
	//Starts recursion from the root account
	public void add(int key, double balance)
	{
		root = addRecursive(root, key, balance);
	}
	
	//Inserting a bank account
	private account addRecursive(account current, int key, double balance)
	{
		//If current node is null we insert the new account here
		if (current == null)
		{
			return new account(key, balance);
		}
		
		//If the account's number is lower than the current number
		//we go to the left child
		if (key < current.getKey())
		{
			current.left = addRecursive(current.left, key, balance);
		}
		//If the account's number is greater than the current number
		//we go to the right child
		else if (key > current.getKey())
		{
			current.right = addRecursive(current.right, key, balance);
		}
		//Account number already exists
		else
		{
			return current;
		}
		
		return current;
	}

	//Starts recursion from the root account
	public account find(int key)
	{
		return findRecursive(root, key);
	}
		
	//Returns a reference to the account in the tree
	private account findRecursive(account current, int key)
	{	
		//Account is not in the tree
		if (current == null)
		{
			return null;
		}
		
		else
		{
			//Add current account to key string
			keyString = keyString + current.getKey() + " ";
		}
				
		//Account is in the tree
		if (key == current.getKey())
		{
			return current;
		}
		//Look to the left child and to the right child of current
		return key < current.getKey()
		  ? findRecursive(current.left, key)
		  : findRecursive(current.right, key);
	}

	//Starts delete recursive from root
	public void delete(int key)
	{
		root = deleteRecursive(root, key);
	}
	
	//Find the account to replace a deleted account
	private int minValue(account current)
	{
		//Set current's account number to the min value
		int minv = current.getKey();
		//While current isnt null
		while (current.left != null)
		{
			//Set left child to min value
			minv = current.left.getKey();
			current = current.left;
		}
		//Return minimum value
		return minv;
	}
	
	//Deletes an account from the tree
	private account deleteRecursive(account current, int key)
	{
		//If tree is empty
		if (current == null)
		{
			return null;
		}
		//If current is account to delete
		if (current.getKey() == key)
		{
			//Account has no children
			if (current.right == null && current.left == null)
			{
				//Set current to null
				return null;
			}
			//If account has one child
			if (current.right == null)
			{
				//Return non null child to become parent node
				return current.left;
			}
			if (current.left == null)
			{
				//Return non null child to become parent node
				return current.right;
			}
			
			//Account has two children
			//Find smallest account number and its corresponding balance
			int minA = minValue(current.right);
			account min = this.find(minA);
			double minB = min.getBalance();
			//Set current account number to smallest key to delete from right subtree
			current.setKey(minA);
			current.newBalance(minB);
			current.right = deleteRecursive(current.right, minA);
			return current;
		}
		//If account number of current is bigger
		if (key < current.getKey())
		{
			//Go to the left child
			current.left = deleteRecursive(current.left, key);
		}
		//Go to the right child
		current.right = deleteRecursive(current.right, key);
		return current;
	}
	
	//In order traversal of the tree
	public void traverseInOrder(account account)
	{
		//Check account is null
		if (account != null)
		{
			//Go to left child
			traverseInOrder(account.left);
			//Print the account to the console
			System.out.println(account.getKey() + " " + account.getBalance());
			//Go to right child
			traverseInOrder(account.right);
		}
	}
	
	public static void main(String[] args)
	{
		try
		{		
			//User inputs name of file
			Scanner scan = new Scanner(System.in);
			System.out.println("File:");
			String filename = scan.nextLine();			
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			
			//Create binary tree
			BankBST bank = new BankBST();
			
			//String of the line
			String line = "";
			//Variables for parts of string
			int accNum;
			String transaction;
			double balance;
			account currentAcc;
			String type = null;
			
			//Read lines from the file
			while (sc.hasNextLine())
			{
				try
				{
					//Set line to the line in the text
					line = sc.nextLine();
					//Split string
					String[] splitLine = line.split("\\s+");
					//Store parts of string in variables
					accNum = Integer.parseInt(splitLine[0]);
					transaction = splitLine[1];
					balance = Double.parseDouble(splitLine[2]);
					
					//If transaction is deposit
					if (transaction.equals("d"))
					{
						type = "DEPOSIT";
						//Check if account number in bank
						currentAcc = bank.find(accNum);
						//If account number is not in bank
						if (currentAcc == null)
						{
							//Create a new account
							bank.add(accNum, 0);
							//Reset key string
							keyString = "";
							//Set current account to this
							currentAcc = bank.find(accNum);
						}
						//Add deposit to bank account					
						currentAcc.setBalance(balance);						
					}
					
					//If transaction is withdrawl
					else if (transaction.equals("w"))
					{
						type = "WITHDRAWL";
						//Check if account number in bank
						currentAcc = bank.find(accNum);
						//If account number is not in bank
						if (currentAcc == null)
						{
							//Create a new account
							bank.add(accNum, 0);
							//Reset key string
							keyString = "";
							//Set current account to this
							currentAcc = bank.find(accNum);
						}
						//Add negative withdrawl to bank account
						balance = -balance;
						currentAcc.setBalance(balance);	
					}
					
					//If transaction is closure
					else if (transaction.equals("c"))
					{
						type = "CLOSE";
						//Check if account in bank
						currentAcc = bank.find(accNum);
						//If account number is bank
						if (currentAcc != null)
						{
							//Delete account
							bank.delete(accNum);
						}
					}
					//Print string
					System.out.println(keyString + type);
					//Reset key string
					keyString = "";
				}
				catch (Exception e)
				{
					//Move to next line
					System.out.println("There was an error with this line and it could not be entered.");
				}
			}
			scan.close();
			sc.close();
			//Print out the bank tree
			System.out.println("");
			System.out.println("RESULT");
			bank.traverseInOrder(root);
		}
		
		catch (Exception e)
		{
			//Error with finding file
			System.out.println("File does not exist.");
		}
	}
}
