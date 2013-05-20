package tradehero.core;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tradehero.api.IAPIResponseHandler;
import tradehero.api.THAPI;
import tradehero.core.auth.IAuthenticator;
import tradehero.core.net.RequestMethod;
import tradehero.core.net.RestClient;
import tradehero.core.net.RestError;
import tradehero.json.Security;
import tradehero.json.User;
import tradehero.json.dto.ProfileDTO;
import tradehero.json.dto.RootClass;
import tradehero.json.dto.SecurityDTO;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.*;

public class THClient
{
	public int    responseCode;
	public String message;
	public String response;
	
	private RestClient restClient = null;
	
	public THClient(String url)
	{
		restClient = new RestClient(url);
	}
	
	public void execute(RequestMethod reqMethod, IAuthenticator auth)
	{
		String email = auth.email;
		String password = auth.password;
	
		try
		{
			restClient.Execute(reqMethod,email,password);
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
	}

	public void addParam(String key, String value)
	{
		restClient.AddParam(key, value);
	}
	
	public void login(IAuthenticator authenticator, IAPIResponseHandler<User> handler)
	{
		execute(RequestMethod.POST, authenticator);
		String response = restClient.getResponse();
		Type type = new TypeToken<RootClass>(){}.getType();
		if (responseCode > 400)
		{
			RestError error = new RestError(responseCode, message, response);
			handler.onFailure(error);
			return;
		}
		
		try
		{
			RootClass root = deserializeResponse(response, type);
			ProfileDTO profile = root.profileDTO;
			User user = createUserFromDto(profile);
        	handler.onSuccess(user);
        	return;
		}
		catch (Exception e)
		{
			System.out.println("Exception parsing JSON");
			response = "Error parsing JSON";
			RestError restError = new RestError(responseCode,message,response);
			handler.onFailure(restError);
			e.printStackTrace();
			return;
		}
	}
	
	public void trending(IAuthenticator authenticator,IAPIResponseHandler<List<Security>> _handler)
	{
		execute(RequestMethod.GET,authenticator);
		String response = restClient.getResponse();
		if (responseCode > 400) 
		{
			RestError error = new RestError(responseCode, message, response);
			_handler.onFailure(error);
			return ;
		}
		
        try
        {
        	Type type = new TypeToken<ArrayList<SecurityDTO>>(){}.getType();
            List<SecurityDTO> secDto = deserializeResponse(response,type);
            List<Security> sec = createSecurityListFromDTO(secDto);
            THAPI.getInstance().trendingSecurities = sec;
            _handler.onSuccess(sec);
        }
        catch(Exception e)
        {
        	// TODO: use proper logging -- log4j (different error levels)
        	// TODO: crashlytics ++
        	
        	System.out.println("Exception parsing JSON");
			response = "Error parsing JSON";
			RestError restError = new RestError(responseCode, message, response);
			_handler.onFailure(restError);
			e.printStackTrace();
        }
	}
		
	private List<Security> createSecurityListFromDTO(List<SecurityDTO> secDto)
	{		
		List<Security> security = new ArrayList<Security>();
		Iterator i = secDto.iterator();
		while (i.hasNext())
		{
			security.add(new Security((SecurityDTO)i.next()));
		}
		return security;
	}

	private User createUserFromDto(ProfileDTO profileDto)
	{
		return new User(profileDto);
	}
	
	public <T> T deserializeResponse(String response, Type t) throws Exception
	{	
		Gson gson = new Gson();
		return  gson.fromJson(response, t);
	}
}
	
	