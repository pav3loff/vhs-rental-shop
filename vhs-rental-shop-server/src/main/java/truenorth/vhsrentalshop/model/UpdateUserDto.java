package truenorth.vhsrentalshop.model;

import javax.validation.constraints.NotBlank;

public class UpdateUserDto {
	
	@NotBlank(message = "{firstname.invalid}")
	private String firstName;
	
	@NotBlank(message = "{lastname.invalid}")
	private String lastName;
	
	@NotBlank(message = "{password.invalid}")
	private String password;
	
	public UpdateUserDto(@NotBlank(message = "{firstname.invalid}") String firstName,
			@NotBlank(message = "{lastname.invalid}") String lastName,
			@NotBlank(message = "{password.invalid}") String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof UpdateUserDto) {
			UpdateUserDto other = (UpdateUserDto) obj;
			
			return firstName.equals(other.firstName) && lastName.equals(other.lastName) 
					&& password.equals(other.password);
		}
		
		return false;
	}

}
