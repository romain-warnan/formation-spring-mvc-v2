package fr.insee.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.keycloak.KeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import fr.insee.springmvc.model.Utilisateur;

@Component
public class UtilisateurInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(UtilisateurInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		var session = request.getSession(true);
		var utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		if(utilisateur == null) {
			var securityContext = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
			if(securityContext != null) {
				var token = securityContext.getToken();
				utilisateur = Utilisateur.fromToken(token);
				session.setAttribute("utilisateur", utilisateur);
				logger.info("Connexion de l’utilisateur : " + utilisateur);
			}
		}
		return true;
	}
}
