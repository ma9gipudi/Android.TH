package tradehero.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/**
 * Created by tradehero on 16/5/13.
 *
 * Select to login with facebook, twitter, linkedin or email
 */
public class SelectLoginTypeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login_type);


    }

    public void onEmailLoginClick(View view)
    {
        Intent intent = new Intent (this, LoginActivity.class);
        startActivity(intent);
    }


}
