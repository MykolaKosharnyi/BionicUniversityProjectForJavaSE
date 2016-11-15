package model.entity;

import java.util.Map;

public class Certificate {//аттестат
	private Map<Subject, Integer> itemsWithEstimates;
	
	public double getAverageRating(){
		double sumOfAllSubjects = 0;
		
		for(Map.Entry<Subject, Integer> entry : itemsWithEstimates.entrySet())
			sumOfAllSubjects+=entry.getValue();
		
		return itemsWithEstimates.size() > 0 ? (double)sumOfAllSubjects/itemsWithEstimates.size() : 0;
	}
	
	/**
	 * add new Subject with value
	 * change Subject
	 * delete subject
	 */
	

	public Map<Subject, Integer> getItemsWithEstimates() {
		return itemsWithEstimates;
	}

	public void setItemsWithEstimates(Map<Subject, Integer> itemsWithEstimates) {
		this.itemsWithEstimates = itemsWithEstimates;
	}
	
}
