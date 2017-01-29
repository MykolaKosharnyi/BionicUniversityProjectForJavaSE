package model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
	long create(T t);

	Optional<T> find(long id);

	List<T> findAll();

	void update(T t);

	void delete(long id);
}
