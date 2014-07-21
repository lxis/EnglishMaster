package com.englishmaster.tweeter.infrastructure.ui.mvvm;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;

public class VisualHelper
{
	private static void getAllChildrenControls(ArrayList<View> controls, View view)
	{
		ViewGroup g = (ViewGroup) view;
		int childCount = g.getChildCount();
		for (int i = 0; i < childCount; i++)
		{
			View controlItem = g.getChildAt(i);
			if (controlItem instanceof ViewGroup) getAllChildrenControls(controls, controlItem);
			controls.add(controlItem);
		}
		controls.add(view);
	}

	//鎵�湁瀛愭帶浠讹紝涔熷寘鍚嚜宸�
	public static  ArrayList<View> getAllChildrenControls(View view)
	{
		ArrayList<View> controls = new ArrayList<View>();
		getAllChildrenControls(controls,view);
		return controls;
	}
}
