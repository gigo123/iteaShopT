<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<ul class="mainmenu">
	<li class="mainmenu__item menu-item-has-children"><a
		href="index.html" class="mainmenu__link"> <span class="mm-text">Home</span>
	</a>
		<ul class="sub-menu">
			<li><a href="index.html"> <span class="mm-text">Home
						One</span>
			</a></li>
			<li><a href="index-02.html"> <span class="mm-text">Home
						Two</span>
			</a></li>
		</ul></li>
	<li class="mainmenu__item menu-item-has-children megamenu-holder">
		<a href="shop.html" class="mainmenu__link"> <span class="mm-text">Shop</span>
	</a>
		<ul class="megamenu">
			<li><a class="megamenu-title" href="#"> <span
					class="mm-text">Shop Grid</span>
			</a>
				<ul>
					<li><a href="shop-fullwidth.html"> <span class="mm-text">Full
								Width</span>
					</a></li>
					<li><a href="shop.html"> <span class="mm-text">Left
								Sidebar</span>
					</a></li>
					<li><a href="shop-right-sidebar.html"> <span
							class="mm-text">Right Sidebar</span>
					</a></li>
					<li><a href="shop-three-columns.html"> <span
							class="mm-text">Three Columns</span>
					</a></li>
					<li><a href="shop-four-columns.html"> <span
							class="mm-text">Four Columns</span>
					</a></li>
				</ul></li>
			<li><a class="megamenu-title" href="#"> <span
					class="mm-text">Shop List</span>
			</a>
				<ul>
					<li><a href="shop-list.html"> <span class="mm-text">Full
								Width</span>
					</a></li>
					<li><a href="shop-list-sidebar.html"> <span
							class="mm-text">Left Sidebar</span>
					</a></li>
					<li><a href="shop-list-right-sidebar.html"> <span
							class="mm-text">Right Sidebar</span>
					</a></li>
				</ul></li>
			<li><a class="megamenu-title" href="#"> <span
					class="mm-text">Product Details</span>
			</a>
				<ul>
					<li><a href="product-details.html"> <span class="mm-text">Tab
								Style 1</span>
					</a></li>
					<li><a href="product-details-tab-style-2.html"> <span
							class="mm-text">Tab Style 2</span>
					</a></li>
					<li><a href="product-details-tab-style-3.html"> <span
							class="mm-text">Tab Style 3</span>
					</a></li>
					<li><a href="product-details-gallery-left.html"> <span
							class="mm-text">Gallery Left</span>
					</a></li>
					<li><a href="product-details-gallery-right.html"> <span
							class="mm-text">Gallery Right</span>
					</a></li>
					<li><a href="product-details-sticky-left.html"> <span
							class="mm-text">Sticky Left</span>
					</a></li>
				</ul></li>
			<li><a class="megamenu-title" href="#"> <span
					class="mm-text">Product Details</span>
			</a>
				<ul>
					<li><a href="product-details-sticky-right.html"> <span
							class="mm-text">Sticky Right</span>
					</a></li>
					<li><a href="product-details-slider-box.html"> <span
							class="mm-text">Slider Box</span>
					</a></li>
					<li><a href="product-details-slider-full-width.html"> <span
							class="mm-text">Slider Box Full Width</span>
					</a></li>
					<li><a href="product-details-affiliate.html"> <span
							class="mm-text">Affiliate Proudct</span>
					</a></li>
					<li><a href="product-details-variable.html"> <span
							class="mm-text">Variable Proudct</span>
					</a></li>
					<li><a href="product-details-group.html"> <span
							class="mm-text">Group Product</span>
					</a></li>
				</ul></li>
		</ul>
	</li>


	<!--my menu  -->
	<c:choose>
		<c:when test="${login}">
			<li class="mainmenu__item  megamenu-holder"><a href="./logout"
				class="mainmenu__link"> <span class="mm-text">logout</span>
			</a></li>

			<li class="mainmenu__item  megamenu-holder"><a href="./register"
				class="mainmenu__link"> <span class="mm-text"
					${ page!=null?page.equals("register")?"style='color: red; '": "":""}>Account</span>
			</a></li>
		</c:when>
		<c:otherwise>
			<li class="mainmenu__item  megamenu-holder"><a href="./login"
				class="mainmenu__link"> <span class="mm-text"
					${ page!=null?page.equals("login")?"style='color: red; '": "":""}>Login</span>
			</a></li>

			<li class="mainmenu__item  megamenu-holder"><a href="./register"
				class="mainmenu__link"> <span class="mm-text"
					${ page!=null?page.equals("register")?"style='color: red; '": "":""}>Register</span>
			</a></li>
		</c:otherwise>
	</c:choose>


	<!--my menu  -->
	<li class="mainmenu__item"><a href="contact-us.html"
		class="mainmenu__link"> <span class="mm-text">Contact Us</span>
	</a></li>
</ul>