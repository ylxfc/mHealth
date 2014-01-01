package com.yuli.mhealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class Welcome extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                    Intent intent = new Intent(Welcome.this, MainActivity.class);
                    startActivity(intent);
                    Welcome.this.finish();
            }
    }, 3000);
	}

}
