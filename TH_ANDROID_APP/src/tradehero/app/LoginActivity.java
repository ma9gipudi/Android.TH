package tradehero.app;

import tradehero.api.IAPIResponseHandler;
import tradehero.api.THAPI;
import tradehero.core.auth.BasicAuthenticator;
import tradehero.core.auth.IAuthenticator;
import tradehero.core.net.RestError;
import tradehero.json.User;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class LoginActivity extends Activity {

	private LoginActivity loginActivity ;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		loginActivity = this;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void onLoginClick(View view)
	{
		THAPI api = THAPI.getInstance();
		String userEmail ="grimaultjulien@gmail.com";
		String userPassword = "123123";
		IAuthenticator auth = new BasicAuthenticator(userEmail,userPassword);
		new LoginTask().execute(auth);
	}

	class LoginTask extends AsyncTask <IAuthenticator,Integer,Long>
	{
		@Override
		protected Long doInBackground(IAuthenticator... params) 
		{
			THAPI api = THAPI.getInstance();
			api.login(params[0], new LoginHandler());
			return null;
		}
	}
	class LoginHandler implements IAPIResponseHandler <User>
	{

		@Override
		public void onSuccess(User u) {
			Intent intent = new Intent(loginActivity,UserSummaryActivity.class);
			startActivity(intent);
			
		}
		@Override
		public void onFailure(RestError error) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
