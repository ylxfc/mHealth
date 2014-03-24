package com.yuli.mhealth.xiaotool;

//�绰ԤԼ�Һ�
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

	private String[] msgName={"���ҽԺ","�人�е���ҽԺ","�½���ҽԺ","�人��ƽ����ҽԺ"};//ҽԺ��
	private String[] msgNum={"329029","239049","294810","938291"};//��ӦҽԺ�ĵ绰����
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState); 
	    setContentView(R.layout.hospitallist);
	    //��ʾҽԺ�б�
	    ListView listview = (ListView) findViewById(R.id.listView);
		
	    ArrayList<HashMap<String, Object>> lstMsg = new ArrayList<HashMap<String, Object>>();  
	    for(int i=0;i<msgName.length;i++)  
	    {  
	        HashMap<String, Object> map = new HashMap<String, Object>();  
	        map.put("hosNmae", msgName[i]);  
	        map.put("hosNum", msgNum[i]);   
	        lstMsg.add(map);  
	    }
	    //���ü�������
	    SimpleAdapter saListMsg = new SimpleAdapter(this,lstMsg,R.layout.listview,new String[] {"hosNmae","hosNum"},new int[] {R.id.hosName,R.id.hosNum});  
	    //��Ӳ�����ʾ   
	    listview.setAdapter(saListMsg);
	    //�����Ϣ����   
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