My Java project contains the following elements:

- the Main.java class
	- read the JSon data from this website: https://jsonplaceholder.typicode.com/users
	- make deserialize of data
	- create the "log.txt" file on the root of application, where store the status of connecting
	- invite the JDBCPostgreSQLConnect.java file, what makes a connection between our PostgresSQL database
	
- ReadAllAndGenerateTXT.java
	- a bonus application, what read all data from the JSon file and create the all_content.txt with all of datas
	
- other classes:
 	- Users.java class - define the variables for id, name, username, email, phone and website data and invite the Address and Company classes
 	- Company.java class - define the name, catchPhrase and bs variables
 	- Address.java class - street, suite, city, zipcode, lat and lng variables