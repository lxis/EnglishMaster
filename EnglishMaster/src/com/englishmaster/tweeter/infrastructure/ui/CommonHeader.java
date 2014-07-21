//package com.englishmaster.tweeter.infrastructure.ui;
//
//import com.englishmaster.tweeter.R;
//
//import android.app.Activity;
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//public class CommonHeader extends RelativeLayout
//{
//	public CommonHeader(Context context)
//	{
//		super(context);
//		init(context);
//	}	
//	
//	public CommonHeader(Context context, AttributeSet attrs)
//	{
//		super(context, attrs);
//		init(context);
//	}
//
//	public CommonHeader(Context context, AttributeSet attrs, int defStyle)
//	{
//		super(context, attrs, defStyle);
//		init(context);
//	}
//
//	public void setTitle(String title)
//	{
//		TextView textViewTitle = (TextView)findViewById(R.id.textViewCommonHeaderTitle);
//		textViewTitle.setText(title);
//	}
//	
//	public void setTitle(View titleView)
//	{
//		LinearLayout linearTitle = (LinearLayout)findViewById(R.id.linearCommonHeaderTitle);
//		View textViewTitle = findViewById(R.id.textViewCommonHeaderTitle);
//		textViewTitle.setVisibility(8);
//		linearTitle.addView(titleView);		
//	}
//	
//	public void setButton(View  buttonView)
//	{
//		ViewGroup viewGroup = (ViewGroup)findViewById(R.id.linearCommonHeaderButton);
//		viewGroup.addView(buttonView);
//	}
//
//	private void init(final Context context)
//	{
//		LayoutInflater.from(context).inflate(R.layout.common_header, this);
//		ImageView imageView = (ImageView)findViewById(R.id.imageViewCommonHeaderBack);
//		imageView.setOnClickListener(new OnClickListener()
//		{			
//			@Override
//			public void onClick(View v)
//			{			
//				((Activity)context).finish();
//			}
//		});
//	}
//}
