package com.remmylife.diary;

import java.util.Date;

import com.remmylife.head.*;

public class TextDiary extends Diary
{
	private String text = null;
	
	public TextDiary()
	{
		super();
		this.setType(DiaryType.TEXT_DIARY);
	}
	
	public TextDiary(String title, Date date)
	{
		super(title, date);
		this.setType(DiaryType.TEXT_DIARY);
	}
	
	public TextDiary(int id, String title, Date date, Weather weather)
	{
		super(id, DiaryType.TEXT_DIARY, title, date, weather);
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String getText()
	{
		return this.text;
	}
}