package tradehero.app;

import java.util.List;

import tradehero.api.IAPIResponseHandler;
import tradehero.api.THAPI;
import tradehero.core.net.RestError;
import tradehero.json.Security;
import tradehero.json.User;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class UserSummaryActivity extends Activity {

	private UserSummaryActivity userSummaryActivity = null;
	private User user = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_summary);
		
		THAPI api = THAPI.getInstance();
		User user = api.authenticatedUser;
		int idUserEmail = R.id.userEmail;
		TextView userEmailView = (TextView)findViewById(idUserEmail);
		userEmailView.setText(user.displayName);
		userSummaryActivity = this;
	}
	
	protected void onLaunch()
	{
		int idUserEmail = R.id.userEmail;
		TextView userEmailView = (TextView)findViewById(idUserEmail);
		userEmailView.setText(user.email);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_summary, menu);
		return true;
	}
	

    public void getTrendingSecurities(View view)
    {
    	new TrendingTask().execute();
    }
    
    class TrendingTask extends AsyncTask
    {
		@Override
		protected Object doInBackground(Object... params) {
			THAPI api = THAPI.getInstance();
			api.trending(new TrendingResponseHandler());
			return null;
		}
    	
    }
    
    class TrendingResponseHandler implements IAPIResponseHandler <List<Security>>
    {
		@Override
		public void onSuccess(List<Security> obj) {
			Intent intent = new Intent(userSummaryActivity,TrendingSecuritiesActivity.class);
			startActivity(intent);
			
		}

		@Override
		public void onFailure(RestError error) {
			// TODO Auto-generated method stub
			
		}
    	
    }

}
