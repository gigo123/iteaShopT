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
							<a class="nav-link active" data-toggle="pill" role="tab"
								href="#dashboard" aria-controls="dashboard" aria-selected="true">Dashboard</a>
							<a class="nav-link" data-toggle="pill" role="tab" href="#orders"
								aria-controls="orders" aria-selected="true">Orders</a> <a
								class="nav-link" data-toggle="pill" role="tab" href="#downloads"
								aria-controls="downloads" aria-selected="true">Downloads</a> <a
								class="nav-link" data-toggle="pill" role="tab" href="#addresses"
								aria-controls="addresses" aria-selected="true">Addresses</a> <a
								class="nav-link" data-toggle="pill" role="tab"
								href="#accountdetails" aria-controls="accountdetails"
								aria-selected="true">Account Details</a> <a class="nav-link"
								href="login-register.html">Logout</a>
						</div>
						<div class="user-dashboard-tab__content tab-content">
							<div class="tab-pane fade show active" id="dashboard">