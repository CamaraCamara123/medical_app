package ma.medical_app.medical_app.security.controllers;

import org.springframework.web.bind.annotation.RestController;


import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import ma.medical_app.medical_app.security.entities.User;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/csrf-totken")
    public CsrfToken csrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/whoami")
    public String whoami() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            System.out.println(user);
            return "Hello, " + authentication.getName() + "! Roles : " + user.getAuthorities();
        } else {
            return "Not authenticated";
        }
    }

    @GetMapping("/admin")
    @RolesAllowed(value = { "ADMIN" })
    // @PreAuthorize(value = "hasRole('ADMIN')")
    public String admin(Authentication authentication) {
        System.out.println(authentication);
        return "Welcome Admin";
    }

}
