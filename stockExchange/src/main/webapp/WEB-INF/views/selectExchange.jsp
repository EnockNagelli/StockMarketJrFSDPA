<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="com.iiht.stockExchange.entity.StockExchange, java.util.*"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Stock Market Business Application</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	</head>
	<body><br>
		<div class="container">
			<div style="height: 100px;">
				<div class="bg-danger h-50 d-inline-block text-white text-center form-rounded" style="width: 1120px;"><h2>Stock Market Exchange Business</h2></div>
		  		<div>&nbsp;</div>
		  		<p class="bg-primary text-danger text-center"><h1>Registered Company Information</h1><br>
<%				List<StockExchange> stockExchanges = new ArrayList<StockExchange>(Arrays.asList(StockExchange.values()));	%>
		  		<form action="selectExchange" class="was-validated" method=get>
			 		<div class="form-group">
			   			<label for="title">Stock Market Exchange List : </label>
						<select name="stockExchange" id="stockExchange" onchange='this.form.submit()'>
						    <option value="">Stock Exchange</option>
						    <c:forEach items="<%=stockExchanges%>" var="value">
						       <option>${value}</option>
						    </c:forEach>
						</select>
						<!-- <noscript><input type="submit" value="Submit"></noscript> -->
			   		</div><br>
				<div class="container-fluid">
				 	<div class="row-fluid">
				  		<div class="span12">
				    		<fieldset>
								<table class="table table-hover table-striped-rows table-bordered table-condensed">
								    <tr class="text-danger">
								        <th>S.No</th>
								        <th>Company Code</th>
								        <th>Company Name</th>
								        <th>Company CEO</th>
								        <th>Company Turnover</th>
								        <th>Company Profile</th>
								    </tr>
						           	<c:forEach var="listValue" varStatus="loop" items="${clist}">
							            <tr>
							                <td>
							                    <c:out value="${loop.index + 1}" /> 
							                </td>
							                <td>
							                    <c:out value="${listValue.companyCode}" /> 
							                </td>
							                <td>
							                    <c:out value="${listValue.companyName}" /> 
							                </td>
							                <td>
							                    <c:out value="${listValue.companyCEO}" /> 
							                </td>
							                <td>
							                    <c:out value="${listValue.turnover}" /> 
							                </td>
							                <td>
							                    <c:out value="${listValue.companyProfile}" /> 
							                </td>
							                <td>
							                    <!-- <a href="addComments">Post Comment</a> -->
				
							                    <%-- <a href="addComments?id=${listValue.postId}" style='text-decoration: none;'>Comment</a> | <a href="updateLike" style='text-decoration: none;'>Like</a> --%>
							                    
								  				<button class="btn btn-info" onclick="location.href='deleteCompany/id=${listValue.companyCode}'">Delete</button>
				
							                    <%-- <a href="addComments?id=${listValue.postId}"><img src="<c:url value="/resources/comment.jpg" />" alt="Post Comment"/></a> --%>
							                </td>
							            </tr>
						           	</c:forEach>
						        </table>
				    		</fieldset>
			   			</div>
			  		</div>
	 			</div>			   		
		   		</form>
   			</div>
   		</div>
	</body>
</html>