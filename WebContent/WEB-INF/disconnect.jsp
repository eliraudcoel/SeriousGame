<% 
	if (request.getAttribute("disconnect") == "true") {
		session.removeAttribute("user");
		response.sendRedirect("Application?action=index");
	} else {
		response.sendRedirect("Application?action=index");
	}
%>