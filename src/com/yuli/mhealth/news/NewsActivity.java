package com.yuli.mhealth.news;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.yuli.mhealth.R;
import com.yuli.mhealth.R.id;
import com.yuli.mhealth.R.layout;
import com.yuli.mhealth.tools.Constant;
import com.yuli.mhealth.tools.HttpUploadUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
/*
 * author yuli
 */
public class NewsActivity extends Activity {

	private int cid;	//新闻类型id
	private final int NEWSCOUNT = 5;	//一次获取的新闻数量
	private Button refreshBtn;	//刷新按钮
	private ListView datalist;	//显示新闻的listview
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();	//存储新闻的数据结合
	private SimpleAdapter mSimpleAdapter;
	
	private Handler hd;
	
	private final String IP = Constant.IP;
	private final String url = "http://"+IP+":8080/WebRoot/postInfo.jsp";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.news_layout);
		
		refreshBtn = (Button)findViewById(R.id.news_refresh);
		datalist = (ListView)findViewById(R.id.news_list);
		
		//进行新闻更新
		refreshBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					init();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		//查看具体一条新闻
		datalist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Map<String, String> map2 = (Map<String, String>)NewsActivity.this.mSimpleAdapter
						.getItem(position);
				Intent intent = new Intent();
				intent.setClass(NewsActivity.this, NewsInfo.class);
				Bundle bundle = new Bundle();
				bundle.putString("newslist_item_title", map2.get("newslist_item_title"));
				bundle.putString("newslist_item_digest", map2.get("newslist_item_digest"));
				bundle.putString("newslist_item_source", map2.get("newslist_item_source"));
				bundle.putString("newslist_item_ptime", map2.get("newslist_item_ptime"));
			}
		});
	}

	protected void init() {
		// TODO Auto-generated method stub
		//如果是第一次加载的话
		list.clear();
		final Map<String, String> params = new HashMap<String, String>();
		params.put("startid", "0");
		params.put("newscount", Integer.toString(NEWSCOUNT));
		params.put("cid", Integer.toString(cid));
//		String params = "0,"+ NEWSCOUNT +","+ cid;	//依次传入开始新闻编号，新闻数量，新闻类别
		
		new Thread() {
			public void run() {
				String msgStr = HttpUploadUtil.postWithoutFile(url, params);
				Bundle b = new Bundle();
				b.putString("msg", msgStr);
				Message msg = new Message();
				msg.setData(b);
				hd.sendMessage(msg);
			}
		}.start();
		//重写handler
		hd = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				Bundle b = msg.getData();
				String msgStr = b.getString("msg");	//返回标识校对
				/*
				 * 如果getCode数值为0,则返回信息正常
				 * 1,则没有新闻
				 * 其他，则访问异常
				 */
				try{
					JSONObject jsonObject = new JSONObject(msgStr);
					int getCode = jsonObject.getInt("cinfo");	//获取返回信息类型
					if(getCode == 0) {
						JSONObject dataObject = jsonObject.getJSONObject("data");
						int totalNum = dataObject.getInt("totalnum");	//获取返回新闻的数目
						if(totalNum > 0) {
							JSONArray newslistArray = dataObject.getJSONArray("newslist");	//获取返回新闻集合
							//将json解析为正常的格式
							for(int i=0;i<newslistArray.length();i++) {
								JSONObject newsObject = (JSONObject)newslistArray.opt(i);
								HashMap<String, Object> hashMap = new HashMap<String, Object>();
								hashMap.put("nid", newsObject.getInt("nid"));
								hashMap.put("newslist_item_title",
										newsObject.getString("title"));
								hashMap.put("newslist_item_digest",
										newsObject.getString("digest"));
								hashMap.put("newslist_item_source",
										newsObject.getString("source"));
								hashMap.put("newslist_item_ptime",
										newsObject.getString("ptime"));
								hashMap.put("newslist_item_comments",
										newsObject.getInt("commentcount"));
								list.add(hashMap);
							}
							//将新闻加载到listview上
							mSimpleAdapter = new SimpleAdapter(NewsActivity.this, list, 
									R.layout.news_item_layout, 
									new String[] {
									"newslist_item_title", "newslist_item_digest",
									"newslist_item_source", "newslist_item_ptime"}, 
									new int[] {
									R.id.newslist_item_title, R.id.newslist_item_source,
									R.id.newslist_item_digest, R.id.newslist_item_source,
									R.id.newslist_item_ptime});
							datalist.setAdapter(mSimpleAdapter);
						}else {
							Toast.makeText(NewsActivity.this, "抱歉，没有新闻更新！", Toast.LENGTH_SHORT).show();
						}
					}else if(getCode == 1) {
						Toast.makeText(NewsActivity.this, "抱歉，没有新闻更新！", Toast.LENGTH_SHORT).show();
					}else {
						Toast.makeText(NewsActivity.this, "抱歉，网络异常！", Toast.LENGTH_SHORT).show();
					}
				}catch(Exception e) {
					Toast.makeText(NewsActivity.this, "抱歉，访问异常！", Toast.LENGTH_SHORT).show();
				}
			}
		};
	}
}
