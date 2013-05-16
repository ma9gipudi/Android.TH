package tradehero.json;
import tradehero.json.dto.*;

public class Security {

	public int id;
	public String symbol;
	public String name;
	public String exchange;
	public Double lastPrice;
	public Double bidPrice;
	public Double askPrice;
	
	public Security()
	{
		
	}
	
	public Security(SecurityDTO secDto)
	{
		id = secDto.id;
		symbol=secDto.symbol;
		name = secDto.name;
		exchange=secDto.exchange;
		lastPrice=secDto.lastPrice;
		bidPrice = secDto.bidPrice;
		askPrice = secDto.askPrice;
				
	}
	
}
