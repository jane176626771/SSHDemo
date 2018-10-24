package com.example.studentapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText nameEdit,ageEdit,sexEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		nameEdit = (EditText)findViewById(R.id.name);
		ageEdit = (EditText)findViewById(R.id.age);
		sexEdit = (EditText)findViewById(R.id.sex);
		
	}
	
	public void submitInfo(View v){
		if(v.getId()==R.id.button1){
			String name = nameEdit.getText().toString();
			String sex = sexEdit.getText().toString();
			String age = ageEdit.getText().toString();
			SubmitThread th = new SubmitThread(name,sex,age);
			th.start();
		}else if(v.getId()==R.id.query){
			String name = nameEdit.getText().toString();
			QueryThread th = new QueryThread(name);
			th.start();
		}
		
		
	}
	
}
