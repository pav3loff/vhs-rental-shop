package truenorth.vhsrentalshop.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserInfo {

    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public UserInfo(String username, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setRole(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

}
