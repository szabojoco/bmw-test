import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
	public static void main(String[] args) {

		Logger logCreator = Logger.getLogger("MyLog");
		logCreator.setUseParentHandlers(false);
		FileHandler fileHandler;
		String sourceUrl = "https://jsonplaceholder.typicode.com/users";

		try {
			URL url = new URL(sourceUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");
			connection.connect();

			try {
				fileHandler = new FileHandler("log.txt", true);
				logCreator.addHandler(fileHandler);
				SimpleFormatter formatter = new SimpleFormatter();
				fileHandler.setFormatter(formatter);
				logCreator.info("Trying to connect to: " + sourceUrl);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			int response = connection.getResponseCode();
			System.out.println(response);

			if (response != 200) {
				throw new RuntimeException("Error code: " + response);
			} else {
				logCreator.info("Url connection successfully!");
				Scanner scanner = new Scanner(url.openStream());
				String line = "";

				while (scanner.hasNext()) {
					line += scanner.nextLine();
				}
				scanner.close();

				JSONParser parser = new JSONParser();
				JSONArray dataArray = (JSONArray) parser.parse(line);
				
				Users user = new Users();
				
				for (int i = 0; i < dataArray.size(); ++i) {
					JSONObject element = (JSONObject) dataArray.get(i);

					
					JSONObject addressJSon = ((JSONObject) element.get("address"));
					JSONObject companyJSon = ((JSONObject) element.get("company"));

					Address address = new Address((String) addressJSon.get("street"));
					Company company = new Company((String) companyJSon.get("name"));

					user = new Users((Long) element.get("id"));
					user.setName((String) element.get("name"));
					user.setUsername((String) element.get("username"));
					user.setEmail((String) element.get("email"));
					user.setAddress(address);
					user.setPhone((String) element.get("phone"));
					user.setWebsite((String) element.get("website"));
					user.setCompany(company);

					// System.out.println(element.get("address"));
					// System.out.println(company.getName().toString());

					String regex = "^(.+)@(.+)$";
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(element.get("email").toString());
					if (!matcher.matches()) {
						System.out.println("Email address format invalid for: " + element.get("email"));
					}
				}

				JDBCPostgreSQLConnect sqlConnect = new JDBCPostgreSQLConnect();

				sqlConnect.saveUsers(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}