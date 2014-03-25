package com.yuli.mhealth.zicha;

import java.util.ArrayList;
import java.util.HashMap;

import com.yuli.mhealth.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ZichaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.modzicha);
        
        views();
	}

	private void views() {
		// TODO Auto-generated method stub
		GridView gridview = (GridView)findViewById(R.id.gridview);
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();   
		for(int i=1;i<5;i++) {
			HashMap<String, Object> map = new HashMap<String, Object>(); 
			if(i==1) {
				map.put("ItemImage", R.drawable.zicha_zhengzhuang);
				map.put("ItemText", "症状自查");
			}
			if(i==2) {
				map.put("ItemImage", R.drawable.zicha_jibing);
				map.put("ItemText", "疾病自查");
			}
			if(i==3) {
				map.put("ItemImage", R.drawable.zicha_yaopin);
				map.put("ItemText", "药品自查");
			}
			if(i==4) {
				map.put("ItemImage", R.drawable.zicha_more);
				map.put("ItemText", "更多");
			}
			lstImageItem.add(map); 
		}
		SimpleAdapter saImageItems = new SimpleAdapter(this, 
                lstImageItem, 
                R.layout.grid_item,      
                new String[] {"ItemImage","ItemText"},    
                new int[] {R.id.ItemImage,R.id.ItemText}); 
		gridview.setAdapter(saImageItems);   
		gridview.setOnItemClickListener(new ItemClickListener());
	}
	
	class  ItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			// TODO Auto-generated method stub
			HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(arg2);   
			
			if(item.get("ItemText").equals("症状自查")) {
				Toast.makeText(ZichaActivity.this, "症状自查", Toast.LENGTH_LONG).show();
				GotoZhengzhuang();
			}
			if(item.get("ItemText").equals("疾病自查")) {
				Toast.makeText(ZichaActivity.this, "疾病自查", Toast.LENGTH_LONG).show();
				GotoJibing();
			}
			if(item.get("ItemText").equals("药品自查")) {
				Toast.makeText(ZichaActivity.this, "药品自查", Toast.LENGTH_LONG).show();
				GotoYaopin();
			}
			if(item.get("ItemText").equals("更多")) {
				Toast.makeText(ZichaActivity.this, "更多", Toast.LENGTH_LONG).show();
				GotoGengduo();
			}
		}
		
	}

	public void GotoZhengzhuang() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(ZichaActivity.this, ZhengzhuangSearch.class);
		startActivity(intent);
	}

	public void GotoGengduo() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(ZichaActivity.this, MoreZichaMod.class);
		startActivity(intent);
	}

	public void GotoYaopin() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(ZichaActivity.this, YaoPinSearch.class);
		startActivity(intent);
	}

	public void GotoJibing() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(ZichaActivity.this, JiBingSearch.class);
		startActivity(intent);
	}
}