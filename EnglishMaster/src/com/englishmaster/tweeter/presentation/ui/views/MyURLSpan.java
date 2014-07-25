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
	private String url;
	private NewMainItemViewModel viewModel;

	public MyURLSpan(String url, Context context, NewMainItemViewModel viewModel)
	{
		this.url = url;
		this.viewModel = viewModel;
	}

	@Override
	public void updateDrawState(TextPaint ds)
	{
		ds.setColor(ds.linkColor);
		ds.setUnderlineText(false);
	}

	@Override
	public void onClick(View widget)
	{
		new AsyncTask<String, Integer, String>()
		{
			@Override
			protected String doInBackground(String... arg0)
			{
				return new YoudaoService().GetWord(url);
			}

			@Override
			protected void onPostExecute(String result)
			{
				super.onPostExecute(result);

				if (!result.equals(viewModel.TranslationText) || viewModel.TranslationVisibility == View.GONE)
				{
					viewModel.SetTranslationText(result);
					viewModel.SetTranslationVisibility(View.VISIBLE);
				}
				else
				{
					viewModel.SetTranslationVisibility(View.GONE);
				}
			}
		}.execute(url);

	}
}
