package com.example.studentapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class QueryThread extends Thread {
	String name;
	public static final String URL = "http://10.0.2.2:8080/StudentServer/StudentQuery";
	public QueryThread(String name){
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		//android6.0不支持HttpClient，将project.properties改成API=17即可使用
		HttpClient hc = new DefaultHttpClient();
		HttpPost hp = new HttpPost(URL);
		
		ArrayList<NameValuePair> value = new ArrayList<NameValuePair>();
		value.add(new BasicNameValuePair("name",name));
		try {
			hp.setEntity(new UrlEncodedFormEntity(value,HTTP.UTF_8));
			HttpResponse response = hc.execute(hp);
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();
				int length = 0;
				byte[] data = new byte[1024];
				String info = "";
				while((length=is.read(data))!=-1){
					info += new String(data,0,length);
				}
				
				JSONObject json = new JSONObject(info);
				JSONArray stuArray = json.getJSONArray("students");
				for(int i=0;i<stuArray.length();i++){
					JSONObject jo = stuArray.getJSONObject(i);
					String name = jo.getString("name");
					int age = jo.getInt("age");
					int id = jo.getInt("id");
					String sex = jo.getString("sex");
					Log.i("===============", id+" "+name+ " "+age + " "+sex);
				}
				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
