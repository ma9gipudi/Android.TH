package tradehero.test;

import java.util.List;

import tradehero.IAPIResponseHandler;
import tradehero.THAPI;
import tradehero.core.auth.BasicAuthenticator;
import tradehero.core.net.RestError;
import tradehero.json.Security;
import tradehero.json.User;

public class RestTest {

	public void makeRestCall()
	{
		LoginResponseHandler handler = new LoginResponseHandler();
		TrendingResponseHandler tHandler = new TrendingResponseHandler();
		THAPI api = THAPI.getInstance();
		BasicAuthenticator auth = new BasicAuthenticator("grimaultjulien@gmail.com","123123");
		api.login(auth, handler );
		api.trending(tHandler);
	}
	
	public static void main(String[] args)
	{
		RestTest test = new RestTest();
		test.makeRestCall();
	}
	
}

class LoginResponseHandler implements IAPIResponseHandler <User>
{
	@Override
	public void onSuccess(User u) {
		
		System.out.println(u.email);
		
	}

	@Override
	public void onFailure(RestError error) {
		// TODO Auto-generated method stub
		
	}
	
}

class TrendingResponseHandler implements IAPIResponseHandler <List<Security>>
{
	@Override
	public void onSuccess(List<Security> u) {
		
		System.out.println("Success");
		
	}

	@Override
	public void onFailure(RestError error) {
		System.out.println("Failure");
		
	}
	
}
