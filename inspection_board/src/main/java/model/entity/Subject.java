package model.entity;

public class Subject {
	
	private long id;
	private String name;

	public Subject(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Subject() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
