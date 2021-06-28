package truenorth.vhsrentalshop.model;

import javax.validation.constraints.NotBlank;

public class CreateUserDto {
	
	@NotBlank(message = "{firstname.invalid}")
	private String firstName;
	
	@NotBlank(message = "{lastname.invalid}")
	private String lastName;
	
	@NotBlank(message = "{username.invalid}")
	private String username;
	
	@NotBlank(message = "{password.invalid}")
	private String password;

	public CreateUserDto(@NotBlank(message = "{firstname.invalid}") String firstName,
			@NotBlank(message = "{lastname.invalid}") String lastName,
			@NotBlank(message = "{username.invalid}") String username,
			@NotBlank(message = "{password.invalid}") String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CreateUserDto) {
			CreateUserDto other = (CreateUserDto) obj;
			
			return username.equals(other.username);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("username: %s, firstName: %s, lastName: %s", username, firstName, lastName);
	}

}
