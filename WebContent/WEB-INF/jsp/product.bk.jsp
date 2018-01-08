<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${productname}-Buy Products Online at RumboJ.com</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
	<div class="container"></div>
	<div class="col-md-4">
		<h3>${productname}</h3>
		<img alt="image" src="<c:url value="/resources/img/index.png" />"">
	</div>
	<div class="col-md-4">
		<span>
			<h5>Flipkart</h5>
			<h5>Buy Now</h5>
			<h5>20000</h5>
		</span>
	</div>
	<div class="col-md-4">
		<span>
			<h5>Amazon</h5>
			<h5>Buy Now</h5>
			<h5>25000</h5>
		</span>
	</div>
	<div class="col-md-12">
		<ul>
			<li>Screen Size: 5.0 inch</li>
			<li>Ram: 2 GB</li>
			<li>Battery Capacity: 2750 mAh</li>
			<li>Processor Speed: 1.4 Ghz</li>
		</ul>
	</div>
</body>
</html>