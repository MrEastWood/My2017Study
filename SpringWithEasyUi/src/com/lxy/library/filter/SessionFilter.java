package com.lxy.library.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class SessionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String[] ignore = new String[]{"login","register"};
		
		String uri = request.getRequestURI();
		System.out.println("$$$$$$$$$ uri : " + uri);
		
		
		for(String s : ignore){
			if(uri.indexOf(s) != -1){
				filterChain.doFilter(request, response);
				return;
			}
		}
		
		Object obj = request.getSession().getAttribute("loginUser");
		
		if(null == obj){
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}else{
			System.out.println(obj);
			filterChain.doFilter(request, response);
		}
		
	}

}
