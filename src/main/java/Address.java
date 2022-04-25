
public class Address {
	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private String lat;
	private String lng;
	
	public Address() {
		super();
	}

	
	public Address(String street) {
		super();
		this.street = street;
	}


	public Address(String street, String suite, String city, String zipcode, String lat, String lng) {
		super();
		this.street = street;
		this.suite = suite;
		this.city = city;
		this.zipcode = zipcode;
		this.lat = lat;
		this.lng = lng;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getSuite() {
		return suite;
	}



	public void setSuite(String suite) {
		this.suite = suite;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getZipcode() {
		return zipcode;
	}



	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}



	public String getLat() {
		return lat;
	}



	public void setLat(String lat) {
		this.lat = lat;
	}



	public String getLng() {
		return lng;
	}



	public void setLng(String lng) {
		this.lng = lng;
	}

	
}
