package com.englishmaster.tweeter.infrastructure.ui.mvvm;

import java.util.ArrayList;

public class BindingAnalyst
{
	Boolean isFinish = false;
	
	Integer pos = 0;
	
	Integer currentPos = 0;
	
	ArrayList<TagBindingParamText> list = new ArrayList<TagBindingParamText>();
	
	public ArrayList<TagBindingParamText> loadBindingParamText(String tag)
	{					
		while (!isFinish)
		{
			TagBindingParamText tagInfo = new TagBindingParamText();
			char[] methodName = new char[32];
			char[] singnal = tag.toCharArray();

			currentPos = pos;
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
			
			//开始搞field
			analysisFields(singnal, tagInfo);
			//field搞完
			
			
			methodLength = addOmitPart(methodName, methodLength);

			
			tagInfo.MethodNameString = new String(methodName, 0, methodLength);
			
			list.add(tagInfo);
		}
		return list;
	}


	private int addOmitPart(char[] methodName, int methodLength)
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
	
	private void analysisFields(char[] singnal,TagBindingParamText tagInfo)
	{
		char[] fieldNameText = new char[32];
		ArrayList<char[]> fieldNames = new ArrayList<char[]>();
		
		boolean isContinueCurrentField =true; 
		while(!isFinish && isContinueCurrentField)
		{
			char[] fieldName = new char[32];
			fieldNames.add(fieldName);
			int currentPos2 = pos;//这个比较烂
			while (true)
			{
				fieldName[pos - currentPos2] = singnal[pos];
				fieldNameText[pos-currentPos] = singnal[pos];
				pos++;
				if (pos == singnal.length)
				{
					isFinish = true;					
					break;
				}
				if (singnal[pos] == ',')
				{
					isContinueCurrentField = false;
					break;					
				}
				if(singnal[pos] == '.')		
				{
					fieldNameText[pos-currentPos] = singnal[pos];
					pos++;
					break;
				}
			}			
		}

		int fieldLength = pos - currentPos;
		pos++;				
		
		tagInfo.FieldName = new String(fieldNameText, 0, fieldLength);		

		for(char[] currentfieldName:fieldNames)			
			tagInfo.FieldNameStrings.add(new String(currentfieldName, 0, fieldLength));
	}
}
