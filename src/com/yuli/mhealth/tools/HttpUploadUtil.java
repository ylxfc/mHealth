package com.yuli.mhealth.tools;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
/*
 * ͨ��HttpЭ�鷢�ʹ��ļ��򲻴��ļ�������Ĺ�����
 */
public class HttpUploadUtil 
{
	
	//�����ļ��������ͷ���
	public static String postWithoutFile(String actionUrl, Map<String, String> params )	//����Ĳ�������
	{		
		HttpClient httpclient = new DefaultHttpClient();
		//���URL
		HttpPost httppost = new HttpPost(actionUrl);
		try { 
		   List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(params.size()); 
		   
		   for (Map.Entry<String, String> entry : params.entrySet()) 
		   {//�����?�ֶ�����  
	            nameValuePairs.add(new BasicNameValuePair(entry.getKey(),MyConverter.escape(entry.getValue()))); 
	       }  
		   httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 
		   HttpResponse response; 
		   response=httpclient.execute(httppost); 
		   InputStream in=response.getEntity().getContent();
		   ByteArrayOutputStream baos = new ByteArrayOutputStream();
		   int ch=0;
		    while((ch=in.read())!=-1)
		    {
		      	baos.write(ch);
		    }      
		    byte[] data=baos.toByteArray();
		    baos.close();
		    return MyConverter.unescape(new String(data).trim());
		  } catch (Exception e) 
		  { 
		   e.printStackTrace(); 
		   return "error";
		  }
	}
}
