import java.util.ArrayList;
public class TickerList extends ArrayList<StockList>
{
	public TickerList()
	{
		super();
	}
	public TickerList(int n)
	{
		super(n);
	}
	public StockList search(String name)
	{
		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i).getTicker().equalsIgnoreCase(name))
			{
				return this.get(i);
			}
		}
		return null;
	}
	/*
	 * smartly adds stock orders, must change to add ordering
	 */
	public void add(StockOrder s)
	{
		String ticker = s.getTicker();
		if(this.search(ticker) == null)
		{
			super.add(new StockList(ticker, 1));
			search(ticker).add(s);
			return;
		}
		else
		{
			int limit = this.search(ticker).size();
			for(int i = 0; i < limit; i++)
			{
				if(Math.abs(s.getPrice() - search(ticker).get(i).getPrice()) < 0.00000001) //change value for more precision
				{
					if(i+1 < limit)
					{
						if(Math.abs(s.getPrice() - search(ticker).get(i+1).getPrice()) > 0.00000001)
						{
							search(ticker).add(i+1,s);
							return;
						}
					}
				}
				else if(s.getPrice() < search(ticker).get(i).getPrice())
				{
					search(ticker).add(i, s);
					return;
				}
				
			}
			search(ticker).add(s);
		}
			//search(s.getTicker()).add(s);
	}
	public void addBuy(StockOrder s)
	{
		String ticker = s.getTicker();
		if(this.search(ticker) == null)
		{
			super.add(new StockList(ticker, 1));
			search(ticker).add(s);
			return;
		}
		else
		{
			int limit = this.search(ticker).size();
			for(int i = 0; i < limit; i++)
			{
				if(Math.abs(s.getPrice() - search(ticker).get(i).getPrice()) < 0.00000001) //change value for more precision
				{
					search(ticker).add(i,s);
					return;
				}
				else if(s.getPrice() < search(ticker).get(i).getPrice())
				{
					search(ticker).add(i, s);
					return;
				}
				
			}
			search(ticker).add(s);
		}
			//search(s.getTicker()).add(s);
	}
}
