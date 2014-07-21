package com.englishmaster.tweeter.presentation.ui.views;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.infrastructure.common.common_simple_handlers.CommonSimpleHandler;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.BindingAdapter;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.ViewModelBinder;
import com.englishmaster.tweeter.presentation.ui.viewmodels.BindItemViewModel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Space;

public class BindingActivity extends Activity
{
	ArrayList<BindItemViewModel> data = new ArrayList<BindItemViewModel>();
	
	ViewModelBinder binder = new ViewModelBinder();
	

	BindItemViewModel viewModel = new BindItemViewModel("feifei", "chengong");
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.binding_activity);		
		InitControl();
		InitData();
		try
		{
			BindData();
		}
		catch (NoSuchFieldException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NoSuchMethodException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void BindData() throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{		
		View view =findViewById(R.id.textViewTitle);
		binder.Bind(view , viewModel);
	}
	
	public void Click(View view)
	{
		int i = 0;
		int ii = i;
		try
		{
			viewModel.SetContent("wangwang");
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void InitData()
	{
		data.add(new BindItemViewModel("haha","Content"));
		final BindItemViewModel viewModel = new BindItemViewModel("hehe","Content too");
		viewModel.Handler = new CommonSimpleHandler()
		{
			
			@Override
			public void Run()
			{
				try
				{
					viewModel.SetContent("miaomiao");
				}
				catch (IllegalAccessException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IllegalArgumentException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (InvocationTargetException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
				
			}
		};
		data.add(viewModel);		
	}

	private void InitControl()
	{
		ListView list = (ListView)findViewById(R.id.listViewBinding);
		BindingAdapter<BindItemViewModel> adapter = new BindingAdapter<BindItemViewModel>(data, BindingActivity.this, R.layout.binding_list_item, BindItemViewModel.class);
		list.setAdapter(adapter);		
		
	}
}
