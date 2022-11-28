package vn.codingt.clean.presenter.usecases.secutiry;

import java.util.Collection;
import java.util.Collections;

import vn.codingt.clean.data.db.jpa.entities.UserData;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "name", "email", "password","address"})
public class UserPrincipal implements UserDetails {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal from(UserData customerData) {
        return new UserPrincipal(customerData.getId(), customerData.getName(), customerData.getEmail(),
                customerData.getPassword(), customerData.getAddress(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public String getUsername() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
