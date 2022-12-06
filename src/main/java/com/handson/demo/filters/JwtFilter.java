package com.handson.demo.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.handson.demo.service.EmployeeDetailsService;
import com.handson.demo.util.JWTUtility;


@Component
public class JwtFilter extends OncePerRequestFilter {

	
	@Autowired
	JWTUtility jWTUtility;
	
	@Autowired
	EmployeeDetailsService employeeDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String auth = request.getHeader("Authorization");
		String token=null;
		String username=null;
		
		if(null!=auth && auth.startsWith("Bearer ")) {
			token=auth.substring(7);
			System.out.print("token is "+ token);
			username=jWTUtility.getUsernameFromToken(token);
			System.out.println("user is "+ username);
			
		}
		if(null!=username && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = employeeDetailsService.loadUserByUsername(username);
			  if(jWTUtility.validateToken(token, userDetails)) {
				  UsernamePasswordAuthenticationToken upt=new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				  upt.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				  SecurityContextHolder.getContext().setAuthentication(upt);
			  }
			 
		}
		 filterChain.doFilter(request, response);
	}

}
