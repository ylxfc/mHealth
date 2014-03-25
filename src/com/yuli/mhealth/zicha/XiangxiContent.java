package com.yuli.mhealth.zicha;

import com.yuli.mhealth.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class XiangxiContent extends Activity {

	private TextView title;
	private TextView content_zhengzhuang;
	private TextView content_jiancha;
	private TextView content_zhiliao;
	private TextView content_yufang;
	private final String[] JibingFlag = new String[] {"°×ñ°·ç", "°×ÄÚÕÏ", "±Ç¹Ç¹ÇÕÛ"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_set_trafficstat);
		
		title = (TextView)findViewById(R.id.titleBar);
		content_zhengzhuang = (TextView)findViewById(R.id.content_zhengzhuang);
		content_jiancha = (TextView)findViewById(R.id.content_jiancha);
		content_zhiliao = (TextView)findViewById(R.id.content_zhiliao);
		content_yufang = (TextView)findViewById(R.id.content_yufang);
		
		Bundle bundle = this.getIntent().getExtras();
		String temp = bundle.getString("info");
		if(temp.equals(JibingFlag[0])) {
			title.setText(temp);
			content_zhengzhuang.setText(getResources().getString(R.string.b_baidianfeng_z));
			content_jiancha.setText(getResources().getString(R.string.b_baidianfeng_j));
			content_zhiliao.setText(getResources().getString(R.string.b_baidianfeng_zl));
			content_yufang.setText(getResources().getString(R.string.b_baidianfeng_yf));
		}
		if(temp.equals(JibingFlag[1])) {
			title.setText(temp);
			content_zhengzhuang.setText(getResources().getString(R.string.b_baidianfeng_z));
			content_jiancha.setText(getResources().getString(R.string.b_baidianfeng_j));
			content_zhiliao.setText(getResources().getString(R.string.b_baidianfeng_zl));
			content_yufang.setText(getResources().getString(R.string.b_baidianfeng_yf));
		}
		if(temp.equals(JibingFlag[2])) {
			title.setText(temp);
			content_zhengzhuang.setText(getResources().getString(R.string.b_baidianfeng_z));
			content_jiancha.setText(getResources().getString(R.string.b_baidianfeng_j));
			content_zhiliao.setText(getResources().getString(R.string.b_baidianfeng_zl));
			content_yufang.setText(getResources().getString(R.string.b_baidianfeng_yf));
		}
	}

}
