package model.entity;

public class User {// Вступник
	private long id;
	private String firstName;
	private String secondName;
	private String email;
	private String phone;
	private String password;

	private Certificate certificate;// аттестат

	public User() {
	}

	public User(long id, String firstName, String secondName, String email, String phone, String password) {
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public static class Builder {
		private long id;
		private String firstName;
		private String secondName;
		private String email;
		private String phone;
		private String password;
		private Certificate certificate;

		public Builder setId(long id) {
			this.id = id;
			return this;
		}

		public Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder setSecondName(String secondName) {
			this.secondName = secondName;
			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder setPhone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder setCertificate(Certificate certificate) {
			this.certificate = certificate;
			return this;
		}

		public User build() {
			User user = new User();
			user.setId(id);
			user.setFirstName(firstName);
			user.setSecondName(secondName);
			user.setEmail(email);
			user.setPhone(phone);
			user.setPassword(password);
			user.setCertificate(certificate);
			return user;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

}
