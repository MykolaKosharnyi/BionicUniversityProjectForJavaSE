package model;

public class Enrollee {
	private long id;
	private String firstName;
	private String secondName;
	
	private int ratingMathematic;
	private int ratingPhisic;
	private int ratingBiology;
	
	private Certificate certificate;
	
	private double averageRating;

	public Enrollee(){}
	
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

	public int getRatingMathematic() {
		return ratingMathematic;
	}

	public void setRatingMathematic(int ratingMathematic) {
		this.ratingMathematic = ratingMathematic;
	}

	public int getRatingPhisic() {
		return ratingPhisic;
	}

	public void setRatingPhisic(int ratingPhisic) {
		this.ratingPhisic = ratingPhisic;
	}

	public int getRatingBiology() {
		return ratingBiology;
	}

	public void setRatingBiology(int ratingBiology) {
		this.ratingBiology = ratingBiology;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	@Override
	public String toString() {
		return "Enrollee [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", ratingMathematic="
				+ ratingMathematic + ", ratingPhisic=" + ratingPhisic + ", ratingBiology=" + ratingBiology
				+ ", averageRating=" + averageRating + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(averageRating);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ratingBiology;
		result = prime * result + ratingMathematic;
		result = prime * result + ratingPhisic;
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
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
		Enrollee other = (Enrollee) obj;
		if (Double.doubleToLongBits(averageRating) != Double.doubleToLongBits(other.averageRating))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (ratingBiology != other.ratingBiology)
			return false;
		if (ratingMathematic != other.ratingMathematic)
			return false;
		if (ratingPhisic != other.ratingPhisic)
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		return true;
	}
	
}
