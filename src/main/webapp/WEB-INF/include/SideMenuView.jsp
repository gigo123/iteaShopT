<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<div class="main-content-wrapper">
	<div class="page-content-inner ptb--80 ptb-md--60 pb-sm--55">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="user-dashboard-tab flex-column flex-md-row">
						<div class="user-dashboard-tab__head nav flex-md-column"
							role="tablist" aria-orientation="vertical">
							<c:if test="${login}">
								<div class="cart-side-menu">
									Hello ${userName} <br /> you have ${items} goods in cart
								</div>
							</c:if>

							<a class="nav-link active" 
								href="./product">Home</a>
							<a class="nav-link"  href="./product"
								aria-controls="orders" aria-selected="true">Shop</a> <a
								class="nav-link" data-toggle="pill" role="tab" href="./product?category=1"
								aria-controls="downloads" aria-selected="true">Iphone</a> 
								<a class="nav-link" data-toggle="pill" role="tab" href="./product?category=2"
								aria-controls="addresses" aria-selected="true">Ipad</a> 
								<a class="nav-link" data-toggle="pill" role="tab"
								href="./product?category=3" aria-controls="accountdetails"
								aria-selected="true">Watch</a> 
								<a class="nav-link" href="./cart">Cart</a>
								<a class="nav-link" href="./login">Login</a>
								<a class="nav-link" href="./register">Register</a>
						</div>
						<div class="user-dashboard-tab__content tab-content">
							<div class="tab-pane fade show active" id="dashboard">