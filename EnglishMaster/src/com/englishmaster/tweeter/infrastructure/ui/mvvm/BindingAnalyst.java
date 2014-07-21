package com.englishmaster.tweeter.infrastructure.ui.mvvm;

import java.util.ArrayList;

public class BindingAnalyst
{
	//铏界劧閫昏緫鍜岀畻娉曞彲鑳芥瘮杈冮夯鐑︼紝浣嗕笟鍔″惈涔夋瘮杈冪畝鍗曪紝鎶�湳鎵嬫姣旇緝鐩存帴銆傚氨鏄瓧绗︿覆瑙ｆ瀽锛屼粠layout鐨則ag涓垎鏋愬嚭鎯宠鐨勪笟鍔℃ā鍨嬨�
	public static ArrayList<TagBindingParamText> loadBindingParamText(String tag)
	{
		ArrayList<TagBindingParamText> list = new ArrayList<TagBindingParamText>();
		boolean isFinish = false;
		int pos = 0;

		while (!isFinish)
		{
			char[] methodName = new char[32];
			char[] filedName = new char[32];
			char[] singnal = tag.toCharArray();

			int currentPos = pos;
			while (true)
			{
				char currentChar = singnal[pos];
				if (currentChar != '=' || singnal[pos + 1] != '>')
					methodName[pos++ - currentPos + 3] = currentChar;// Q:3
				else
					break;
			}

			int methodLength = pos - currentPos + 3;// Q:3
			pos += 2;
			currentPos = pos;
			while (true)
			{
				filedName[pos - currentPos] = singnal[pos];
				pos++;
				if (pos == singnal.length)
				{
					isFinish = true;
					break;
				}
				if (singnal[pos] == ',') break;
			}

			int fieldLength = pos - currentPos;
			pos++;						
			
			methodLength = addOmitPart(methodName, methodLength);

			TagBindingParamText tagInfo = new TagBindingParamText();
			tagInfo.MethodNameString = new String(methodName, 0, methodLength);
			tagInfo.FieldNameString = new String(filedName, 0, fieldLength);
			list.add(tagInfo);
		}
		return list;
	}


	private static int addOmitPart(char[] methodName, int methodLength)
	{
		if(Character.isUpperCase(methodName[3]))
		{
			methodName[0] = 's';// Q:鍐欐
			methodName[1] = 'e';
			methodName[2] = 't';
		}

		if (methodName[3] == 'O' && methodName[4] == 'n')
		{
			methodName[methodLength + 0] = 'L';
			methodName[methodLength + 1] = 'i';
			methodName[methodLength + 2] = 's';
			methodName[methodLength + 3] = 't';
			methodName[methodLength + 4] = 'e';
			methodName[methodLength + 5] = 'n';
			methodName[methodLength + 6] = 'e';
			methodName[methodLength + 7] = 'r';
			methodLength += 8;
		}
		return methodLength;
	}
}
