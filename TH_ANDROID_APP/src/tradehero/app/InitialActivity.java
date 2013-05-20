package tradehero.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;




/**
 * Created by skhro87 on 16/5/13.
 *
 * Start screen of TradeHero
 *
 */
public class InitialActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);


    }


   public void onNewUserClick(View view)
   {
       // go to activity for creating new user
   }

    public void onExistingUserClick(View view)
    {
        Intent intent = new Intent (this, SelectLoginTypeActivity.class);
        startActivity(intent);
    }



}
