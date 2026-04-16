package com.quantitymeasurement.user_service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.quantitymeasurement.user_service.entity.User;
import com.quantitymeasurement.user_service.repository.UserRepository;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository repo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
                                        throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        if (email == null) {
            response.sendRedirect("http://localhost:4200/login?error=email_not_found");
            return;
        }

        // User nahi mila toh auto-register karo (Google login ke liye)
        User user = repo.findByUsername(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setUsername(email);
            newUser.setPassword(""); // OAuth user ka password blank
            return repo.save(newUser);
        });

        String token = jwtUtil.generateToken(user.getUsername(), user.getId());

        String redirectUrl = "http://localhost:4200/login?token=" + token;
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}