package com.yuli.mhealth.zicha;

import java.util.ArrayList;
import java.util.HashMap;

import com.yuli.mhealth.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;
import android.widget.SimpleAdapter;

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
		for(int i=1;i<3;i++) {
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
}
