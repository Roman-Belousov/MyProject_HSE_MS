<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
    <c:choose>
	<c:when test="${not empty employeecard}">
		<c:set var="title" value="Редактирование записи таб № &laquo;${employeecard.personnelnumber}&raquo;"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Добавление новой записи"/>
		<jsp:useBean id="employeecard" class="org.itstep.domain.EmployeeCard.EmployeeCard"/>
	</c:otherwise>
</c:choose>
    

<u:page1>

<h1 style="background: grey;color: yellow ;text-align:left;padding: 5px;position:absolute; top: 230px">${title}</h1>
<c:url var="saveUrl" value="/employeecard/save.html"/>
		<form action="${saveUrl}" method="post" style="text-align:left;position:absolute; top: 280px; left :15px">
		<c:if test="${not empty employeecard.id}">
			<input type="hidden" name="id" value="${employeecard.id}">
			</c:if>
			<br>
			<label for="personnelnumber">Табельный номер:</label>
			<br>
			<input type="text" pattern="^[ 0-9]+$" id="personnelnumber" name="personnelnumber" 
			value="${employeecard.personnelnumber}" required >
			<br>
			<br>
			<label for="name">Имя</label>
			<br>
			<input  pattern="^[А-Яа-яЁё\s]+$" type="text" id="name" name="name" value="${employeecard.name}">
			<br>
			<br>
			<label for="surname">Фамилия</label>
			<br>
			<input type="text" id="surname" name="surname" value="${employeecard.surname}">
			<br>
			<br>
			<label for="workarea">Подразделение</label>
			<br>
			<input type="text" id="workarea" name="workarea" value="${employeecard.workarea}">
			<br>
			<br>
			<label for="position">Профессия</label>
			<br>
			<input type="text" id="position" name="position" value="${employeecard.position}">
			<br>
			<br>
			<button type="submit">Сохранить</button>
		</form>

</u:page1>