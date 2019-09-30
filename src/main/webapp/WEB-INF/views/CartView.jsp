<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@ include file="/WEB-INF/include/HeaderView.jsp"%>
<%@ include file="/WEB-INF/include/BreadcrumbView.jsp"%>
<%@ include file="/WEB-INF/include/SideMenuView.jsp"%>
<!-- Main Content Wrapper Start -->
<div class="col-9">
	<form class="cart-form" action="./cart" method="post">
		<div class="table-content table-responsive">
			<table class="table text-center">
				<thead>
					<tr>
						<th>&nbsp;</th>
						<th class="text-left">Product</th>
						<th>price</th>
						<th>quantity</th>
						<th>total</th>
						<th>remove</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${requestScope.productList}">
						<tr>
							<td class="product-thumbnail text-left"><img
								src="./productImage/${product.id}.JPG" alt="Product Thumnail"></td>
							<td class="product-name text-left wide-column">
								<h3>
									<a href="product-details.html">${product.name}</a>
								</h3>
							</td>
							<td class="product-price"><span
								class="product-price-wrapper"> <span class="money">${product.price}</span>
							</span></td>
							<td class="product-quantity">
								<div class="quantity">
									<input type="number" class="quantity-input" name="qty"
										id="qty-${product.id}" value="1" min="1">
								</div>
							</td>
							<td class="product-total-price"><span
								class="product-price-wrapper"> <span class="money">$</span>
							</span></td>
							<td class="product-remove text-left"><input type="hidden"
								name="productToRemove" value="${product.id}" /> <input
								type="submit" value="remove"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row no-gutters border-top pt--20 mt--20">
			<div class="col-sm-6 text-sm-right">
				<button type="submit" class="cart-form__btn">Clear Cart</button>
			</div>
		</div>
	</form>
	<div class="cart-collaterals">
		<div class="cart-totals">
			<h5 class="font-size-14 font-bold mb--15">Cart totals</h5>
			<div class="cart-calculator__item order-total">
				<div class="cart-calculator__item--head">
					<span>Total</span>
				</div>
				<div class="cart-calculator__item--value">
					<span class="product-price-wrapper"> <span class="money">$226.00</span>
					</span>
				</div>
			</div>
		</div>
	</div>
	<a href="checkout.html"
		class="btn btn-size-md btn-shape-square btn-fullwidth"> Proceed To Checkout </a>
</div>
<!--  close div of SideMenuView -->
</div>
</div>
</div>
</div>
</div>
<!-- Main Content Wrapper Start -->

<%@ include file="/WEB-INF/include/FooterView.jsp"%>