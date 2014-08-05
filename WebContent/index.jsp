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
				<%
			}
		}
	%>
	
	<% if(request.getAttribute("message") != null) { %>
	<div class="row alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		<strong>Attention! </strong><%= request.getAttribute("message") %>
	</div>
	<% } %>
	
	<div class="row">
		<div class="sign_in col-xs-6">
			<%@ include file="sign_in.jsp"  %>
		</div>
		<div class="sign_in col-xs-6">
			<%@ include file="sign_up.jsp"  %>
		</div>
	</div>
<!-- Insertion Footer -->
<%@ include file="footer.jsp"  %>