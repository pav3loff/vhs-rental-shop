package truenorth.vhsrentalshop.services;

import java.util.List;

import truenorth.vhsrentalshop.model.Vhs;
import truenorth.vhsrentalshop.model.VhsDto;

public interface VhsService {
	
	List<Vhs> getAllVhses();

	Vhs getVhs(int id);
	
	Vhs addVhs(VhsDto vhsDto);
	
	Vhs updateVhs(int id, VhsDto vhsDto);
	
	boolean deleteVhs(int id);
}
