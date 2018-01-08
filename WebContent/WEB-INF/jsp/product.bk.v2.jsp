<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${product.get("productname").getAsString()}-Buy Products Online at RumboJ.com</title>
<script src="<c:url value="/resources/js/jquery-2.0.min.js" />"/></script>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
<script src="<c:url value="/resources/js/bootstrap.min.js" />"/></script>
</head>
<body>
  <div class="container">
  <div class="row" style='background-color: black; color:white'>
    <h3 align="center" >The ultimate Product Price Comparison available</h3>
  </div>
  <div class="row" style='background-color: black; color:white'>
     <div class="col-sm-2">
      <nav class="navbar navbar-inverse">
       <div class="container-fluid">
         <div class="navbar-header">
            <a class="navbar-brand" href="#">RumboJ.com</a>
         </div>  
       </div>
    </nav>
	 </div>
	 <div class="col-sm-8">
        <form class="navbar-form" role="search">
		    <div class="input-group">       
                <input type="text" class="form-control" name="x" placeholder="Search term..." style="width:400px">
				<div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="width:200px">
                    	<span id="search_concept">Categories</span> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="#contains">Mobile Phones</a></li>
                      <li><a href="#its_equal">Watches</a></li>
                      <li><a href="#greather_than">All</a></li>
                    </ul>
                </div>
                <span class="input-group-btn">
                    <button class="btn btn-default" type="submit" value="search">Search</button>
                </span>
            </div>
        </form>
    </div>
  </div>
  
  
  <div class="row">
	<nav class="navbar navbar-inverse">
       <div class="container">
         <ul class="nav navbar-nav">
            <li class="active"><a href="#">Mobile Phones</a></li>
            <li><a href="#">Watches</a></li>
            <li><a href="#">Fridges</a></li>
			<li><a href="#">Washing Machines</a></li>
         </ul>
       </div>
    </nav>
  </div>
   <div class="row">
     <div class="col-md-4">
	    <img src="<c:url value="/resources/img/Nexus.jpg"/>" alt="Nexus" class="img-rounded img-responsive" alt=${product.get("title")}/>
	  <h4>Rate this product</h4>
	  <h4>Overall Reviews</h4>
     </div>
     <div class="col-md-5">
	    <div class="row">
		  <h2 style="font-size: 22px!important;">${product.get("title").getAsString()}</h2>
		  <h5 style="color:#555!important">Best Price - ${product.get("price").getAsString()}</h5>
		  <h4 style="color:#C60">Technical Specifications</h4>
		</div>
	    <div class="row">
<table class="table table-bordered">
  <tbody>
    <tr>
      <td>Screen Size</td>
      <td>${product.get("screensize").getAsString()}</td>
    </tr>
    <tr>
      <td>Ram</td>
      <td>${product.get("RAM").getAsString()}</td>
    </tr>
    <tr>
      <td>Battery Capacity</td>
      <td>${product.get("battery").getAsString()}</td>
    </tr>
	<tr>
      <td>Processor Speed</td>
      <td>${product.get("processor").getAsString()}</td>
    </tr>
  </tbody>
</table>
		</div>
		<div class="row">
		  <div class="col-sm-4">
		  <button type="button" class="btn btn-primary">Buy from Flipkart</button>
		  <tr>
		    <td>Price</td>
			<td>${product.get("flipkartPrice").getAsString()}</td>
		  </tr>
		  <h5>Reviews</h5>
		  </div>
		  <div class="col-sm-4">
          <button type="button" class="btn btn-success">Buy from Amazon</button>
		  <tr>
		    <td>Price</td>
			<td>${product.get("amazonPrice").getAsString()}</td>
		  </tr>
		  <h5>Reviews</h5>
		  </div>
		  <div class="col-sm-4">
          <button type="button" class="btn btn-info">Buy from Snapdeal</button>
		  <h5>Price</h5>
		  <h5>Reviews</h5>
		  </div>
		</div>
     </div>	
     <div class="col-md-3">
       
	 </div>	 
	 </div>
  <div class="row-fluid" style='background-color: black; color:white'>
    <h3 align="center" style="font-size: 15px">Conditions of Use & SalePrivacy NoticeInterest-Based Ads© 1996-2017, Amazon.com, Inc. or its affiliates</h3>
  </div>
   </div>
  </div>
</body>
</html>