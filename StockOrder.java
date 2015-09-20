import java.util.ArrayList;
public class StockOrder
{
	private int Order_Type;//0 is order, 1 is cancel, 2 is replace
	private int OrderID;//unique id
	private String symbol;//security identifier
	private boolean side; //buy is true, sell is false
	private int quantity;//amount of shares
	private double price;//price of stock
	private int oldQuantity;
	private double oldPrice;
    
	public StockOrder(String orderT, int orderI, String sym, String side, int quant, double p)
	{
		if(orderT.equalsIgnoreCase("ORDER"))
			Order_Type = 0;
		else if(orderT.equalsIgnoreCase("CANCEL"))
			Order_Type = 1;
		else
			Order_Type = 2;
		this.setOrderID(orderI);
		this.setSymbol(sym);
		if(side.equalsIgnoreCase("B"))
			setSide(true);
		else
			setSide(false);
		this.setQuantity(quant);
		this.setPrice(p);
		this.setOldPrice(0);
		this.setOldQuantity(0);
		
		
	}
	public int getOrder_Type() {
		return Order_Type;
	}
	public void setOrder_Type(String orderT) {
		if(orderT.equalsIgnoreCase("ORDER"))
			Order_Type = 0;
		else if(orderT.equalsIgnoreCase("CANCEL"))
			Order_Type = 1;
		else
			Order_Type = 2;
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public String getTicker() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public boolean isSide() {
		return side;
	}
	public boolean getSide()
	{
		return this.side;
	}
	public void setSide(boolean side) {
		this.side = side;
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
	public int getOldQuantity() {
		return oldQuantity;
	}
	public void setOldQuantity(int oldQuantity) {
		this.oldQuantity = oldQuantity;
	}
	public double getOldPrice(double oldPrice)
	{
		return oldPrice;
	}
	public void setOldPrice(int oldPrice) {
		this.oldPrice = oldPrice;
	}

}
