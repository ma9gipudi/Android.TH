package tradehero;

import java.util.List;

import tradehero.core.THClient;
import tradehero.core.auth.BasicAuthenticator;
import tradehero.core.auth.IAuthenticator;
import tradehero.core.net.RestError;
import tradehero.json.Security;
import tradehero.json.User;

public class THAPI {

	
	//private static final String baseUrl ="https://tradehero.mobi/api/";
	private static final String baseUrl ="https://localhost:44300/api/";
	private static THAPI thApi = null;
	public IAuthenticator authenticator = null;
	public User	authenticatedUser = null;
	public List<Security> trendingSecurities = null;
	
	private THAPI()
	{
		
	}
	
	public void login(IAuthenticator auth, IAPIResponseHandler<User> _handler)
	{
		String loginUrl=baseUrl+"login/";
		THClient client = new THClient(loginUrl);
		addTHParams(client);
		client.login(auth,_handler);
	}
	
	//public void trending(BasicAuthenticator auth, IAPIResponseHandler<Security> _handler)
	public void trending(IAPIResponseHandler<List<Security>> _handler)
	{
		String trendingUrl=baseUrl+"securities/trending/";
		THClient client = new THClient(trendingUrl);
		addTHParams(client);
		if( authenticator != null && authenticatedUser != null)
		{
			client.trending(authenticator,_handler);
		}else{
			
			RestError error = new RestError(403, "Forbidden", "Unauthorized! Please login first.");
			_handler.onFailure(error);
		}
		
	}
	
	public void addTHParams(THClient client)
	{
		client.addParam("clientiOS", "true");
        client.addParam("clientVersion", "1.4");
	}
	
	public static THAPI getInstance()
	{
		if(thApi==null)
		{
			thApi= new THAPI();
		}		
		return thApi;
	}
	
}
