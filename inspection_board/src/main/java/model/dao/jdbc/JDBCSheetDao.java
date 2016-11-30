package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import model.dao.SheetDao;
import model.entity.Department;
import model.entity.Enrollee;
import model.entity.Sheet;

public class JDBCSheetDao implements SheetDao {

	static Logger logger = Logger.getLogger(JDBCSheetDao.class);
	
	@Override
	public long add(long idEnrollee, long idDepartment) {
		long result = 0;
		try (Connection cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = cn.prepareStatement(
						"INSERT INTO sheet (id_enrollee, id_department) values (?,?)",
						Statement.RETURN_GENERATED_KEYS);) {

			st.setLong(1, idEnrollee);
			st.setLong(2, idDepartment);
			st.executeUpdate();

			try (ResultSet key = st.getGeneratedKeys();) {
				if (key.next())
					result = key.getInt(1);

			}
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		return result;
	}

	@Override
	public Sheet getSheet() {
		Sheet sheet = Sheet.getInstance();

		try (Connection cn = JdbcConnection.getInstance().getConnection(); Statement st = cn.createStatement();) {

			ResultSet rs = st.executeQuery("SELECT DISTINCT id_department FROM sheet; ");
			Map<Department, List<Enrollee>> sheetMap = new HashMap<Department, List<Enrollee>>();
			while (rs.next()) {
				sheetMap.put(new JDBCDaoFactory().createDepartmentDao().find(rs.getLong(1)),
						getEnrolleeByDepartmentID(rs.getLong(1)));
			}

			sheet.setSheet(sheetMap);

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		return sheet;
	}

	private List<Enrollee> getEnrolleeByDepartmentID(long id) {
		List<Enrollee> enrollee = new ArrayList<Enrollee>();

		try (Connection cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = cn.prepareStatement("SELECT id_enrollee FROM sheet where id_department = ? ");) {

			st.setLong(1, id);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				enrollee.add(new JDBCDaoFactory().createEnrolleeDao().find(rs.getLong(1)));
			}

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		return enrollee;
	}
	
	@Override
	public void deleteDepartment(long idDepartment) {
		try (Connection cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = cn.prepareStatement("DELETE FROM sheet WHERE id_department = ?");) {

			st.setLong(1, idDepartment);
			st.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
	}

	@Override
	public void deleteEnrollee(long idEnrollee) {
		try (Connection cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = cn.prepareStatement("DELETE FROM sheet WHERE id_enrollee  = ?");) {

			st.setLong(1, idEnrollee);
			st.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}

	}

	@Override
	public void deleteEnrolleeFromDepartment(long idEnrollee, long idDepartment) {
		try (Connection cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = cn
						.prepareStatement("DELETE FROM sheet WHERE id_enrollee  = ? and id_department = ?");) {

			st.setLong(1, idEnrollee);
			st.setLong(2, idDepartment);
			st.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
	}

}
