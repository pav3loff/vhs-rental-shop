package truenorth.vhsrentalshop.model;

import javax.validation.constraints.NotBlank;

public class UserDto {
	
	@NotBlank(message = "{firstname.invalid}")
	private String firstName;
	
	@NotBlank(message = "{lastname.invalid}")
	private String lastName;

	public UserDto(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof UserDto) {
			UserDto other = (UserDto) obj;
			
			return firstName.equals(other.firstName) && lastName.equals(other.lastName);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("firstName: %s, lastName: %s", firstName, lastName);
	}

}
