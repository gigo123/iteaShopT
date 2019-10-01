<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/BreadcrumbView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuView.jsp"%>
<div class="col-9 mb--50">
	<div class="ft-product-list">
		<c:forEach var="product" items="${requestScope.productList}">
			<div class="product-inner">
				<figure class="product-image">
					<a href="product-details.html"> <img
						src="./productImage/${product.id}.JPG" alt="Products">
					</a>
				</figure>
				<div class="product-info">
					<h3 class="product-title mb--25">
						<a href="product-details.html">${product.name}</a>
					</h3>
					<div class="product-price-wrapper mb--15 mb-sm--10">
						<span class="money">price:${product.price} $</span>
					</div>
					<p class="product-short-description mb--20">${product.description}</p>
					<div class="ft-product-action-list d-flex align-items-center">
						<form action="./cart" method="post">
							<input type="hidden" name="productToBuy" value="${product.id}" />
							<input type="submit" class="btn btn-size-md" value="add to cart" />
						</form>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<!--  close div of SideMenuView -->
</div>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/include/FooterView.jsp"%>