package model.entity;

public enum Subject {
	MATH("Math"), PHYSICS("Physics"), HISTORY("History"), GEOGRAPHY("Geography"), ENGLISH("English"), CHEMISTRY("Chemistry"),
	BIOLOGY("Biology"); 
	
	private String name;
	
	private Subject(String name) {
		this.name = name;
	}
	
	private Subject() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
