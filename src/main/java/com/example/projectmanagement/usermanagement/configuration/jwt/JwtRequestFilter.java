package com.example.projectmanagement.usermanagement.configuration.jwt;

import com.example.projectmanagement.usermanagement.configuration.UserPrincipalService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final String[] AUTH_WHITELIST = {
            "/api/user/signIn",
            "/api/user/create",
            ".*/api/process-login/.*",
            ".*/api/process-login/*",
            ".*/api/logout/.*",
            ".*/api/logout/*",
            ".*/actuator/*",
            ".*/actuator/.*",
            ".*/health/.*",
            ".*/health/*",
            ".*/v3/api-docs/.*",
            ".*/v3/api-docs.*",
            ".*/v3/api-docs/swagger-config/.*",
            ".*/v2/api-docs.*",
            ".*/swagger-ui.html",
            ".*/swagger-ui/.*",
            ".*/swagger-resources/.*",
            ".*/webjars/.*"
    };
    private final JwtProvider jwtProvider;
    private final UserPrincipalService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String token = getJwtFromRequest(request);
        if (token != null && jwtProvider.isExpired(token)) {
            Claims claims = jwtProvider.getAllClaimsFromToken(token);
            String username = claims.getSubject();

            UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestURI = request.getRequestURI();

        for (String url : AUTH_WHITELIST) {
            if (requestURI.matches(url)) {
                return true;
            }
        }
        return false;
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
