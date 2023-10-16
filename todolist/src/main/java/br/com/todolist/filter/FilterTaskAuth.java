package br.com.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.todolist.repository.UserRespository;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

	@Autowired
	private UserRespository userRespository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		var servletPath = request.getServletPath();

		if (servletPath.startsWith("/tasks/")) {

			// Pegar a autenticação (usuario e senha)
			var authorization = request.getHeader("Authorization");

			var authEncoded = authorization.substring("Basic".length()).trim();

			byte[] authDecode = Base64.getDecoder().decode(authEncoded);

			var authString = new String(authDecode);

			String[] credentials = authString.split(":");
			String username = credentials[0];
			String password = credentials[1];

			System.out.println(username);
			System.out.println(password);

			// Validar usuario
			var user = this.userRespository.findByUsername(username);
			if (user == null) {
				response.sendError(401, "Usuario sem autorização");
			} else {
				//validar senha
				var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
				if (passwordVerify.verified) {
					request.setAttribute("idUser", user.getId());
					filterChain.doFilter(request, response);
				} else {
					response.sendError(401);
				}

			}
		} else {
			filterChain.doFilter(request, response);
		}
	}

}
