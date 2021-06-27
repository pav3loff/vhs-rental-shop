package truenorth.vhsrentalshop.model;

import javax.validation.constraints.NotNull;

public class RentalDto {
	
	@NotNull
	private int userId;
	
	@NotNull
	private int vhsId;

	public RentalDto(int userId, int vhsId) {
		super();
		this.userId = userId;
		this.vhsId = vhsId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
		result = prime * result + userId;
		result = prime * result + vhsId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof RentalDto) {
			RentalDto other = (RentalDto) obj;
			
			return userId == other.userId && vhsId == other.vhsId;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("userId: %d, vhsId: %d", userId, vhsId);
	}

}
