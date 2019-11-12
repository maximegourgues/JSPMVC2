package simplejdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

public class ExtendedDAO extends DAO {
	/**
	 *
	 * @param dataSource la source de données à utiliser
	 */
	public ExtendedDAO(DataSource dataSource) {
		super(dataSource);
	}	

	/**
	 * Liste des codes d'états des USA présents dans la table CUSTOMER
	 *
	 * @return la liste des états
	 * @throws DAOException
	 */
	public List<String> existingStates() throws DAOException {
		List<String> result = new LinkedList<>();
		String sql = "SELECT DISTINCT STATE FROM CUSTOMER ORDER BY STATE";
		try (	Connection connection = myDataSource.getConnection(); 
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				String state = rs.getString("STATE");
				result.add(state);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return result;
	}
        public Map<String,Float> existingDiscountCode() throws DAOException {
		Map<String,Float> result = new HashMap<>();
		String sql = "SELECT * FROM DISCOUNT_CODE";
		try (	Connection connection = myDataSource.getConnection(); 
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				String code = rs.getString("DISCOUNT_CODE");
                                float rate = rs.getFloat("RATE");
				result.put(code,rate);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return result;
	}
}
