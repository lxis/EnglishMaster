package com.englishmaster.tweeter.presentation.ui.views;

import java.util.ArrayList;

import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.domain.model.Article;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.BaseViewModel;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.BindingAdapter;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.ViewModelBinder;
import com.englishmaster.tweeter.presentation.ui.adapters.MainListAdapter;
import com.englishmaster.tweeter.presentation.ui.viewmodels.NewMainViewModel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class NewMainActivity extends Activity
{
	NewMainViewModel viewModel = new NewMainViewModel();
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ViewModelBinder.Bind(viewModel, R.layout.new_main_activity, NewMainActivity.this);
		listView = (ListView) findViewById(R.id.newMainList);
		initViews();
		bindList();
	}

	Boolean isLoading = false;

	private void bindList()
	{
		if (isLoading) return;
		isLoading = true;
		new AsyncTask<String, Integer, ArrayList<Article>>()
		{
			@Override
			protected ArrayList<Article> doInBackground(String... params)
			{
				return viewModel.LoadData();
			}

			@Override
			protected void onPostExecute(ArrayList<Article> result)
			{
				bindAdapter(result);
				isLoading = false;
			}

		}.execute("");
	}

	MainListAdapter adapter;

	private void bindAdapter(ArrayList<Article> result)
	{
		if (adapter == null)
		{
			adapter = new MainListAdapter(result, NewMainActivity.this);
			listView.setAdapter(adapter);
		}
		else
		{
			adapter.notifyDataSetChanged();
		}
	};

	private void initViews()
	{
		listView.setOnScrollListener(new OnScrollListener()
		{
			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1)
			{}

			@Override
			public void onScroll(AbsListView arg0, int arg1, int arg2, int totalCount)
			{
				int lastPosition = listView.getLastVisiblePosition();
				if (viewModel.IsNeedLoadData(totalCount, lastPosition)) bindList();
			}
		});
	}

}
