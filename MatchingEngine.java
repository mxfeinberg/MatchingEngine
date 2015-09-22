import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Integer;
import java.io.FileWriter;

public class MatchingEngine {
    TickerList buys; 
    TickerList sells;
    ArrayList<Match> matches;
     public static void main(String[] args) 
         {

	    long startTime = System.currentTimeMillis();
            MatchingEngine obj = new MatchingEngine();
            obj.run();
            obj.write("fill_final.txt");
	        System.out.println("Run Time: " + (System.currentTimeMillis() - startTime) + "ms ");

          }

          public void run() 
          {

            String csvFile = "orders.txt";
            BufferedReader br = null;
            String line = "";
            String csvSplitBy = ",";
            buys = new TickerList();
            sells = new TickerList();
            matches = new ArrayList<Match>();
            try 
            {
                    br = new BufferedReader(new FileReader(csvFile));
                    while ((line = br.readLine()) != null) 
                	{
                		String[] stockData = line.split(csvSplitBy);
                		StockOrder stock = new StockOrder(stockData[0], Integer.parseInt(stockData[1]), stockData[2], stockData[3], Integer.parseInt(stockData[4]), Double.parseDouble(stockData[5]));
                		process(stock);
                	}
                }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (br != null)
                {
                    try {
                            br.close();
                        }
                    catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                }
            }
          }
          public void write(String fileName)
          {              
              FileWriter writer = null;
              try
              {
                  writer = new FileWriter(fileName);
                  for(int i = 0; i < matches.size(); i++)
                  {
                	  double price = matches.get(i).getPrice();
                	  if(price - Math.floor(price) == 0)
                		  writer.append("FILL " + matches.get(i).getSymbol() + " " + matches.get(i).getBuySideOrderID() + " " + matches.get(i).getSellSideOrderID() + " " + matches.get(i).getQuantity()+ " " + (int)matches.get(i).getPrice() + "\n");    
                	  else
                		  writer.append("FILL " + matches.get(i).getSymbol() + " " + matches.get(i).getBuySideOrderID() + " " + matches.get(i).getSellSideOrderID() + " " + matches.get(i).getQuantity()+ " " + matches.get(i).getPrice() + "\n");
                  }         
              }
              catch (Exception e)
              {
                  e.printStackTrace();
              }
              finally
              {
                  try
                  {
                      writer.flush();
                      writer.close();
                  }
                  catch (IOException e)
                  {
                      e.printStackTrace();
                  }
              }
          }
public void process(StockOrder s)
{
   	String ticker = s.getTicker();
	double price = s.getPrice();
	if(s.getSide())//indicates a buy
	{
		if(sells.size() == 0 || sells.search(ticker) == null || sells.search(ticker).size() == 0)//nothing in sells
 		{
			buys.addBuy(s);
  		}
 		else
 		{
 			if(s.getOrder_Type() == 0)//Order
 			{
 				int location = sells.search(ticker).findSell(price);
 				if(location != -1)//tells us if match
 				{
					while(location != -1)
					{
 						matches.add(new Match(s, sells.search(ticker), location));
					if(s.getQuantity() > 0 )
						location = sells.search(ticker).findSell(price);
					else
						location = -1;
					}
 					if(s.getQuantity() > 0)
 							buys.addBuy(s);
 				}
				else
				{
					buys.addBuy(s);
				}
      			}
      		else if(s.getOrder_Type() == 1)//cancel
      		{
      			buys.search(ticker).cancel(s);
      		}
      		else//Replace
      		{
      			StockOrder temp = buys.search(ticker).replace(s);
      			if(temp != null)
      			{
      				temp.setOrder_Type("ORDER");
      				process(temp);
      			}
      		}
      				
      	}
	}
    else //indicates a sell
    {
      	if(buys.size() == 0 || buys.search(ticker) == null || buys.search(ticker).size() == 0)//nothing in sells
  		{
  			sells.add(s);
  		}
      	else
      	{
      		if(s.getOrder_Type() == 0)//order
      		{
      			int location = buys.search(ticker).findBuy(price);
      			if(location != -1)//there is a match
      			{
				while(location != -1)
				{
      					matches.add(new Match(buys.search(ticker), location, s));
      				if(s.getQuantity() > 0)
					location = buys.search(ticker).findBuy(price);
				else
					location = -1;
      				}
				if(s.getQuantity() > 0)
					sells.add(s);
			}
      			else
      			{
      					sells.add(s);
      			}
      		}
      		else if(s.getOrder_Type() == 1)//cancel
      		{
      			sells.search(ticker).cancel(s);
      		}
      		else //replace
      		{
      			StockOrder temp = sells.search(ticker).replace(s);
      			if(temp != null)
      			{
      			temp.setOrder_Type("ORDER");
      			process(temp);
      			}
      		}
      	}
      }
      		

}
}







