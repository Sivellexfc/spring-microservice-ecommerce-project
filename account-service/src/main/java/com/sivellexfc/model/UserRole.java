package com.sivellexfc.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum UserRole {

    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER"),
    SELLER("SELLER");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static List<GrantedAuthority> mapToGrantedAuthorities(UserRole userRole) {
        return Arrays.stream(userRole.getRoleName().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
