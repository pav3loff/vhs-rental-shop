package truenorth.vhsrentalshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Vhs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String title;
	
	private String plot;
	
	@NotNull
	private int year;
	
	public Vhs() {
		super();
	}

	public Vhs(String title, String plot, int year) {
		super();
		this.title = title;
		this.plot = plot;
		this.year = year;
	}
	
	public Vhs(int id, String title, String plot, int year) {
		this(title, plot, year);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		result = prime * result + id;
		result = prime * result + ((plot == null) ? 0 : plot.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Vhs) {
			Vhs other = (Vhs) obj;
			
			return this.id == other.id;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("id: %d, title: %s, year: %d", id, title, year);
	}
}
