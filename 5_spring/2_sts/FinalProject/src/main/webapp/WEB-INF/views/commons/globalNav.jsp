<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <%-- include 대상이 되는 조각화하는 jsp는  html ... body .. 다 지워야 된다.--%>
 
 	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">Title</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="#">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="../board/mainPage.do">자유게시판</a>
	        </li>
	        <c:choose>
	        	<c:when test="${!empty sessionUser }">
			        <li class="nav-item dropdown">
			          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			            ${sessionUser.member_nick }
			          </a>
			          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
			          
			            <li><a class="dropdown-item" href="../member/readMyPage.do">마이 페이지</a></li>
			            
			            <li><a class="dropdown-item" href="#">.....</a></li>
			            <li><hr class="dropdown-divider"></li>
			            <li><a class="dropdown-item" href="../member/logoutProcess.do">로그 아웃</a></li>
			          </ul>
			        </li>	        	
	        	</c:when>
	        	<c:otherwise>
			        <li class="nav-item">
			          <a class="nav-link" href="../member/loginPage.do">로그인</a>
			        </li>	        	
	        	</c:otherwise>
	        </c:choose>
	        
	        <li class="nav-item">
	          <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">...</a>
	        </li>
	      </ul>
	      <form class="d-flex">
	        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
	        <button class="btn btn-outline-success" type="submit">Search</button>
	      </form>
	    </div>
	  </div>
	</nav>