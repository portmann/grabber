package ch.lgt.portmann.sqllayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteHandler {

	Connection c = null;

	public SQLiteHandler(String connectionString) {

		try {
			connect(connectionString);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connect(String connectionString) throws SQLException, ClassNotFoundException {

		Class.forName("org.sqlite.JDBC");
		this.c = DriverManager.getConnection(connectionString);

	}

	public ResultSet runQuery(String query) throws SQLException {

		Statement stmt = c.createStatement();

		return stmt.executeQuery(query);

	}

	public int runUpdate(String query) {

		try {
			PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			int generatedKey = 0;
			if (rs.next()) {
				generatedKey = rs.getInt(1);
				return generatedKey;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;

	}
}
