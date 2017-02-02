package model.entity;

import java.io.Serializable;

public class Subject implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;

	public Subject(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Subject() {
	}

	public static class Builder {
		private long id;
		private String name;

		public Builder setId(long id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Subject build() {
			Subject subject = new Subject();
			subject.setId(id);
			subject.setName(name);
			return subject;
		}
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + "]";
	}

}
