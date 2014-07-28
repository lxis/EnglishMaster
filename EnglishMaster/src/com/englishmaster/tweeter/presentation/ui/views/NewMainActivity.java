package com.englishmaster.tweeter.presentation.ui.views;

import java.util.ArrayList;

import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.data.storage.StorageSetting;
import com.englishmaster.tweeter.domain.services.settings.ShopCar;
import com.englishmaster.tweeter.domain.services.settings.ShopCarSetting;
import com.englishmaster.tweeter.infrastructure.common.common_simple_handlers.CommonSimpleHandlerGenic;
import com.englishmaster.tweeter.infrastructure.ui.BaseActivity;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.BindingAdapter;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.ViewModelBinder;
import com.englishmaster.tweeter.presentation.ui.viewmodels.NewMainItemViewModel;
import com.englishmaster.tweeter.presentation.ui.viewmodels.NewMainViewModel;
import com.englishmaster.tweeter.presentation.ui.views.NewNewActivity.NewNewActivityParam;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

public class NewMainActivity extends BaseActivity
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

		try
		{
			ShopCarSetting shopCar = StorageSetting.Get(ShopCarSetting.class);
			shopCar.ShopCars.add(new ShopCar(1, "好东西", 100));
			StorageSetting.Set(shopCar);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}



	}
	

	
	private void newNavigate()
	{
		addHandler(MainPageParam.class, new CommonSimpleHandlerGenic<Object>()
		{			
			@Override
			public <T> void Run(T item)
			{
				String title = ((MainPageParam) item).Title;				
			}
		});

		NewNewActivityParam param = new NewNewActivityParam();
		param.Message = "message";
		param.Title = "title";
		navigate(NewMainActivity.class, param);
	}
	
	public static class MainPageParam
	{
		public String Title;
	}
	
	private void oldNavigate()
	{
		Intent t = new Intent(NewMainActivity.this,NewNewActivity.class);
		t.putExtra("title", "title");
		t.putExtra("message", "message");
		startActivityForResult(t, NewNewActivityRequestCode);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode == NewNewActivityRequestCode&& resultCode == MainPageParamRequestCode)
		{
			String title = data.getStringExtra("Title");
		}
	}
	
	public static int NewNewActivityRequestCode = 100;
	public static int MainPageParamRequestCode  = 100;
	
	Boolean isLoading = false;

	private void bindList()
	{
		if (isLoading) return;
		isLoading = true;
		new AsyncTask<String, Integer, ArrayList<NewMainItemViewModel>>()
		{
			@Override
			protected ArrayList<NewMainItemViewModel> doInBackground(String... params)
			{
				return viewModel.LoadData();
			}

			@Override
			protected void onPostExecute(ArrayList<NewMainItemViewModel> result)
			{
				viewModel.AddData(result);
				bindAdapter();
				isLoading = false;
			}

		}.execute("");
	}

	BindingAdapter<NewMainItemViewModel> adapter;

	private void bindAdapter()
	{
		if (adapter == null)
		{
			adapter = new BindingAdapter<NewMainItemViewModel>(viewModel.Data, NewMainActivity.this, R.layout.new_main_list_item, NewMainItemViewModel.class);
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
