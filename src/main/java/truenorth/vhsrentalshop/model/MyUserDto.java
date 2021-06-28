package truenorth.vhsrentalshop.model;

import java.util.List;

public class MyUserDto {
	
	private String username;

	private String firstName;
	
	private String lastName;
	
	private List<Rental> rentals;

	public MyUserDto(String username, String firstName, String lastName, List<Rental> rentals) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.rentals = rentals;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((rentals == null) ? 0 : rentals.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MyUserDto) {
			MyUserDto other = (MyUserDto) obj;
			
			return username.equals(other.username);
		}
		
		return false;
	}

}
