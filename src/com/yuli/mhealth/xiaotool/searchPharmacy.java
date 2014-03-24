package com.yuli.mhealth.xiaotool;

import com.amap.mapapi.map.MapActivity;
import com.yuli.mhealth.R;

import android.content.Intent;
import android.os.Bundle;

public class searchPharmacy extends MapActivity{
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapsearch);
		Intent intent = new Intent(searchPharmacy.this,searchHospital.class);
		Bundle extras = new Bundle();
		extras.putString("interest", "Ò©µê");
		intent.putExtras(extras);
		startActivity(intent);
	}
}