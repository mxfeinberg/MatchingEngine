
public class Match 
{
	private String symbol;
	private int buySideOrderID;
	private int sellSideOrderID;
	private int quantity;
	private double price;
	//for sell
	public Match(StockList tb, int i, StockOrder sell)
	{
		this.symbol = sell.getTicker();
		this.buySideOrderID = tb.get(i).getOrderID();
		this.sellSideOrderID = sell.getOrderID();
		this.price = tb.get(i).getPrice();
		if(sell.getQuantity() > tb.get(i).getQuantity())
		{	
			this.quantity = tb.get(i).getQuantity();
			sell.setQuantity(sell.getQuantity() - quantity);
			tb.remove(i);//removes filled order
		}
		else if(sell.getQuantity() < tb.get(i).getQuantity())
		{	
			this.quantity = sell.getQuantity();
			tb.get(i).setQuantity(tb.get(i).getQuantity() - quantity);
			sell.setQuantity(0);
		}
		else
		{
			this.quantity = sell.getQuantity();
			sell.setQuantity(0);
			tb.remove(i);
		}
	}
	//buy version
	public Match(StockOrder o, StockList ts, int j)
	{
		this.symbol = o.getTicker();
		this.buySideOrderID = o.getOrderID();
		this.sellSideOrderID = ts.get(j).getOrderID();
		this.price = ts.get(j).getPrice();
		if(o.getQuantity() > ts.get(j).getQuantity())
		{	
			this.quantity = ts.get(j).getQuantity();
			o.setQuantity(o.getQuantity() - quantity);
			ts.remove(j);//removes filled order
		}
		else if(o.getQuantity() < ts.get(j).getQuantity())
		{	
			this.quantity = o.getQuantity();
			ts.get(j).setQuantity(ts.get(j).getQuantity() - quantity);
			o.setQuantity(0);
		}
		else
		{
			this.quantity = o.getQuantity();
			o.setQuantity(0);
			ts.remove(j);
		}
	}
	/*
	 * makes match, deletes necessary orders
	 */
	public Match(StockList tb, StockList ts, int i, int j)
	{
		this.symbol = tb.get(i).getTicker();
		this.buySideOrderID = tb.get(i).getOrderID();
		this.sellSideOrderID = ts.get(j).getOrderID();
		this.price = tb.get(i).getPrice();
		if(tb.get(i).getQuantity() > ts.get(j).getQuantity())
		{	
			this.quantity = ts.get(j).getQuantity();
			tb.get(i).setQuantity(tb.get(i).getQuantity() - quantity);
			ts.remove(j);//removes filled order
		}
		else if(tb.get(i).getQuantity() < ts.get(j).getQuantity())
		{	
			this.quantity = tb.get(i).getQuantity();
			ts.get(j).setQuantity(ts.get(j).getQuantity() - quantity);
			tb.remove(i);//removes filled order
		}
		else
		{
			this.quantity = tb.get(i).getQuantity();
			tb.remove(i);
			ts.remove(j);
		}
	}
	public Match(String s, int buySide, int sellSide, int quant, double p)
	{
		this.symbol = s;
		this.setBuySideOrderID(buySide);
		this.setSellSideOrderID(sellSide);
		this.setQuantity(quant);
		this.setPrice(p);
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getBuySideOrderID() {
		return buySideOrderID;
	}
	public void setBuySideOrderID(int buySideOrderID) {
		this.buySideOrderID = buySideOrderID;
	}
	public int getSellSideOrderID() {
		return sellSideOrderID;
	}
	public void setSellSideOrderID(int sellSideOrderID) {
		this.sellSideOrderID = sellSideOrderID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
