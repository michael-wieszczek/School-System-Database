package schoolSystemDatabase;

import schoolSystemDatabase.InvalidInputException;

/**
 * Address.java <br>
 * This class contains the address object, and it's getters and setters. It is apart of
 * the student object and takes input from SchoolSystemDatabase and formats it and saves it to
 * the address object. <br>
 * @author Michael Wieszczek <br>
 * January 20th, 2020
 */
public class Address {
	
	private String street;
	private String city;
	private String province;
	private String postal;
	
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		char cap = street.toUpperCase().charAt(0);
		street = cap + street.substring(1);
		this.street = street;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		char cap = city.toUpperCase().charAt(0);
		city = cap + city.substring(1);
		this.city = city;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) throws InvalidInputException{
		if(province.compareToIgnoreCase("Ontario") == 0 || province.compareToIgnoreCase("ON") == 0) {
			province = "Ontario";
		}
		else if(province.compareToIgnoreCase("Quebec") == 0 || province.compareToIgnoreCase("QC") == 0) {
			province = "Quebec";
		}
		else if(province.compareToIgnoreCase("Prince Edward Island") == 0 || province.compareToIgnoreCase("PE") == 0) {
			province = "Prince Edward Island";
		}
		else if(province.compareToIgnoreCase("Saskatchewan") == 0 || province.compareToIgnoreCase("SK") == 0) {
			province = "Saskatchewan";
		}
		else if(province.compareToIgnoreCase("Alberta") == 0 || province.compareToIgnoreCase("AB") == 0) {
			province = "Alberta";
		}
		else if(province.compareToIgnoreCase("British Columbia") == 0 || province.compareToIgnoreCase("BC") == 0) {
			province = "British Columbia";
		}
		else if(province.compareToIgnoreCase("Manitoba") == 0 || province.compareToIgnoreCase("MB") == 0) {
			province = "Manitoba";
		}
		else if(province.compareToIgnoreCase("New Brunswick") == 0 || province.compareToIgnoreCase("NB") == 0) {
			province = "New Brunswick";
		}
		else if(province.compareToIgnoreCase("Newfoundland and Labrador") == 0 || province.compareToIgnoreCase("NL") == 0) {
			province = "Newfoundland and Labrador";
		}
		else if(province.compareToIgnoreCase("Nova Scotia") == 0 || province.compareToIgnoreCase("NS") == 0) {
			province = "Nova Scotia";
		}
		else if(province.compareToIgnoreCase("Northwest Territories") == 0 || province.compareToIgnoreCase("NT") == 0) {
			province = "Northwest Territories";
		}
		else if(province.compareToIgnoreCase("Nunavut") == 0 || province.compareToIgnoreCase("NU") == 0) {
			province = "Nunavut";
		}
		else if(province.compareToIgnoreCase("Yukon") == 0 || province.compareToIgnoreCase("YT") == 0) {
			province = "Yukon";
		}
		else {
			throw new InvalidInputException();
		}
		this.province = province;
	}
	/**
	 * @return the postal
	 */
	public String getPostal() {
		return postal;
	}
	/**
	 * @param postal the postal to set
	 */
	public void setPostal(String postal) throws InvalidInputException{
		if(postal.length() == 6) {
			postal = postal.toUpperCase();
			if((postal.charAt(0) >= 65 && postal.charAt(0) <= 90) && (postal.charAt(2) >= 65 && postal.charAt(2) <= 90) && (postal.charAt(4) >= 65 && postal.charAt(4) <= 90) && (postal.charAt(1) <= 57 && postal.charAt(1) >= 48) && (postal.charAt(3) <= 57 && postal.charAt(3) >= 48) && (postal.charAt(5) <= 57 && postal.charAt(5) >= 48)) {
				this.postal = postal;
			}
			else {
				throw new InvalidInputException();
			}
		}
		else {
			throw new InvalidInputException();
		}
	}
}

