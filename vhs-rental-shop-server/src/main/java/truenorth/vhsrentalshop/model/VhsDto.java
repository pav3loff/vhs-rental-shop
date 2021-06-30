package truenorth.vhsrentalshop.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class VhsDto {
	
	@NotBlank(message = "{title.invalid}")
	private String title;
	
	private String plot;
	
	@Min(value = 1888, message = "{year.invalid}")
	private int year;

	public VhsDto(@NotBlank(message = "{title.invalid}") String title, String plot,
			@Min(value = 1888, message = "{year.invalid}") int year) {
		super();
		this.title = title;
		this.plot = plot;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plot == null) ? 0 : plot.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof VhsDto) {
			VhsDto other = (VhsDto) obj;
			
			return title.equals(other.title) &&
					plot.equals(other.plot) &&
					year == other.year;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("title: %s, year: %d", title, year);
	}

}
