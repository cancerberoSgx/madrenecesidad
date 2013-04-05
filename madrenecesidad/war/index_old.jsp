<%@page import="org.sgx.madrenecesidad.client.MNConstants"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>MadreNecesidad - publique y encuentre servicios.-</title>
    <script type="text/javascript" language="javascript" src="appenginetest1/appenginetest1.nocache.js"></script>
    <link href="main.css" rel="stylesheet"></link>
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
		nickname: "<%=user.getNickname() %>", 
		email: "<%=user.getEmail() %>",
		federatedIdentity: "<%=user.getFederatedIdentity() %>",
		userId: "<%=user.getUserId() %>",
		authDomain: "<%=user.getAuthDomain() %>",
		loginUrl : "<%= userService.createLoginURL(request.getRequestURI()) %>",
		logoutUrl : "<%= userService.createLogoutURL(request.getRequestURI()) %>"
	}; 
	</script>

<%
    } else {

%>
	<script type="text/javascript">
	window._webuser = {
		loginUrl : "<%= userService.createLoginURL(request.getRequestURI()) %>"
	}; 
	</script>

<%
    }
%>
  </body>
</html>
