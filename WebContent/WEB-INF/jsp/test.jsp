<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${model.title}-Buy Products Online at RumboJ.com</title>
</head>
<body>
<h3 align="right">YOYO</h3>
<h3 align=${product.get("align")}>Hiii</h3>
		<ul>
			<li>${product.get("price")}</li>
			<li>Processor Speed: ${product.get("Proc")}</li>
		</ul>
</body>
</html>