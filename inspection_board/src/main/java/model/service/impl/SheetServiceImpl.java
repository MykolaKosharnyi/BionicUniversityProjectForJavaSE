package model.service.impl;

import java.util.List;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.SheetDao;
import model.entity.Department;
import model.entity.Sheet;
import model.service.SheetService;

public class SheetServiceImpl implements SheetService{
	private DaoFactory daoFactory = DaoFactory.getInstance();

	private SheetServiceImpl() {
	}

	private static class Holder {
		static final SheetServiceImpl INSTANCE = new SheetServiceImpl();
	}

	public static SheetServiceImpl getInstance() {
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

	public Sheet getSheet() {
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

	@Override
	public List<Department> findByEnrolleeId(long idEnrollee) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			SheetDao dao = daoFactory.createSheetDao(connection);
			return dao.findByEnrolleeId(idEnrollee);
		}
	}

}
