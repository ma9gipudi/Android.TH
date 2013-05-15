package tradhero.test;

import tradehero.IAPIResponseHandler;
import tradehero.core.THAPI;
import tradehero.core.auth.BasicAuthenticator;
import tradehero.core.net.RestError;
import tradehero.json.User;

public class RestTest {

	public void makeRestCall()
	{
		LoginResponseHandler handler = new LoginResponseHandler();
		THAPI api = THAPI.getInstance();
		BasicAuthenticator auth = new BasicAuthenticator("grimaultjulien@gmail.com","123123");
		api.login(auth, handler );
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
