<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/BreadcrumbView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuView.jsp"%>
<h1>Product page</h1>
<div>
	<c:forEach var="product" items="${requestScope.productList}">
		<table>
			<tr>
				<td width="200">${product.name}</td>
				<td width="400"></td>
			</tr>
			<tr>
				<td><img src="./productImage/${product.id}.png" height="200"></td>
				<td>${product.description}</td>
			</tr>
			<tr>
				<td>price:${product.price}</td>
				<td>
				<form action = "./cart" method = "post"><input type = "hidden" name ="productToBuy" value ="${product.id}" /> 
				<input type="submit" value="buy" />
				
			</form></td></tr>
		</table>
		<br>
		<br>
	</c:forEach>
</div>
<!--  close div of SideMenuView -->
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/include/FooterView.jsp"%>