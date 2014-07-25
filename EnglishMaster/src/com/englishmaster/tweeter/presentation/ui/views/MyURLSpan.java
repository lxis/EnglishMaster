package com.englishmaster.tweeter.presentation.ui.views;

import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.data.server.services.YoudaoService;
import com.englishmaster.tweeter.presentation.ui.viewmodels.NewMainItemViewModel;

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

public class MyURLSpan extends ClickableSpan
{
	private String mUrl;
	private Context context;
	private NewMainItemViewModel viewModel;

	public MyURLSpan(String url, Context context,NewMainItemViewModel viewModel)
	{
		mUrl = url;
		this.viewModel = viewModel;
	}

	@Override
	public void updateDrawState(TextPaint ds)
	{
		ds.setColor(ds.linkColor);
		ds.setUnderlineText(false); // <span
						// style="color: red;">//ȥ���»���</span>
	}

	@Override
	public void onClick(View widget)
	{
		new AsyncTask<String, Integer, String>()
		{
			@Override
			protected String doInBackground(String... arg0)
			{
				// TODO Auto-generated method stub
				try
				{
					return new YoudaoService().GetWord(mUrl);
				}
				catch (IllegalArgumentException e)
				{
					return "�����ַ�����";
				}
			}

			@Override
			protected void onPostExecute(String result)
			{
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				
				
				if (!result.equals(viewModel.TranslationText) ||viewModel.TranslationVisibility == View.GONE)
				{
					viewModel.SetTranslationText(result);					
					viewModel.SetTranslationVisibility(View.VISIBLE);
				}
				else
				{
					viewModel.SetTranslationVisibility(View.GONE);
				}

			}
		}.execute(mUrl);

		// TODO Auto-generated method stub
		// Toast.makeText(mUrl).show();
	}
}
