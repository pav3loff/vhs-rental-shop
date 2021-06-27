package truenorth.vhsrentalshop.model;

import java.util.List;

import javax.persistence.Entity;
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
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@OneToMany(mappedBy = "user")
	private List<Rental> rentals;

	public User() {
		super();
	}

	public User(@NotNull String firstName, @NotNull String lastName, List<Rental> rentals) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.rentals = rentals;
	}

	public User(int id, @NotNull String firstName, @NotNull String lastName, List<Rental> rentals) {
		this(firstName, lastName, rentals);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		return String.format("id: %d, firstName: %s, lastName: %s", id, firstName, lastName);
	}

}
