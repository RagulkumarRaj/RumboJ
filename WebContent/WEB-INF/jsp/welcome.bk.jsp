<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RumboJ - Best Price Comparison and Online Shopping Site</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
</head>
<body bgcolor="#323335">
	<h1 align="center">
		<font size="25">RumboJ.com</font>
	</h1>
	<span align="center">
		<form id="searchbox" action="search"  method="get">
			<input id="search" type="text" placeholder="Search here" name="searchstring" > 
			<input id="submit" type="submit" value="Search">
		</form>
		<h5>Search from over 10000 products</h5>
	</span>
</body>
</html>