package com.englishmaster.tweeter.presentation.ui.views;

import java.util.ArrayList;

import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.data.server.services.TweeterService;
import com.englishmaster.tweeter.domain.model.Article;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.mainList);
        data = new ArrayList<Article>();
        data.add(null);
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
				startActivity(new Intent(MainActivity.this,BindingActivity.class));				
//				buttonGrade.setTextColor(Color.rgb(140,210,50));	
//				buttonLearn.setTextColor(Color.WHITE);
//				listLearn.setVisibility(8);
//				linearGrade.setVisibility(0);
			}
		});    			
		
		listView.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {}
			
			@Override
			public void onScroll(AbsListView arg0, int arg1, int arg2, int totalCount) {
				int lastPosition = listView.getLastVisiblePosition();				
				if( totalCount <= lastPosition+1+preloadCount)		//�������µ�Item�ǵڼ�����		
					LoadData();								
			}
		});
		
	}
    
    private int preloadCount = 1;
    
    private int startIndex = 1;
    private int lastIndex = 1;
    
    private int singleCount = 1;

    private Boolean isLoading = false;
    
	private void LoadData() {
		if(isLoading)
			return;
		isLoading = true;
		// TODO Auto-generated method stub
    	new AsyncTask<String,Integer,ArrayList<Article>>(){
    		@Override
    		protected ArrayList<Article> doInBackground(
    				String... params) {
    			return new TweeterService().GetTweeters(lastIndex,singleCount);
    		}    		
    		@Override
    		protected void onPostExecute(ArrayList<Article> result) 
    		{
    			data.addAll(data.size()-1, result);   			
				 
    			if(adapter == null)
    			{
    				adapter = new MainListAdapter(data, MainActivity.this);
    				listView.setAdapter(adapter);
    			}    		
    			else
    			{
    				adapter.notifyDataSetChanged();
    			}
    			lastIndex = lastIndex+singleCount;
    			
    			isLoading = false;
    		};}.execute("");
	}


	ArrayList<Article> data;
	MainListAdapter adapter;
    ListView listView;



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
