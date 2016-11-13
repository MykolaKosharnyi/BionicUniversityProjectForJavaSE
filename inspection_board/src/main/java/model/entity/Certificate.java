package model.entity;

import java.util.Map;
import java.util.TreeMap;

public class Certificate {//аттестат
	private Map<Subject, Integer> itemsWithEstimates = new TreeMap<>();
	
	public double getAverageRating(){
		int sumOfAllSubjects = 0;
		
		for(Map.Entry<Subject, Integer> entry : itemsWithEstimates.entrySet())
			sumOfAllSubjects+=entry.getValue();
		
		return itemsWithEstimates.size() > 0 ? sumOfAllSubjects/itemsWithEstimates.size() : 0;
	}
	
	/**
	 * add new Subject with value
	 * change Subject
	 * delete subject
	 */
	
	public boolean addSubject(Subject subject, Integer value){
		boolean contain = itemsWithEstimates.containsKey(subject);
		
		if(!contain)
			itemsWithEstimates.put(subject, value);
		
		return contain;
	}
	
	public void changeSubjectValue(Subject subject, Integer value){
		boolean contain = itemsWithEstimates.containsKey(subject);
		
		if(contain)
			itemsWithEstimates.put(subject, value);
	}
	
	public Integer deleteSubject(Subject subject){
		return itemsWithEstimates.remove(subject);
	}
}
