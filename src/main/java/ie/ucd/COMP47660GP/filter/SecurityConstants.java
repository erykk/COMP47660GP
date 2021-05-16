package ie.ucd.COMP47660GP.filter;


public final class SecurityConstants {

    // Signing key for HS512 algorithm
    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys


    // JWT token defaults
    public static final String SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String COOKIE_NAME = "JWT";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/registration";
}

