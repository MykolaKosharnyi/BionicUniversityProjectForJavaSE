package model.dao;

import java.util.List;

public interface GenericDAO<T> {
	 long create( T t );
	 T find( long id );
	 List<T> findAll();
	 void update(T t);
	 void delete(long id);
}
