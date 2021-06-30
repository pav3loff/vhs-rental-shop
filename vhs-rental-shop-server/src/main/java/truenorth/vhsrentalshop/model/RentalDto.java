package truenorth.vhsrentalshop.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RentalDto {
	
	@NotBlank(message = "{username.invalid}")
	private String username;
	
	@NotNull
	private int vhsId;

	public RentalDto(String username, int vhsId) {
		super();
		this.username = username;
		this.vhsId = vhsId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getVhsId() {
		return vhsId;
	}

	public void setVhsId(int vhsId) {
		this.vhsId = vhsId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + username.hashCode();
		result = prime * result + vhsId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof RentalDto) {
			RentalDto other = (RentalDto) obj;
			
			return username.equals(other.username) && vhsId == other.vhsId;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("username: %s, vhsId: %d", username, vhsId);
	}

}
