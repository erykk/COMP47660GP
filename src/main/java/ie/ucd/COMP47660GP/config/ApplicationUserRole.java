package ie.ucd.COMP47660GP.config;

import com.google.common.collect.Sets;
import ie.ucd.COMP47660GP.config.ApplicationUserPermission;

import java.util.Set;

public enum ApplicationUserRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ApplicationUserPermission.FLIGHT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions){
        this.permissions = permissions;
    }
}
