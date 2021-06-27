package truenorth.vhsrentalshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rental {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	private Vhs vhs;
	
	@OneToOne
	@JsonIgnore
	private User user;
	
	@NotNull
	private LocalDateTime dateRented;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dueDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dateReturned;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private BigDecimal lateFee;

	public Rental() {
		super();
	}

	public Rental(Vhs vhs, User user, @NotNull LocalDateTime dateRented, 
			@NotNull LocalDateTime dueDate, LocalDateTime dateReturned, 
			BigDecimal lateFee) {
		super();
		this.vhs = vhs;
		this.user = user;
		this.dateRented = dateRented;
		this.dueDate = dueDate;
		this.dateReturned = dateReturned;
		this.lateFee = lateFee;
	}
	
	public Rental(int id, Vhs vhs, User user, @NotNull LocalDateTime dateRented, 
			@NotNull LocalDateTime dueDate, LocalDateTime dateReturned, 
			BigDecimal lateFee) {
		this(vhs, user, dateRented, dueDate, dateReturned, lateFee);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vhs getVhs() {
		return vhs;
	}

	public void setVhs(Vhs vhs) {
		this.vhs = vhs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getDateRented() {
		return dateRented;
	}

	public void setDateRented(LocalDateTime dateRented) {
		this.dateRented = dateRented;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDateTime getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(LocalDateTime dateReturned) {
		this.dateReturned = dateReturned;
	}

	public BigDecimal getLateFee() {
		return lateFee;
	}

	public void setLateFee(BigDecimal lateFee) {
		this.lateFee = lateFee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateRented == null) ? 0 : dateRented.hashCode());
		result = prime * result + ((dateReturned == null) ? 0 : dateReturned.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((lateFee == null) ? 0 : lateFee.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((vhs == null) ? 0 : vhs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Rental) {
			Rental other = (Rental) obj;
			
			return id == other.id;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("id: %d, user: %s %s, vhs: %s (%d)", 
				id, user.getFirstName(), user.getLastName(), vhs.getTitle(), vhs.getYear());
	}

}
