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
    			twelvehour: false
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
		  		<div class="h-50 d-inline-block text-red text-center form-rounded" style="width: 1120px;"><h2>Stock Details of the Company</h2></div>
			    <!-- --------------------------------------------------------------------------------------------- -->
<%				List<StockExchange> stockExchanges = new ArrayList<StockExchange>(Arrays.asList(StockExchange.values()));	%>
		  		<form action="selectStockExchange2" class="was-validated" method=get>
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
	  			<form action="selectCompanyName2" class="was-validated" method=get>
		    		<div class="form-group">
		    			<label for="title">Name of the Company : </label>
						<select name="companyName" id="companyName" onchange='this.form.submit()'>
						    <option value="">Company Name</option>
						    <c:forEach items="${companyList}" var="value">
						       <option>${value.getCompanyName()}</option>
						    </c:forEach>
						</select>
		    		</div><br>
				<!-- --------------------------------------------------------------------------------------------- -->
					<div class="container-fluid">
					 	<div class="row-fluid">
					  		<div class="span12">
					    		<fieldset>
									<table class="table table-hover table-striped-rows table-bordered table-condensed">
									    <tr class="text-danger text-center">
									        <th>Company Code</th>
									    	<th>Company CEO</th>
									        <th>Company Turnover</th>
									    </tr>
							            <tr class="text-center">
							                <td>${comCode}</td>
							                <td>${comCEO}</td>
							                <td>${comTurnover}</td>
										</tr>
							        </table>
					    		</fieldset>
				   			</div>
				  		</div>
		 			</div><br>
					<!-- --------------------------------------------------------------------------------------------- -->
					<div class="container-fluid">
					 	<div class="row-fluid">
					  		<div class="span12">
					    		<fieldset>
									<table class="table table-hover table-striped-rows table-bordered table-condensed">
									    <tr class="text-danger text-center">
									        <th>S.No</th>
									        <th>Stock Id</th>
									        <th>Stock Price</th>
									        <th>Stock Price Date</th>
									        <th>Stock Price Time</th>
									    </tr>
							           	<c:forEach var="spIndex" varStatus="loop" items="${spList}">
								            <tr class="text-center">
								                <td>
								                    <c:out value="${loop.index + 1}" /> 
								                </td>
								                <td>
								                    <c:out value="${spIndex.stockIndex}" /> 
								                </td>
								                <td>
								                    <c:out value="${spIndex.currentStockPrice}" /> 
								                </td>
								                <td>
								                    <c:out value="${spIndex.stockPriceDate}" /> 
								                </td>
								                <td>
								                    <c:out value="${spIndex.stockPriceTime}" /> 
								                </td>
								                <td>
									  				<button class="btn btn-info" onclick="location.href='deleteCompany/id=${spIndex.stockIndex}'">Delete</button>
								                </td>
								            </tr>
							           	</c:forEach>
							        </table>
					    		</fieldset>
				   			</div>
				  		</div>
	 				</div>	
		    	</form>
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



<%-- 							                <td><%=session.getAttribute("comCode")%>   ${comCode}</td>
							                <td><%=session.getAttribute("comCEO")%>		${comCEO}</td>
							                <td><%=session.getAttribute("comTurnover")%> ${comTurnover}</td>
 --%>