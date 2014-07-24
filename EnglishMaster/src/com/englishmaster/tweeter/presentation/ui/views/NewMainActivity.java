package com.englishmaster.tweeter.presentation.ui.views;

import com.englishmaster.tweeter.R;
import com.englishmaster.tweeter.infrastructure.ui.mvvm.facade.ViewModelBinder;
import com.englishmaster.tweeter.presentation.ui.viewmodels.NewMainViewModel;

import android.app.Activity;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class NewMainActivity extends Activity
{
	NewMainViewModel viewModel = new NewMainViewModel();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ViewModelBinder.Bind(viewModel, R.layout.new_main_activity, NewMainActivity.this);		
	}
}
