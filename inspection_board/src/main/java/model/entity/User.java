package model.entity;

import java.io.Serializable;

/**
 * Entity of user  
 * @author nikolay
 *
 */
public class User implements Serializable, Comparable<User> {
	private static final long serialVersionUID = 1482272761478193542L;
	private long id;
	private String firstName;
	private String secondName;
	private String email;
	private String phone;
	private String password;

	private Certificate certificate;
	
    /**
     * Role define specific system functionality available to a user.
     */
    private Role role;

    public enum Role {
        ADMIN, ENROLLEE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

	public User() {}

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
		private Role role;

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
		
		public Builder setRole(Role role) {
			this.role = role;
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
			user.setRole(role);
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean hasRole(Role role) {
        return this.role.equals(role);
    }

    public boolean hasRole(String role) {
        return hasRole(Role.valueOf(role.toUpperCase()));
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", email=" + email
				+ ", phone=" + phone + ", certificate=" + certificate + ", role=" + role + "]";
	}

	@Override
	public int compareTo(User user) {
		return Double.compare(user.certificate.getAverageRating(),this.certificate.getAverageRating());
	}

}
