//Connor Welham
//1508018

public class account
{
	//Creates attributes that account has
	private int key;
	private double balance;
	//Accounts right and left
	account right;
	account left;
			
	account(int k, double b)
	{
		key = k;
		balance = b;
		right = null;
		left = null;
	}
		
	//Gets the account number
	public int getKey()
	{
		return key;
	}
		
	//Gets the balance of the account
	public double getBalance()
	{
		return balance;
	}
		
	//Changes the account number
	public void setKey(int K)
	{
		key = K;
	}
		
	//Changes the balance in an account by a value
	public void setBalance(double B)
	{
		balance += B;
		//Formats number to 2dp
		balance = Math.round(balance * 100.0) / 100.0;
	}
	
	//Creates a new balance for an account
	public void newBalance(double B)	
	{
		balance = B;
	}	
}
