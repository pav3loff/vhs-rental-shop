package truenorth.vhsrentalshop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vhs_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(unique = true)
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@OneToMany(mappedBy = "user")
	private List<Rental> rentals;
	
	@Enumerated(value = EnumType.STRING)
	private Role role;

	public User() {
		super();
	}

	public User(@NotNull String username, @NotNull String password, @NotNull String firstName, @NotNull String lastName,
			List<Rental> rentals, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.rentals = rentals;
		this.role = role;
	}

	public User(int id, @NotNull String username, @NotNull String password, @NotNull String firstName,
			@NotNull String lastName, List<Rental> rentals, Role role) {
		this(username, password, firstName, lastName, rentals, role);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((rentals == null) ? 0 : rentals.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User) {
			User other = (User) obj;
			
			return id == other.id;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return String.format("username: %s, firstName: %s, lastName: %s", username, firstName, lastName);
	}

}
