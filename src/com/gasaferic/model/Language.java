package com.gasaferic.model;

public enum Language {
	
	IT("it"), ENG("eng");
	
	private String lang;
	
	Language(String lang) {
		this.lang = lang;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}