package com.example.jee.projetjee.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUser;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;


    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(targetEntity = Cart.class, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Cart> carts;

    @Column
    private boolean isAccountNonExpired = true;

    @Column
    private boolean isAccountNonLocked = true;

    @Column
    private boolean isCredentialsNonExpired = true;

    @Column
    private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
}
