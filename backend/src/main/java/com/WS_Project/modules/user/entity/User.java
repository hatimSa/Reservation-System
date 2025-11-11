package com.WS_Project.modules.user.entity;

import com.WS_Project.core.security.token.Token;
import com.WS_Project.modules.restaurant.entity.Restaurant;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String phone;

    @Column
    private String address;   // nouveau

    @Column
    private String website;   // nouveau

    @Column
    private String logoUrl;   // nouveau pour stocker le chemin du logo

    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
            new SimpleGrantedAuthority("ROLE_OWNER"),
            new SimpleGrantedAuthority("OWNER_READ"),
            new SimpleGrantedAuthority("OWNER_CREATE"),
            new SimpleGrantedAuthority("OWNER_UPDATE"),
            new SimpleGrantedAuthority("OWNER_DELETE")
        );
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
