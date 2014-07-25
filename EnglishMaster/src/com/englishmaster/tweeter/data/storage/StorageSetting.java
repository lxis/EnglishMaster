package com.englishmaster.tweeter.data.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import com.google.gson.Gson;

public class StorageSetting
{

	public static <T> T Get(Class<T> classType)
	{
		String key = classType.getName();
		String content = null;
		try
		{
			StringBuffer contents = new StringBuffer();
			if (!new File(key).exists()) return classType.newInstance();
			FileReader fr = new FileReader(key);
			BufferedReader br = new BufferedReader(fr);
			String str;
			while ((str = br.readLine()) != null)
				contents.append(str);
			content = contents.toString();
			br.close();
			fr.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Gson().fromJson(content, classType);
	}

	public static <T> void Set(T model)
	{
		String key =model.getClass().getName();
		try
		{
			FileOutputStream out;
			PrintStream ps;
			if (!new File(key).exists()) new File(key).createNewFile();
			out = new FileOutputStream(key);
			ps = new PrintStream(out);
			String jsonText = new Gson().toJson(model);
			ps.println(jsonText);
			ps.close();
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
