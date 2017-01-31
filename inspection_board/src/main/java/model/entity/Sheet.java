package model.entity;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Sheet {// Ведомость
	private Map<Department, List<User>> table;

	public Sheet() {}

	public Map<Department, List<User>> getTable() {
		return table;
	}

	public void setTable(Map<Department, List<User>> table) {
		this.table = table;
	}

	@Override
	public String toString(){
		StringBuilder tableString = new StringBuilder();
		Iterator<Department> iterator = table.keySet().iterator();
		
		while( iterator.hasNext() ){
			Department currectDepartment = iterator.next();
			tableString.append("{");
			tableString.append( currectDepartment.getNameDepartment() );
			tableString.append(" : ");
			tableString.append( table.get(currectDepartment).toString() );
			tableString.append("}");
			
			if( iterator.hasNext() ){
				tableString.append(", ");
			}
		}
		
		return "Sheet: [" + tableString.toString() + "]";
	}

}
