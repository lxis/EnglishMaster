package com.englishmaster.tweeter.ui;

import java.util.ArrayList;

import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.domain.Article;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainListAdapter extends BaseAdapter {

	ArrayList<Article> data;
	Context context;
	
	public MainListAdapter(ArrayList<Article> data,Context context)
	{
		this.data = data;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		convertView = LayoutInflater.from(context).inflate(R.layout.listitem_main_list, null);
		TextView textView1 = (TextView)convertView.findViewById(R.id.text1);
		
		String[] contents = data.get(position).Content.split(" ");		
        SetTextLink(textView1, contents);			
		
		
		
		return convertView;
	}

	private void SetTextLink(TextView textView1, String[] words) {
		for(int i = 0;i<words.length;i++)		
			words[i] = words[i];		
		String clickText ="";
		for(String word :words)		
			clickText += "<a style=\"text-decoration:none\" href=\""+word+"\">"+word+"</a> ";				
		textView1.setText(Html.fromHtml("<string name=\"hello\">"+clickText+"</string>"));       		
		
		
		textView1.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = textView1.getText();
        if (text instanceof Spannable) 
        {
                int end = text.length();
                Spannable sp = (Spannable) textView1.getText();
                URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
                SpannableStringBuilder style = new SpannableStringBuilder(text);
                style.clearSpans(); // should clear old spans
                for (URLSpan url : urls) 
                {
                        MyURLSpan myURLSpan = new MyURLSpan(url.getURL(),context);
                        style.setSpan(myURLSpan, sp.getSpanStart(url),
                                        sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        style.setSpan(new ForegroundColorSpan(Color.BLACK), sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                textView1.setText(style);
        }
	}

}
