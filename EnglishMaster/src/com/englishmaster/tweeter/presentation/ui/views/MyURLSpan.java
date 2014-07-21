package com.englishmaster.tweeter.presentation.ui.views;

import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.data.server.services.YoudaoService;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyURLSpan extends ClickableSpan {
    private String mUrl;
    private Context context;
    private View currentView;

    MyURLSpan(String url,Context context,View currentView) {
            mUrl = url;
            this.context = context;
            this.currentView = currentView;
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
    			try
    			{
    				return new YoudaoService().GetWord(mUrl);
    			}
    			catch(IllegalArgumentException e)
    			{
    				return "请求字符有误";     
    			}
    		}
    		@Override
    		protected void onPostExecute(String result) {
    			// TODO Auto-generated method stub
    			super.onPostExecute(result);    			
    			
    			TextView translationView = (TextView)currentView.findViewById(R.id.textTranslation);
    			String currentText = translationView.getText().toString();
    			LinearLayout translationLinear = (LinearLayout)currentView.findViewById(R.id.linearTranslation);
    			if(!result.equals(currentText)||translationLinear.getVisibility() == 8)
    			{
    				translationView.setText(result);    				
    				translationLinear.setVisibility(0);
    			}
    			else
    			{
    				translationLinear.setVisibility(8);
    			}
    			
    			
    			
    			
    		}
    	}.execute(mUrl);
    	
            // TODO Auto-generated method stub
            //Toast.makeText(mUrl).show();
    }
}
