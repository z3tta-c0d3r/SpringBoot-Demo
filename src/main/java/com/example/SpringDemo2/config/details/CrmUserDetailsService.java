package com.example.SpringDemo2.config.details;

import com.example.SpringDemo2.config.details.CrmUserDetails;
import com.example.SpringDemo2.model.User;
import com.example.SpringDemo2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("crmUserDetailsService")
@RequiredArgsConstructor
public class CrmUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Load User by username in authentication oauth
     * @param userName String
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        if(user == null){
            throw new UsernameNotFoundException("UserName "+userName+" not found");
        }
        return new CrmUserDetails(user);
    }

}
