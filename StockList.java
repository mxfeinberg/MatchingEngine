import java.util.ArrayList;
public class StockList extends ArrayList<StockOrder>
{
	private String ticker;
	public StockList(String t, int n)
	{
		super(n);
		this.setTicker(t);
	}
	/*
	 * searches sell list for price at or below desired point
	 * @return -1 if no sell available
	 */
	public int findSell(double p)
	{	
		/*for(int i = 0; i < size(); i++) If the first element of the book isn't cheap enough, nothing will be
		{
			if(get(i).getPrice() <= p)
			{
				return i;
			}
		}*/
		if(get(0).getPrice() <= p)
			return 0;
		else
			return -1;
	}
	/*
	 * searches buy list for price at or ABOVE the desired point
	 * @return -1 if no buy available
	 */
	public int findBuy(double p)
	{
		if(get(size()-1).getPrice() >= p)
			return(size()-1);
		return -1;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	/*
	 * method for canceling a stock order
	 */
	public boolean cancel(StockOrder s)
	{
		int location = this.searchByID(s);
		if(location != -1)
		{
			this.remove(location);
			return true;
		}
		else
		{
			return false;
		}
	}
	/*
	 * method for find the location of a stock in a stock list by ID name
	 */
	public int searchByID(StockOrder s)
	{
		for(int i = 0; i < size(); i++)
		{
			if(this.get(i).getOrderID() == s.getOrderID())
			{
				return i;
			}
		}
		return -1;
	}
	/*
	 * 
    If the price is the same and the quantity goes down, this can be thought of as a partial cancel (cancel down). 
    Queue position is maintained in this case.
    If the price is different or the quantity increases, this should be handled as a Cancel of the previous order and 
    a new ORDER order should be made. Queue position isn't maintained in this case.

	 */
	public StockOrder replace(StockOrder s)
	{
		int location = this.searchByID(s);
		double newPrice = s.getPrice();
		int newQuantity = s.getQuantity();
		if(location != -1)
		{
			int q = this.get(location).getQuantity();
			if(Math.abs(newPrice - this.get(location).getPrice()) < .0001)
			{
				this.get(location).setOldQuantity(q);
				this.get(location).setQuantity(newQuantity);
				return null;
			}
			else
			{
				this.cancel(s);//not perfectly efficient consider revising
				return s;
			}
		}
		return null;
	}
}
