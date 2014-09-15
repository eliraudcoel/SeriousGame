<%@page import="Modeles.Utilisateur"%>
<%@ include file="header_global.jsp"  %>

<%
	Utilisateur user;
	if(request.getAttribute("user") != null) {
		user = (Utilisateur) request.getAttribute("user"); 
		session.setAttribute("user", user);
	} else {
		user = (Utilisateur) session.getAttribute("user"); 
	}
%>

<h2>Vous êtes connecté <%= user.getLogin() %>!</h2>

<!-- Insertion Footer -->
<%@ include file="footer.jsp"  %>