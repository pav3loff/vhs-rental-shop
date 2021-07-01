package truenorth.vhsrentalshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import truenorth.vhsrentalshop.model.Rental;
import truenorth.vhsrentalshop.model.Vhs;
import truenorth.vhsrentalshop.model.VhsDto;
import truenorth.vhsrentalshop.repositories.RentalRepository;
import truenorth.vhsrentalshop.repositories.VhsRepository;

@Service
@Slf4j
public class VhsServiceImpl implements VhsService {
	
	private VhsRepository vhsRepository;

	private RentalRepository rentalRepository;
	
	@Autowired
	public VhsServiceImpl(VhsRepository vhsRepository, RentalRepository rentalRepository) {
		this.vhsRepository = vhsRepository;
		this.rentalRepository = rentalRepository;
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
			Vhs vhs = vhsRepository.findById(id).get();
			
			log.info(String.format("Delete vhs: %s", vhs.toString()));
			
			vhsRepository.deleteById(id);
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean isAvailable(int id) throws IllegalArgumentException {
		Optional<Rental> optionalRental = rentalRepository.findByVhsId(id);

		return !(optionalRental.isPresent());
	}

}
