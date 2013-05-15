package tradehero.core;

import tradehero.IAPIResponseHandler;
import tradehero.core.auth.IAuthenticator;
import tradehero.json.RootClass;
import tradehero.json.User;

import com.google.gson.Gson;

public class THAPI {

	
	private static final String baseUrl ="https://tradehero.mobi/api/";
	private static THAPI thApi = null;
	IAuthenticator authenticator = null;
	User	authenticatedUser = null;
	
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
	
	public void addTHParams(THClient client)
	{
		client.addParam("clientiOS", "true");
        client.addParam("clientVersion", "1.4");
	}
	
	public static THAPI getInstance()
	{
		
		if(thApi != null) 
		{
			return thApi;
		}else{
			return new THAPI();
		}
		
	}
	
}
