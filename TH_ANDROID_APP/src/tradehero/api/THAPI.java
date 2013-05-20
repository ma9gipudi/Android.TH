package tradehero.api;

import java.util.List;

import tradehero.core.THClient;
import tradehero.core.auth.IAuthenticator;
import tradehero.core.net.RestError;
import tradehero.json.Security;
import tradehero.json.User;

/*
 * 
 * Singleton single point of access to all API for the client app.
 * 
 * Maintains authentication details and authenticated User object.
 * 
 *  
 * 
 */

public class THAPI
{
	public User			  authenticatedUser  = null;
	public List<Security> trendingSecurities = null;
	
	private IAuthenticator authenticator 	 = null;
    private static final String baseUrl 	 = "https://tradehero.mobi/api/";
	//private static final String baseUrl 	 = "https://localhost:44300/api/";
	private static 		 THAPI  thApi 		 = null;
	private static final Object lock 		 = new Object();

	public static THAPI getInstance()
	{	
		if(thApi ==null)
		{
			synchronized(lock)
			{
				if (thApi == null)
				{
					thApi = new THAPI();
				}
			}
		}
		
		return thApi;
	}
	private THAPI() {}
	
	// caller must pass in populated authenticator object
	public void login(final IAuthenticator auth, final IAPIResponseHandler<User> _handler)
	{
		String loginUrl = baseUrl + "login/";
		THClient client = new THClient(loginUrl);
		addTHParams(client);
		
		// TODO: make aysnc -- on all APIs
		client.login(auth, new IAPIResponseHandler<User>() {
							   	public void onSuccess(User obj)
							   	{
							   		authenticator = auth;
							   		authenticatedUser = obj;
							   		_handler.onSuccess(authenticatedUser);
							   	}
							   	public void onFailure(RestError error)
							   	{
							   		authenticator = null;
							   		authenticatedUser = null;
							   		_handler.onFailure(error);
							   	}
						   	} );		
	}
	
	public void addTHParams(THClient client)
	{
		client.addParam("clientiOS", "true");
        client.addParam("clientVersion", "1.4");
	}	
	
	public void trending(IAPIResponseHandler<List<Security>> _handler)
	{
		String trendingUrl = baseUrl + "securities/trending/";
		THClient client = new THClient(trendingUrl);
		addTHParams(client);
		if ( authenticator != null && authenticatedUser != null)
		{
			client.trending(authenticator,_handler);
		}
		else
		{
			RestError error = new RestError(403, "Forbidden", "Unauthorized! Please login first.");
			_handler.onFailure(error);
		}
	}

}
