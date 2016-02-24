public class AccountTransaction {

	AccountTransactionData data = new AccountTransactionData();

	/** Blank constructor. */
	public AccountTransaction()
	{
		this.data.date = "";
		this.data.type = "";	
		this.data.amount = 0;
		
	}
	
	/** Constructor with details. */
	public AccountTransaction(String date, String type, double amount)
	{
		this.data.date = date;
		this.data.type = type;
		
		
		this.data.amount = amount;
		
	}
	
	/** Accessor methods. */
	public String getDate()
	{
		return this.data.date;
	}
	
	public String getType()
	{
		return this.data.type;
	}

	public double getAmount()
	{
		return this.data.amount;
	}

	/** Mutator methods. */
	public void setDate(String date)
	{
		this.data.date = date;
	}
	
	public void setType(String type)
	{
		this.data.type = type;
	}
	
	public void setAmount(double amount)
	{
		this.data.amount = amount;
	}
	
	public String toString()
	{
		return  "\n" + "Date = " + this.data.date + "\n"
				+ "Type = " + this.data.type + "\n"
				+ "Amount = " + this.data.amount + "\n";
			
	}
	
}
