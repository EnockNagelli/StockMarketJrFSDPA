<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html lang="en">
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
		  		<div class="h-50 d-inline-block text-red text-center form-rounded" style="width: 1120px;"><h2>Post Company Details</h2></div>
		  		<form action="saveCompany" class="was-validated" method=post>
		    		<div class="form-group">
		    			<label for="title">Stock Market Exchange List : </label>
						<select name="stockExchange" id="stockExchange">
						    <option value="">Stock Exchange</option>
						    <c:forEach items="${exchanges}" var="value">
						       <option>${value}</option>
						    </c:forEach>
						</select>
		    		</div>
		    		<div class="form-group">
		    			<label for="title">Company Code</label>
		    			<input type="text" class="form-control" id="companyCode" placeholder="Enter Company Code" name="companyCode" required>
		    			<div class="valid-feedback">Valid.</div>
		    		</div>
		    		<div class="form-group">
		    			<label for="title">Name of the Company</label>
		    			<input type="text" class="form-control" id="company" placeholder="Enter Company Name" name="companyName" required>
		    			<div class="valid-feedback">Valid.</div>
		    		</div>
		    		<div class="form-group">
		      			<label for="tags">Name of the CEO</label>
		      			<input type="text" class="form-control" id="ceoName" placeholder="Enter CEO Name" name="ceoName" required>
		      			<div class="valid-feedback">Valid.</div>
		    		</div>
		    		<div class="form-group">
		      			<label for="tags">Company Turnover</label>
		      			<input type="text" class="form-control" id="turnover" placeholder="Enter Company Turnover" name="turnover" required>
		      			<div class="valid-feedback">Valid.</div>
				    </div>
		    		<div class="form-group">
						<label for="tags">Board Of Directors</label>
		      			<input type="text" class="form-control" id="directors" placeholder="Enter Board of Directors" name="directors" required>
		      			<div class="valid-feedback">Valid.</div>
		    		</div>
		    		<div class="form-group">
		      			<label for="posts">Company Profile</label>
		      			<textarea class="form-control" rows="7" id="profile" placeholder="Describe Company Profile" name="profile" required></textarea>
		      			<div class="valid-feedback">Valid.</div>
		    		</div>
		    		<button type="submit" class="btn btn-primary">Post Company</button>
		  		</form><br>
				<div class="bg-primary h-50 text-white text-center form-rounded">@ Copy right : www.iiht.com</div>
		  	</div>
		</div>
		<script>
			// Disable form submissions if there are invalid fields
			(function() {
			  	'use strict';
				window.addEventListener('load', function() {
		   			// Get the forms we want to add validation styles to
		   			var forms = document.getElementsByClassName('needs-validation');
		   			// Loop over them and prevent submission
		   			var validation = Array.prototype.filter.call(forms, function(form) {
		   				form.addEventListener('submit', function(event) {
		        			if (form.checkValidity() === false) {
		          				event.preventDefault();
		          				event.stopPropagation();
			      			}
				       		form.classList.add('was-validated');
			   			}, false);
			    	});
			  	}, false);
			})();
		</script>
	</body>
</html>




























<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<!-- <f:view> </f:view> -->
		<h2>Inside Add Company Page...</h2>
	 </body>
</html> --%>