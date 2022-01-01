package jmaster.security;

import jmaster.types.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityUtils {
    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();//only work with user has logged in
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();//lay thong tin nguoi dung dang nhap hien tai
            if (userDetails.getAuthorities().toString().equals(Role.ROLE_PREMIUM_MEMBER.toString())) {
                return true;
            }
        }
        return false;
    }
}
