<!-- Insertion Header -->
<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ include file="header.jsp"  %>

	<% 
		if (request.getAttribute("deconnect") == "true") {
			session.removeAttribute("user");
		}else {
			if(session.getAttribute("user") != null) {
				response.sendRedirect("Application?action=home");
			} else {
				%>
				<!-- Texte de bienvenue -->
				<h2> Bienvenue !</h2>
				
				<a href="Application?action=sign_up">S'inscrire</a>
				
				<a href="Application?action=sign_in">Se connecter</a>
				<%
			}
		}
	%>
<!-- Insertion Footer -->
<%@ include file="footer.jsp"  %>