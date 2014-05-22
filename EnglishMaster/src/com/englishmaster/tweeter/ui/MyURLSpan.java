package com.englishmaster.tweeter.ui;

import com.englishmaster.tweeter.server.service.YoudaoService;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

public class MyURLSpan extends ClickableSpan {
    private String mUrl;
    private Context context;

    MyURLSpan(String url,Context context) {
            mUrl = url;
            this.context = context;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(ds.linkColor);
        ds.setUnderlineText(false); //<span style="color: red;">//去掉下划线</span>
    }

    
    @Override
    public void onClick(View widget) {
    	int i = 0;
    	int ii = i;
    	new AsyncTask<String,Integer,String>(){
    		@Override
    		protected String doInBackground(String... arg0) {
    			// TODO Auto-generated method stub
    			return new YoudaoService().GetWord(mUrl);    			
    		}
    		@Override
    		protected void onPostExecute(String result) {
    			// TODO Auto-generated method stub
    			super.onPostExecute(result);    			
    			
    			
    			new AlertDialog.Builder(context)
    			.setPositiveButton("OK", new DialogInterface.OnClickListener() {    				
    				@Override
    				public void onClick(DialogInterface dialog, int which) {				
    		            		
    				}
    			})
    			.setMessage("意思是:"+result).create().show();
    			
    			
    			
    		}
    	}.execute(mUrl);
    	
            // TODO Auto-generated method stub
            //Toast.makeText(mUrl).show();
    }
}
