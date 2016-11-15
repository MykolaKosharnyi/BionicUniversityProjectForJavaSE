package model.entity;

import java.util.List;

public class Department {
	private long id;
	private String nameDepartment;
	private int maxAmountStudent;
	
	private List<Subject> necessaryItems;

	public Department(long id, String nameDepartment, int maxAmountStudent) {
		this.id = id;
		this.nameDepartment = nameDepartment;
		this.maxAmountStudent = maxAmountStudent;
	}
	
	public Department() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNameDepartment() {
		return nameDepartment;
	}

	public void setNameDepartment(String nameDepartment) {
		this.nameDepartment = nameDepartment;
	}

	public int getMaxAmountStudent() {
		return maxAmountStudent;
	}

	public void setMaxAmountStudent(int maxAmountStudent) {
		this.maxAmountStudent = maxAmountStudent;
	}

	public List<Subject> getNecessaryItems() {
		return necessaryItems;
	}

	public void setNecessaryItems(List<Subject> necessaryItems) {
		this.necessaryItems = necessaryItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + maxAmountStudent;
		result = prime * result + ((nameDepartment == null) ? 0 : nameDepartment.hashCode());
		result = prime * result + ((necessaryItems == null) ? 0 : necessaryItems.hashCode());
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
		Department other = (Department) obj;
		if (id != other.id)
			return false;
		if (maxAmountStudent != other.maxAmountStudent)
			return false;
		if (nameDepartment == null) {
			if (other.nameDepartment != null)
				return false;
		} else if (!nameDepartment.equals(other.nameDepartment))
			return false;
		if (necessaryItems == null) {
			if (other.necessaryItems != null)
				return false;
		} else if (!necessaryItems.equals(other.necessaryItems))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", nameDepartment=" + nameDepartment + ", maxAmountStudent=" + maxAmountStudent
				+ ", necessaryItems=" + necessaryItems + "]";
	}
	
	
}
