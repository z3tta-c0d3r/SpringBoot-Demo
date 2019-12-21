package com.example.SpringDemo2.config.details;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.SpringDemo2.constants.Constants;
import com.example.SpringDemo2.model.User;
import com.example.SpringDemo2.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User details for a oauth2
 */
@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class CrmUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;

    /**
     * Constructor
     * @param user User
     */
    public CrmUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = translate(user.getRoles());
    }

    /**
     * Get authorities of user
     * @param roles List<UserRole>
     * @return Collection<? extends GrantedAuthority>
     */
    private Collection<? extends GrantedAuthority> translate(List<UserRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : roles) {
            String name = role.getName().toUpperCase();
            if (!name.startsWith(Constants.ROLE)) {
                name = Constants.ROLE + name;
            }
            authorities.add(new SimpleGrantedAuthority(name));
        }
        return authorities;
    }

    /**
     * Is account non Expired
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Is Account non locked
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Is Credentials non expired
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Is user is enabled
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}