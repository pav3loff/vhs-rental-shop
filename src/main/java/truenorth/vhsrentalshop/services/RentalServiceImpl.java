package truenorth.vhsrentalshop.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import truenorth.vhsrentalshop.model.Rental;
import truenorth.vhsrentalshop.model.RentalDto;
import truenorth.vhsrentalshop.model.User;
import truenorth.vhsrentalshop.model.Vhs;
import truenorth.vhsrentalshop.repositories.RentalRepository;
import truenorth.vhsrentalshop.repositories.UserRepository;
import truenorth.vhsrentalshop.repositories.VhsRepository;
import truenorth.vhsrentalshop.utils.FeesCalculator;

@Service
@Slf4j
public class RentalServiceImpl implements RentalService {
	
	private RentalRepository rentalRepository;
	
	private UserRepository userRepository;
	
	private VhsRepository vhsRepository;
	
	private FeesCalculator feesCalculator;
	
	@Value("${rental.duration}")
	private int rentalDuration;
	
	@Autowired
	public RentalServiceImpl(RentalRepository rentalRepository, UserRepository userRepository, 
			VhsRepository vhsRepository, FeesCalculator feesCalculator) {
		this.rentalRepository = rentalRepository;
		this.userRepository = userRepository;
		this.vhsRepository = vhsRepository;
		this.feesCalculator = feesCalculator;
	}

	@Override
	public List<Rental> getAllRentals() {
		return rentalRepository.findAll();
	}

	@Override
	public Rental getRental(int id) {
		Optional<Rental> optionalRental = rentalRepository.findById(id);
		
		if(optionalRental.isPresent()) {
			return optionalRental.get();
		}
		
		return null;
	}

	@Override
	public Rental addRental(RentalDto rentalDto) {
		String username = rentalDto.getUsername();
		
		if(userRepository.existsByUsername(username)) {
			int vhsId = rentalDto.getVhsId();
			
			if(vhsRepository.existsById(vhsId)) {
				User user = userRepository.findByUsername(username).get();
				Vhs vhs = vhsRepository.findById(vhsId).get();
				
				Rental rental = new Rental(vhs, user, LocalDateTime.now(), 
						LocalDateTime.now().plusDays(rentalDuration), null, BigDecimal.valueOf(0));
				
				return rentalRepository.saveAndFlush(rental);
			}
			
			throw new IllegalArgumentException(String.format("Vhs with id %d does not exist!", vhsId));
		}
		
		throw new IllegalArgumentException(String.format("User with username %s does not exist!", username));
	}

	@Override
	public Rental returnRental(int id) {
		Optional<Rental> optionalRental = rentalRepository.findById(id);
		
		if(optionalRental.isPresent()) {
			Rental rental = optionalRental.get();
			
			rental.setDateReturned(LocalDateTime.now());
			
			if(rental.getDateReturned().isAfter(rental.getDueDate())) {
				rental.setLateFee(feesCalculator.calculateFee(rental.getDueDate(), rental.getDateReturned()));
			}
			
			return rentalRepository.saveAndFlush(rental);
		}
		
		return null;
	}

	@Override
	public boolean deleteRental(int id) {
		if(rentalRepository.existsById(id)) {
			Rental rental = rentalRepository.findById(id).get();
			
			log.info(String.format("Delete rental: %s", rental.toString()));
			
			rentalRepository.deleteById(id);
			
			return true;
		}
		
		return false;
	}

}
