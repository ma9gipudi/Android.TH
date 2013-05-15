package tradehero.core;

import tradehero.IAPIResponseHandler;
import tradehero.core.auth.IAuthenticator;
import tradehero.core.net.RequestMethod;
import tradehero.core.net.RestClient;
import tradehero.core.net.RestError;
import tradehero.json.ProfileDTO;
import tradehero.json.RootClass;
import tradehero.json.User;

import com.google.gson.Gson;



public class THClient  {

	public int responseCode;
	public String message;
	public String response;
	
	private RestClient restClient =null;
	
	
	public THClient(String url)
	{
		restClient = new RestClient(url);
	}
	
	public void execute (IAuthenticator auth)
	{
		String email = auth.email;
		String password = auth.password;
	
		try {
			restClient.Execute(RequestMethod.POST,email,password);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
	}
	
	public void addParam(String key, String value)
	{
		restClient.AddParam(key, value);
	}
	
	public void login(IAuthenticator auth,IAPIResponseHandler<User> handler)
	{
		execute(auth);
		String response = restClient.getResponse();
		Gson gson = new Gson();
        RootClass root = gson.fromJson(response, RootClass.class);
        ProfileDTO profile = root.profileDTO;
        User u = createUserFromDto(profile);
        
        if( responseCode < 400  )
		{
        	/* success*/
        	THAPI.getInstance().authenticator = auth;
        	THAPI.getInstance().authenticatedUser = u;
        	
        	handler.onSuccess(u);
			
		}else{
			/*failure */
			RestError restError = new RestError(responseCode,message,response);
			handler.onFailure(restError);
		}

	}

	private User createUserFromDto(ProfileDTO profile) {
		User u = new User();
		u.email = profile.email;
		return u;
	}
}
