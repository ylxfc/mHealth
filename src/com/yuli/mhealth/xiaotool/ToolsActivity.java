package com.yuli.mhealth.xiaotool;



import java.util.ArrayList;
import java.util.HashMap;

import com.yuli.mhealth.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class ToolsActivity extends Activity {

	private String[] itemText={"医院搜寻","药店搜寻","预约挂号","健康管家"};
	private int[] itemImage={};//图标数组
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GridView gridview = (GridView) findViewById(R.id.gridview);
		
		//生成动态数组，并且转入数据   
        ArrayList<HashMap<String, Object>> lstItem = new ArrayList<HashMap<String, Object>>();  
        for(int i=0;i<itemText.length;i++)  
        {  
          HashMap<String, Object> map = new HashMap<String, Object>();  
          map.put("ItemImage", R.drawable.ic_launcher);//添加图像资源的ID   
          map.put("ItemText", itemText[i]);//按序号做ItemText   
          lstItem.add(map);  
        }
        
        //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应   
        SimpleAdapter saImageItems = new SimpleAdapter(this,lstItem,R.layout.gridmenu_item,new String[] {"ItemImage","ItemText"},new int[] {R.id.ItemImage,R.id.ItemText});  
        //添加并且显示   
        gridview.setAdapter(saImageItems);  
        //添加消息处理   
        gridview.setOnItemClickListener(new GridView.OnItemClickListener()
        {
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
        	{
        		switch (arg2)
        		{
        			case 0:
        				Intent intent1=new Intent(ToolsActivity.this,searchHospital.class);
        				startActivity(intent1);
        				break;
        			case 1:
        				Intent intent2=new Intent(ToolsActivity.this,searchPharmacy.class);
        				startActivity(intent2);
        				break;
        			case 2:
        				Intent intent3=new Intent(ToolsActivity.this,dial.class);
        				startActivity(intent3);
        				break;
        			case 3:
        				
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
