
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCPostgreSQLConnect {
	private final String url = "jdbc:postgresql://localhost/postgres";
	private final String user = "postgres";
	private final String password = "root";

	void saveUsers(Users users) {

		String sql = "INSERT INTO users(id, name, username, email, phone, website, address_id, company_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String sql2 = "INSERT INTO address(street, suite, city, id, zipcode, lat, lng) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String sql3 = "INSERT INTO company(name, catchPhrase, bs, id) VALUES (?, ?, ?, ?)";
		
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			if (connection != null) {
				System.out.println("Connected to PostgreSQL server successfully!");
			} else {
				System.out.println("Failed to connect to PostgreSQL server!");
			}
			
			PreparedStatement statement;
			PreparedStatement statement2;
			PreparedStatement statement3;
		
			statement = connection.prepareStatement(sql);
			statement2 = connection.prepareStatement(sql2);
			statement3 = connection.prepareStatement(sql3);

			statement.setLong(1, users.getId());
			statement.setString(2, users.getName());
			statement.setString(3, users.getUsername());
			statement.setString(4, users.getEmail());
			statement.setString(5, users.getPhone());
			statement.setString(6, users.getWebsite());
			statement.setLong(7, users.getId());
			statement.setLong(8, users.getId());
			
			statement2.setString(1, users.getAddress().getStreet());
			statement2.setString(2, users.getAddress().getSuite());
			statement2.setString(3, users.getAddress().getCity());
			statement2.setLong(4, users.getId());
			statement2.setString(5, users.getAddress().getZipcode());
			statement2.setString(6, users.getAddress().getLat());
			statement2.setString(7, users.getAddress().getLng());
			
			statement3.setString(1, users.getCompany().getName());
			statement3.setString(2, users.getCompany().getCatchPhrase());
			statement3.setString(3, users.getCompany().getBs());
			statement3.setLong(4, users.getId());

			
			
			int rows2 = statement2.executeUpdate();
			if (rows2 > 0) {
				System.out.println("A new row has inserted.");
			}
			
			int rows3 = statement3.executeUpdate();
			if (rows3 > 0) {
				System.out.println("A new row has inserted.");
			}
			
			int rows = statement.executeUpdate();
			if (rows > 0) {
				System.out.println("A new row has inserted.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		

	}

}
