package truenorth.vhsrentalshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import truenorth.vhsrentalshop.model.Vhs;
import truenorth.vhsrentalshop.model.VhsDto;
import truenorth.vhsrentalshop.repositories.VhsRepository;

@Service
public class VhsServiceImpl implements VhsService {
	
	private VhsRepository vhsRepository;
	
	@Autowired
	public VhsServiceImpl(VhsRepository vhsRepository) {
		this.vhsRepository = vhsRepository;
	}
	
	@Override
	public List<Vhs> getAllVhses() {
		return vhsRepository.findAll();
	}

	@Override
	public Vhs getVhs(int id) {
		Optional<Vhs> optionalVhs = vhsRepository.findById(id);
		
		if(optionalVhs.isPresent()) {
			return optionalVhs.get();
		}
		
		return null;
	}

	@Override
	public Vhs addVhs(VhsDto vhsDto) {
		Vhs vhs = new Vhs(vhsDto.getTitle(), vhsDto.getPlot(), vhsDto.getYear());
		
		return vhsRepository.save(vhs);
	}

	@Override
	public Vhs updateVhs(int id, VhsDto vhsDto) {
		Optional<Vhs> optionalVhs = vhsRepository.findById(id);
		
		if(optionalVhs.isPresent()) {
			Vhs vhs = optionalVhs.get();
			
			vhs.setTitle(vhsDto.getTitle());
			vhs.setPlot(vhsDto.getPlot());
			vhs.setYear(vhsDto.getYear());
			
			return vhsRepository.saveAndFlush(vhs);
		} 
		
		return null;
	}

	@Override
	public boolean deleteVhs(int id) {
		if(vhsRepository.existsById(id)) {
			vhsRepository.deleteById(id);
			
			return true;
		}
		
		return false;
	}

}
