<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="com.iiht.stockExchange.entity.StockExchange, java.util.*"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Stock Market Business Application</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!--  ------------------------------------------------------------------------------------- -->	
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
		<!--  ------------------------------------------------------------------------------------- -->	
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="stylesheet" href="/resources/demos/style.css">
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script>
		  $(function() {
		    $( "#datepicker" ).datepicker({
    			vibrate: true,
    			placement: "top",
    			align: "right"
		    }); // give your date field an id or a date css class
		  });
		</script>
		<!--  ------------------------------------------------------------------------------------- -->	
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/clockpicker/0.0.7/bootstrap-clockpicker.min.css">
		<script type="text/javascript" src ="https://cdnjs.cloudflare.com/ajax/libs/clockpicker/0.0.7/bootstrap-clockpicker.min.js"></script>
		<script>
		  $(function() {
    		$('.clockpicker').clockpicker({
    			'default': 'now',
    			vibrate: true,
    			placement: "top",
    			align: "right",
    			autoclose: true,
    			twelvehour: true
    		})
		  });
	    </script>
		<!--  ------------------------------------------------------------------------------------- -->
	</head>
	<body><br>
		<div class="container">
			<div style="height: 100px;">
				<div class="bg-danger h-50 d-inline-block text-white text-center form-rounded" style="width: 1120px;"><h2>Stock Market Exchange Business</h2></div>
		  		<div>&nbsp;</div>
		  		<div class="h-50 d-inline-block text-red text-center form-rounded" style="width: 1120px;"><h2>Assign Company Stock Prices</h2></div>
			    <!-- --------------------------------------------------------------------------------------------- -->
<%				List<StockExchange> stockExchanges = new ArrayList<StockExchange>(Arrays.asList(StockExchange.values()));	%>
		  		<form action="selectStockExchange" class="was-validated" method=get>
		    		<div class="form-group">
		    			<label for="title">Stock Market Exchange List : </label>
						<select name="stockExchange" id="stockExchange" onchange='this.form.submit()'>
						    <option value="">Stock Exchange</option>
						    <c:forEach items="<%=stockExchanges%>" var="value">
						       <option>${value}</option>
						    </c:forEach>
						</select>
					</div>
				</form>
				<!-- --------------------------------------------------------------------------------------------- -->
		  		<form action="selectCompanyName" class="was-validated" method=get>
		    		<div class="form-group">
		    			<label for="title">Name of the Company : </label>
						<select name="companyName" id="companyName" onchange='this.form.submit()'>
						    <option value="">Company Name</option>
						    <c:forEach items="${companyList}" var="value">
						       <option>${value.getCompanyName()}</option>
						    </c:forEach>
						</select>
		    		</div>
		    	</form>
				<!-- --------------------------------------------------------------------------------------------- -->
		  		<form action="commitStockPrices" class="was-validated" method=get>
		    		<div class="form-group">
		      			<label for="tags">Stock Id</label>
		      			<input type="text" class="form-control" id="stockId" placeholder="Enter Unique Stock Id" name="stockId" required>
		      			<div class="valid-feedback">Valid.</div>
		    		</div>
		    		<div class="form-group">
		      			<label for="tags">Current Stock Price</label>
		      			<input type="text" class="form-control" id="stockPrice" placeholder="Enter Company Stock Price" name="stockPrice" required>
		      			<div class="valid-feedback">Valid.</div>
		    		</div>
				    <!-- --------------------------------------------------------------------------------------------- -->
		    		<div class="form-group">
		      			<label for="tags">Stock Price @Date</label>
		      			<input type="text" class="form-control" id="datepicker" placeholder="Enter Date of Stock Price" name="stockDate" required>
		      			<div class="valid-feedback">Valid.</div>
				    </div>
				    <!-- --------------------------------------------------------------------------------------------- -->
 		    		<div class="form-group clockpicker">
						<label for="tags">Stock Price @Time</label>
		      			<input type="text" class="form-control" id="clockpicker" placeholder="Enter time of Stock Price" name="stockTime" required>
	
							<!-- <div class="input-group-append" data-target="#clockpicker" data-toggle="clockpicker">
                        		<div class="input-group-text"><i class="fa fa-clock-o"></i></div>
                    		</div> -->
		      			
		      			<div class="valid-feedback">Valid.</div>
		    		</div>
				    <!-- --------------------------------------------------------------------------------------------- -->
		    		<button type="submit" class="btn btn-primary">Commit Stock Price</button>
				    <!-- --------------------------------------------------------------------------------------------- -->
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