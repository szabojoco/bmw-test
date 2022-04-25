import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadAllAndGenerateTXT {

	public static void main(String[] args) {
		try {
			URL u = new URL("https://jsonplaceholder.typicode.com/users");
			HttpURLConnection hr = (HttpURLConnection) u.openConnection();

			if (hr.getResponseCode() == 200) {
				InputStream inputStream = hr.getInputStream();
				@SuppressWarnings("unused")
				StringBuffer stringBuffer = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				FileOutputStream fileOutputStream = new FileOutputStream("all_content.txt");
				@SuppressWarnings("resource")
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
				String line = reader.readLine();
				while (line != null) {
					System.out.println(line);
					writer.write(line);
					writer.newLine();
					writer.flush();
					line = reader.readLine();
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
