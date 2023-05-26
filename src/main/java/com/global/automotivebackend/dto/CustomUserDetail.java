//package com.global.automotivebackend.dto;
//
//import java.util.Collection;
//import java.util.HashSet;
//
//import com.global.automotivebackend.model.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class CustomUserDetail implements UserDetails {
//    private User user;
//
//    public CustomUserDetail(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        HashSet<SimpleGrantedAuthority> set = new HashSet<>();
//        set.add(new SimpleGrantedAuthority("user"));
//        return set;
//    }
//
//    public String getPassword() {
//        return this.user.getPassword();
//    }
//
//    public String getUsername() {
//        return this.user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    public boolean isEnabled() {
//        return true;
//    }
//}
