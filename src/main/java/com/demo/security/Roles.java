package com.demo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.demo.security.Permissions.*;

public enum Roles {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, COURSE_READ, COURSE_WRITE)),
    ADMINTRANIEE(Sets.newHashSet(STUDENT_READ, COURSE_READ));

    private final Set<Permissions> permissions;

    Roles(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority>  grantedAuthorities (){
        Set<SimpleGrantedAuthority> grantedPermissions =
                getPermissions()
                        .stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                        .collect(Collectors.toSet());
        grantedPermissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));

        return grantedPermissions;
    }
}
