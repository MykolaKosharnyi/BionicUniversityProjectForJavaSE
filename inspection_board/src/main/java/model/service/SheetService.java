package model.service;

import java.util.List;
import java.util.Map;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.SheetDao;
import model.entity.Department;
import model.entity.User;

public class SheetService {
	private DaoFactory daoFactory = DaoFactory.getInstance();

	private SheetService() {
	}

	private static class Holder {
		static final SheetService INSTANCE = new SheetService();
	}

	public static SheetService getInstance() {
		return Holder.INSTANCE;
	}

	public void add(long idEnrollee, long idDepartment) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			SheetDao dao = daoFactory.createSheetDao(connection);
			dao.add(idEnrollee, idDepartment);
			connection.commit();
		}
	}

	public Map<Department, List<User>> getSheet() {
		try (DaoConnection connection = daoFactory.getConnection()) {
			SheetDao dao = daoFactory.createSheetDao(connection);
			return dao.getSheet();
		}
	}

	public void deleteDepartment(long idDepartment) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			SheetDao dao = daoFactory.createSheetDao(connection);
			dao.deleteDepartment(idDepartment);
			connection.commit();
		}
	}

	public void deleteEnrollee(long idEnrollee) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			SheetDao dao = daoFactory.createSheetDao(connection);
			dao.deleteEnrollee(idEnrollee);
			connection.commit();
		}
	}

	public void deleteEnrolleeFromDepartment(long idEnrollee, long idDepartment) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			SheetDao dao = daoFactory.createSheetDao(connection);
			dao.deleteEnrolleeFromDepartment(idEnrollee, idDepartment);
			connection.commit();
		}
	}

}
