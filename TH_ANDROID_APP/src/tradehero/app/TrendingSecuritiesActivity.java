package tradehero.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tradehero.api.IAPIResponseHandler;
import tradehero.api.THAPI;
import tradehero.core.auth.IAuthenticator;
import tradehero.core.net.RestError;
import tradehero.json.Security;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TrendingSecuritiesActivity extends BaseActivity {

	private TrendingSecuritiesActivity trendingSecuritiesActivity = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trending_securities);
		trendingSecuritiesActivity = this;
		loadTrendingSecurities();
	}
	
	private void loadTrendingSecurities()
	{
		
		THAPI api = THAPI.getInstance();
		List<Security>  securities = api.trendingSecurities;
		List<String> securityNames = new ArrayList<String>();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trending_securities, menu);
		return true;
	}
	
	
}
