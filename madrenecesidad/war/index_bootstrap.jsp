<%@ page contentType="text/html;charset=UTF-8" language="java"%><%--
--%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%--
--%><%@page import="org.sgx.madrenecesidad.client.MNConstants"%><%--
--%><%@ page import="java.util.List"%><%--
--%><%@ page import="com.google.appengine.api.users.User"%><%--
--%><%@ page import="com.google.appengine.api.users.UserService"%><%--
--%><%@ page import="com.google.appengine.api.users.UserServiceFactory"%><%--
--%><%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory"%><%--
--%><%@ page import="com.google.appengine.api.datastore.DatastoreService"%><%--
--%><%@ page import="com.google.appengine.api.datastore.Query"%><%--
--%><%@ page import="com.google.appengine.api.datastore.Entity"%><%--
--%><%@ page import="com.google.appengine.api.datastore.FetchOptions"%><%--
--%><%@ page import="com.google.appengine.api.datastore.Key"%><%--
--%><%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>MadreNecesidad - publique y encuentre servicios en un
	mapa</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Sebastián Gurin">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->
<!-- Favicons
	================================================== -->
<link rel="shortcut icon" href="images/logo2.ico">
<link rel="apple-touch-icon" href="images/apple-touch-icon.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="images/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="images/apple-touch-icon-114x114.png">
	
	 <script src="http://maps.google.com/maps/api/js?libraries=places&amp;sensor=false" ></script>
	 
	<script type="text/javascript"  src="js/jquery.js"></script>
	
	<script type="text/javascript"  src="js/bootstrap.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">

	<script type="text/javascript" 
		src="appenginetest1/appenginetest1.nocache.js"></script>
</head>

<body>
	<%
		String guestbookName = request.getParameter("guestbookName");
		if (guestbookName == null) {
			guestbookName = "default";
		}
		pageContext.setAttribute("guestbookName", guestbookName);
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if (user != null) {
	%>

	<script type="text/javascript">
	window._webuser = {
		nickname: "<%=user.getNickname()%>", 
		email: "<%=user.getEmail()%>",
		federatedIdentity: "<%=user.getFederatedIdentity()%>",
		userId: "<%=user.getUserId()%>",
		authDomain: "<%=user.getAuthDomain()%>",
		loginUrl : "<%=userService.createLoginURL(request.getRequestURI())%>",
		logoutUrl : "<%=userService.createLogoutURL(request.getRequestURI())%>"
	}; 
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
	window._webuser = {
		loginUrl : "<%=userService.createLoginURL(request.getRequestURI())%>"
	}; 
	</script>
	<%
		}
	%>

	 
</body>
</html>
