package model.entity;

import java.io.Serializable;
import java.util.Map;

public class Certificate implements Serializable{
	private static final long serialVersionUID = 1L;	
	private Map<Subject, Integer> itemsWithEstimates;

	public double getAverageRating() {
		double sumOfAllSubjects = 0;

		for (Map.Entry<Subject, Integer> entry : itemsWithEstimates.entrySet()){
			sumOfAllSubjects += entry.getValue();
		}
		return itemsWithEstimates.size() > 0 ? sumOfAllSubjects / itemsWithEstimates.size() : 0;
	}

	/**
	 * add new Subject with value change Subject delete subject
	 */
	public Map<Subject, Integer> getItemsWithEstimates() {
		return itemsWithEstimates;
	}

	public void setItemsWithEstimates(Map<Subject, Integer> itemsWithEstimates) {
		this.itemsWithEstimates = itemsWithEstimates;
	}

	@Override
	public String toString() {
		return "Certificate [itemsWithEstimates=" + itemsWithEstimates + "]";
	}
}
