package com.englishmaster.tweeter.ui;

import java.util.ArrayList;

import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.domain.Article;
import com.englishmaster.tweeter.server.service.TweeterService;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//»•µÙ±ÍÃ‚¿∏
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.mainList);
        LoadData();
        InitEvents();
    }



    private void InitEvents() {
    	final Button buttonLearn = (Button)findViewById(R.id.buttonLearn);
    	final Button buttonGrade = (Button)findViewById(R.id.buttonGrade);
    	final ListView listLearn = (ListView)findViewById(R.id.mainList);
    	final LinearLayout linearGrade = (LinearLayout)findViewById(R.id.linearGrade);
    	buttonLearn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				buttonLearn.setTextColor(Color.rgb(140,210,50));
				buttonGrade.setTextColor(Color.WHITE);
				linearGrade.setVisibility(8);
				listLearn.setVisibility(0);
			}
		});
		
    	buttonGrade.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				buttonGrade.setTextColor(Color.rgb(140,210,50));	
				buttonLearn.setTextColor(Color.WHITE);
				listLearn.setVisibility(8);
				linearGrade.setVisibility(0);
			}
		});
		
	}



	private void LoadData() {
		// TODO Auto-generated method stub
    	new AsyncTask<String,Integer,ArrayList<Article>>(){
    		@Override
    		protected ArrayList<Article> doInBackground(
    				String... params) {
    			return new TweeterService().GetTweeters();
    		}    		
    		@Override
    		protected void onPostExecute(ArrayList<Article> result) {
    			if(adapter == null)
    			{
    				adapter = new MainListAdapter(result, MainActivity.this);
    				listView.setAdapter(adapter);
    			}
    			else
    			{
    				adapter.notifyDataSetChanged();
    			}
    		};}.execute("");
	}



	MainListAdapter adapter;
    ListView listView;



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
