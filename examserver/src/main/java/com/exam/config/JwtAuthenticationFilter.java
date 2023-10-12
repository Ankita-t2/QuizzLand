package com.exam.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.service.impl.UserDetailsServiceimpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
@CrossOrigin(origins = "*" , exposedHeaders = "**")
public class JwtAuthenticationFilter  extends OncePerRequestFilter{
	@Autowired
	private UserDetailsServiceimpl userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//final String requestTokenHeader=request.getHeader("Authorization");
		final String requestTokenHeader= request.getHeader("authorization");
		System.out.println(requestTokenHeader);
		String username=null;
		String jwtToken=null;
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
		    jwtToken = requestTokenHeader.substring(7);
		    System.out.println("Received JWT Token: " + jwtToken); // Add this line for debugging
		    try {
		        username = this.jwtUtil.extractUsername(jwtToken);
		    } catch (ExpiredJwtException e) {
		        System.out.println("JWT Token has expired");
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println("Error");
		    }
		} else {
		    System.out.println("Invalid token, does not start with 'Bearer '");
		}
		//validated 
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			final UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
			if(this.jwtUtil.validateToken(jwtToken, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
			}
		}else
		{
			System.out.println("Token is not valid");
		}
		
		filterChain.doFilter(request, response);
	}
	
	

}
