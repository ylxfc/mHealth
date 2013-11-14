package com.yuli.mhealth;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
	private RadioGroup group;
	private TabHost tabHost;
	private static final String TAB_NEWS = "tabNews";
	private static final String TAB_ZICHA = "tabZicha";
	private static final String TAB_DOCTOR = "tabDoctor";
	private static final String TAB_TOOLS = "tabTools";
	private static final String TAB_PERSON = "tabPerson";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		group = (RadioGroup)findViewById(R.id.main_radio);
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec(TAB_NEWS)
				.setIndicator(TAB_NEWS)
				.setContent(new Intent(this, NewsActivity.class)));
		tabHost.addTab(tabHost.newTabSpec(TAB_ZICHA)
				.setIndicator(TAB_ZICHA)
				.setContent(new Intent(this, ZichaActivity.class)));
		tabHost.addTab(tabHost.newTabSpec(TAB_DOCTOR)
				.setIndicator(TAB_DOCTOR)
				.setContent(new Intent(this, DoctorActivity.class)));
		tabHost.addTab(tabHost.newTabSpec(TAB_TOOLS)
				.setIndicator(TAB_TOOLS)
				.setContent(new Intent(this, ToolsActivity.class)));
		tabHost.addTab(tabHost.newTabSpec(TAB_PERSON)
				.setIndicator(TAB_PERSON)
				.setContent(new Intent(this, PersonActivity.class)));
		
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch(checkedId) {
				case R.id.radio_button0:
					tabHost.setCurrentTabByTag(TAB_NEWS);
					break;
				case R.id.radio_button1:
					tabHost.setCurrentTabByTag(TAB_ZICHA);
					break;
				case R.id.radio_button2:
					tabHost.setCurrentTabByTag(TAB_DOCTOR);
					break;
				case R.id.radio_button3:
					tabHost.setCurrentTabByTag(TAB_TOOLS);
					break;
				case R.id.radio_button4:
					tabHost.setCurrentTabByTag(TAB_PERSON);
					break;
				default:
					break;
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
