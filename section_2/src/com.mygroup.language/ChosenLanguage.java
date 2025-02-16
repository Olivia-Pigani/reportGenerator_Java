package com.mygroup.language;

import java.util.ResourceBundle;
import java.util.Locale;

public final class ChosenLanguage{
	
	private static ChosenLanguage instance;
	private Language language;
	private ResourceBundle bundle;
	private Locale locale;
	private final String DEFAULT_LANGUAGE = Language.ENGLISH.toString(); 
	
	private ChosenLanguage(){
		this.locale = Locale.forLanguageTag(DEFAULT_LANGUAGE);
		this.bundle = ResourceBundle.getBundle("report", this.locale);
	}
	
	public static ChosenLanguage getInstance(){
		if(instance == null){
			instance = new ChosenLanguage();
		}
		return instance;
	}
	
	public void setGeneralLanguage(Language language){
		this.language = language;
		String languageStr = this.language.toString();
		this.locale = Locale.forLanguageTag(languageStr);
		this.bundle = ResourceBundle.getBundle("report", this.locale);
	
	}
	
	public String getMsg(String key){
		return bundle.getString(key);
	}
	
}