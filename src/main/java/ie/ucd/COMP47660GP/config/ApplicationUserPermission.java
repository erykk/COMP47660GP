package ie.ucd.COMP47660GP.config;

public enum ApplicationUserPermission {

    FLIGHT_READ("flight:read"),
    RESERVATION_READ("reservation:read");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
    public String getPermission(){
        return permission;
    }
}
