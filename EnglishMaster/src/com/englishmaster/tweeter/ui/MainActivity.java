package com.englishmaster.tweeter.ui;

import java.util.ArrayList;

import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.domain.Article;
import com.englishmaster.tweeter.server.service.TweeterService;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
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
