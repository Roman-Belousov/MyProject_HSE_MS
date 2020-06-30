<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:page title="Вход в систему">
	
	<c:url var="loginUrl" value="/login.html"/>
	
	<form class="form-2" action="${loginUrl}" method="post">
	<c:if test="${not empty param.message}">
		<div class="message" style="position:absolute; top: 250px; left: 50px;color:red">${param.message}</div>
	</c:if>
	<c:url var="CssUrl" value="/lg3.css"></c:url>

	
			<h1>
				<span class="log-in">Войти</span> или <span class="sign-up">зарегистрироваться</span>
			</h1>
			<p class="float">
				<label for="login"><i class="icon-user"></i>Логин</label> <input
					type="text" name="login" placeholder="Логин или email">
			</p>
			<p class="float">
				<label for="password"><i class="icon-lock"></i>Пароль</label> <input
					type="password" name="password" placeholder="Пароль"
					class="showpassword">
			</p>
			<p class="clearfix">
				<button class="log-signin"  type="submit">Sign in</button>

				<a href="index3.html" class="log-signup" type="button">Sign up</a>
			</p>
		</form>
</u:page>
