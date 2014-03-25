package com.yuli.mhealth.xiaotool;

import java.util.List;

import com.amap.mapapi.core.AMapException;
import com.amap.mapapi.core.GeoPoint;
import com.amap.mapapi.core.PoiItem;
import com.amap.mapapi.map.MapActivity;
import com.amap.mapapi.map.MapController;
import com.amap.mapapi.map.MapView;
import com.amap.mapapi.map.MyLocationOverlay;
import com.amap.mapapi.map.PoiOverlay;
import com.amap.mapapi.map.RouteOverlay;
import com.amap.mapapi.poisearch.PoiPagedResult;
import com.amap.mapapi.poisearch.PoiSearch;
import com.amap.mapapi.poisearch.PoiSearch.SearchBound;
import com.amap.mapapi.poisearch.PoiTypeDef;
import com.amap.mapapi.route.Route;
import com.amap.mapapi.route.Route.FromAndTo;
import com.yuli.mhealth.R;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class searchHospital extends MapActivity{
	
	private MapView mMapView;
	private MapController mMapController;
	private GeoPoint mMapCenter;
	private PoiOverlay poiOverlay=null;
	private SearchBound searchBound;
	private PoiPagedResult result;
	private PoiSearch.Query query;
	private MyLocationOverlay myLocationOverlay;
	private GeoPoint myPoint;
	private Button mAmplify=null; 
	private Button mShrink=null;
	private TextView textView;
	private Button detail;
	private static String interest = "医院";
	private static List<PoiItem>  poiItems;
	private static int zoom=14;
	
	private PoiSearch poiSearch;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapsearch);
		
	    init();
	    initButton();
	    Intent intent_main = getIntent();
	    int number = intent_main.getIntExtra("number", -1);
	    if(number > 0){
	    	initRoute(number);
	    }
	    if(intent_main.getStringExtra("interest") == "药店"){
	    	interest = "药店";
	    }
	    else {
	    	interest = "医院";
	    }
	}	
	private void init(){
	
		
	    try{
	    	//设置初始地图中心
	    	mMapView=(MapView)findViewById(R.id.poisearch_MapView);
			textView = (TextView) findViewById(R.id.mylocation);
		    mMapController = mMapView.getController();
			mMapCenter = new GeoPoint((int) (30.524118 * 1E6),(int) (114.335489 *  1E6));  
		    mMapController.setCenter(mMapCenter);  
		    mMapController.setZoom(14);
		    //获取自身位置，并设为地图中心
		    myLocationOverlay=new MyLocationOverlay(searchHospital.this,mMapView);
		    myLocationOverlay.enableMyLocation();
		    myPoint = myLocationOverlay.getMyLocation();
		    textView.setText(myPoint.toString());
		    mMapController.setCenter(myPoint); 
		    myLocationOverlay.enableCompass();
		    mMapView.getOverlays().add(myLocationOverlay);
	    	query=new PoiSearch.Query(interest, PoiTypeDef.All, "027");
	    	poiSearch=new PoiSearch(searchHospital.this,query);
	    	searchBound=new SearchBound(myPoint,2000);
	    	poiSearch.setBound(searchBound);
	    	poiSearch.setPageSize(20);
	    	//搜索
	    	result=poiSearch.searchPOI();
	    	mHandler.sendEmptyMessage(1);
	    }
	    catch (AMapException e){
	    	e.printStackTrace();
	    }
	    
	
 	}
	
	private Handler mHandler=new Handler(){
		public void handleMessage(Message msg){
			if (msg.what == 1){
				try{
				
			    //将搜索结果在地图上标出
			    if (result.getPageCount() >= 0 && result.getQuery() != null) {  
		            if(result.getQuery().equals(query)){      
		                poiItems = result.getPage(1);  

		                if (poiItems != null && poiItems.size() > 0) {  
		                	mMapController.animateTo(poiItems.get(0).getPoint());
		                	Drawable drawable = getResources().getDrawable(R.drawable.da_marker_red);
		                	if (poiOverlay != null) {
								poiOverlay.removeFromMap();
							}
		                    PoiOverlay poiOverlay = new PoiOverlay(drawable, poiItems);    
		                    poiOverlay.addToMap(mMapView);  
		                    poiOverlay.showPopupWindow(0);  
		                    return;
		                }
		                else
		                {  
		                	Toast.makeText(searchHospital.this, "未搜索到相关信息", Toast.LENGTH_SHORT).show(); 
		                }  
		            }  
		        }
			    else 
			    {  
		 	    	Toast.makeText(searchHospital.this, "未搜索到相关信息", Toast.LENGTH_SHORT).show();
		         }
				}
			 	catch(AMapException e)
			 	{
			 		Log.d("debug","dsjlafj");
			 		Toast.makeText(searchHospital.this, "搜索失败，请检查网络设置", Toast.LENGTH_SHORT).show();
			 	}
			}
		}
	};  

    private void initButton(){
		
		mAmplify=(Button)findViewById(R.id.amplify);
		mAmplify.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				if(zoom<20)
				{
				    zoom=zoom+1;
				    mMapController.setZoom(zoom);
				}
			}
		});
		mShrink=(Button)findViewById(R.id.shrink);
		mShrink.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				if(zoom>4){
				    zoom=zoom-1;
				    mMapController.setZoom(zoom);
				}
			}
		});
		//搜索结果详情按钮
		detail = (Button)findViewById(R.id.detail_button);
		detail.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				int poiSize = poiItems.size();
				String msgName[] = new String[poiSize];
				String msgNum[] = new String[poiSize];
				for(int i = 0;i < poiSize;i++){
					PoiItem poiItem = poiItems.get(i);
					msgName[i] = poiItem.getTitle();
					msgNum[i] = poiItem.getTel();
				}
				Intent intent = new Intent(searchHospital.this,RoutePlan.class);
				Bundle extras = new Bundle();
				extras.putStringArray("msgName", msgName);
				extras.putStringArray("msgNum", msgNum);
				intent.putExtras(extras);
				startActivity(intent);

			}
		});
	}
    
    private void initRoute(int arg2){
    	//路径搜寻
    	PoiItem poiItem = poiItems.get(arg2);
    	GeoPoint aim = poiItem.getPoint();
    	FromAndTo fromAndTo = new FromAndTo(myPoint, aim);
		Route route = new Route(Route.DrivingLeastDistance);
		try{
			List<Route> listRoute = route.calculateRoute(searchHospital.this, fromAndTo, Route.DrivingLeastDistance);
			int listSize = listRoute.size();
    		for(int i = 0;i < listSize;i++){
    			RouteOverlay routeOverlay = new RouteOverlay(searchHospital.this,listRoute.get(i));
    			routeOverlay.addToMap(mMapView);
    		}
		}
		catch(AMapException e){
			Log.e("debug","route.calculateRoute IllegalArgumentException");
			e.printStackTrace();
		}
    }
}
