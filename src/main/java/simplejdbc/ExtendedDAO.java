package simplejdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
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
		String sql = "SELECT DISTINCT STATE FROM CUSTOMER";
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
}
