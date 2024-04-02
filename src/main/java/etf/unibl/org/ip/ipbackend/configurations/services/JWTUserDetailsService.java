package etf.unibl.org.ip.ipbackend.configurations.services;

import etf.unibl.org.ip.ipbackend.configurations.models.JWTUser;
import etf.unibl.org.ip.ipbackend.models.entities.UserEntity;
import etf.unibl.org.ip.ipbackend.respositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return modelMapper.map(user, JWTUser.class);
    }
}
