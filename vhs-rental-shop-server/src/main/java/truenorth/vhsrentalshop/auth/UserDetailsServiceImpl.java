package truenorth.vhsrentalshop.auth;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import truenorth.vhsrentalshop.model.User;
import truenorth.vhsrentalshop.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			
			return new UserDetailsImpl(user.getUsername(), user.getPassword(), 
					Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
		} else {
			throw new UsernameNotFoundException(String.format("Username %s not found", username));
		}
	}

}
