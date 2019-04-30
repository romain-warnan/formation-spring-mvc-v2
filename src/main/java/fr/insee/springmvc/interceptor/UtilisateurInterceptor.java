package fr.insee.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import fr.insee.springmvc.model.Utilisateur;

@Component
public class UtilisateurInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		var session = request.getSession(true);
		var utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		if(utilisateur == null) {
			var securityContext = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
			utilisateur = securityContext != null ? Utilisateur.fromToken(securityContext.getToken()) : Utilisateur.defaultUser();
			session.setAttribute("utilisateur", utilisateur);
		}
		return true;
	}
}
