package com.yuli.mhealth.xiaotool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amap.mapapi.core.AMapException;
import com.amap.mapapi.map.RouteOverlay;
import com.amap.mapapi.route.DriveWalkSegment;
import com.amap.mapapi.route.Route;
import com.amap.mapapi.route.Route.FromAndTo;
import com.yuli.mhealth.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class RoutePlan extends Activity{

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.hospitallist);
	    //显示搜索结果列表
	    ListView listview = (ListView) findViewById(R.id.listView);
	    Intent intent = getIntent();
	    String[] msgName = intent.getStringArrayExtra("msgName");
	    String[] msgNum = intent.getStringArrayExtra("msgNum");
	    ArrayList<HashMap<String, Object>> lstMsg = new ArrayList<HashMap<String, Object>>(); 
	    
	    for(int i = 0;i < msgName.length;i++)  
	    {  
	        HashMap<String, Object> map = new HashMap<String, Object>();  
	        map.put("hosNmae", msgName[i]);  
	        map.put("hosNum", msgNum[i]);   
	        lstMsg.add(map);  
	    }

	    SimpleAdapter saListMsg = new SimpleAdapter(RoutePlan.this,lstMsg,R.layout.listview,
	    		new String[] {"hosNmae","hosNum"},new int[] {R.id.hosName,R.id.hosNum});  
	    //添加并且显示   
	    listview.setAdapter(saListMsg);
	    //添加消息处理   
        listview.setOnItemClickListener(new ListView.OnItemClickListener()
        {
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
        	{
        		Intent intent = new Intent(RoutePlan.this,searchHospital.class);
        		intent.putExtra("number", arg2);
        		startActivity(intent);
        	}
        });
	}
}
