public enum Language {
	
	FRENCH("fr"),
	ENGLISH("en");
	
	String code;
	
	Language(String code){
		this.code=code;
	}
	
	@Override
	public String toString() {
		return code;
  }
	
}


 