/**
 * 
 */
package com.yuli.mhealth.zicha;

<<<<<<< HEAD
import com.yuli.mhealth.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
=======
import android.app.Activity;
import android.os.Bundle;
>>>>>>> 7d82a7a3d3640073cd3383592dcb11dbfc4e8a10

/**
 * @author yuli
 *
 */
public class JiBingSearch extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
<<<<<<< HEAD
	private TextView jibing1;
	private TextView jibing2;
	private TextView jibing3;
=======
>>>>>>> 7d82a7a3d3640073cd3383592dcb11dbfc4e8a10
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
<<<<<<< HEAD
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.zicha_jibing);

		jibing1 = (TextView)findViewById(R.id.jibing1);
		jibing2 = (TextView)findViewById(R.id.jibing2);
		jibing3 = (TextView)findViewById(R.id.jibing3);
		
		jibing1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(JiBingSearch.this, XiangxiContent.class);
				Bundle bundle = new Bundle();
				bundle.putString("info", jibing1.getText().toString());
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
		jibing2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(JiBingSearch.this, XiangxiContent.class);
				Bundle bundle = new Bundle();
				bundle.putString("info", jibing2.getText().toString());
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
		jibing3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(JiBingSearch.this, XiangxiContent.class);
				Bundle bundle = new Bundle();
				bundle.putString("info", jibing3.getText().toString());
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
=======

>>>>>>> 7d82a7a3d3640073cd3383592dcb11dbfc4e8a10
	}

}
