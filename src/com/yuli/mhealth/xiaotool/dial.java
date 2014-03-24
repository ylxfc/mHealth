package com.yuli.mhealth.xiaotool;

//电话预约挂号
import java.util.ArrayList;
import java.util.HashMap;

import com.yuli.mhealth.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class dial extends Activity{

	private String[] msgName={"武昌医院","武汉市第三医院","新江南医院","武汉和平嘉仁医院"};//医院名
	private String[] msgNum={"329029","239049","294810","938291"};//对应医院的电话号码
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState); 
	    setContentView(R.layout.hospitallist);
	    //显示医院列表
	    ListView listview = (ListView) findViewById(R.id.listView);
		
	    ArrayList<HashMap<String, Object>> lstMsg = new ArrayList<HashMap<String, Object>>();  
	    for(int i=0;i<msgName.length;i++)  
	    {  
	        HashMap<String, Object> map = new HashMap<String, Object>();  
	        map.put("hosNmae", msgName[i]);  
	        map.put("hosNum", msgNum[i]);   
	        lstMsg.add(map);  
	    }
	    //设置监听函数
	    SimpleAdapter saListMsg = new SimpleAdapter(this,lstMsg,R.layout.listview,new String[] {"hosNmae","hosNum"},new int[] {R.id.hosName,R.id.hosNum});  
	    //添加并且显示   
	    listview.setAdapter(saListMsg);
	    //添加消息处理   
        listview.setOnItemClickListener(new ListView.OnItemClickListener()
        {
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
        	{
        		String number=msgNum[arg2];
        		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ number));
        		startActivity(intent);
        	}
        });
	}
}