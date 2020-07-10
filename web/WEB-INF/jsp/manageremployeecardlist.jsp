
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<%@ page import="org.itstep.domain.EmployeeCard.EmployeeCard"%>
<%@ page import="java.util.List"%>


<u:page2>
<h1 style="color: red; text-align:left;position:absolute; top: 330px; margin: 10px">Worker personal card</h1>
	
	<c:url var="deleteUrl" value="/employeecard/delete.html" />
	<form action="${deleteUrl}" method="post">

		<table border="1" style="background: grey; text-align:center; position:absolute; top: 400px; margin: 10px">
			<tr>
				<th></th>
				<th>Табельный номер</th>
				<th>Имя</th>
				<th>Фамилия</th>
				<th>Подразделение</th>
				<th>Профессия</th>
				<th>Вид инструктажа</th>
				<th>Дата инструктажа</th>
				<th></th>
			</tr>
			<c:forEach var="employeecard" items="${employeecards}">
				<tr>
					<td><input type="checkbox" name="id"
						value="${employeecard.id}"></td>
					<td>${employeecard.personnelnumber}</td>
					<td>${employeecard.name}</td>
					<td>${employeecard.surname}</td>
					<td>${employeecard.workarea}</td>
					<td>${employeecard.position}</td>
					<td>${employeecard.briefingtype}</td>
					<td>${employeecard.dateofbriefing}</td>
					
					
					
					<c:url var="editUrl" value="/employeecard/edit.html">
						<c:param name="id" value="${employeecard.id}" />
					</c:url>
					<td><button><a href="${editUrl}">Редактировать</a></button></td>
				</tr>
			</c:forEach>
		</table>
		
		
		<p style="text-align:center; position:absolute; top: 560px; margin: 10px">
			<button type="submit">Удалить</button>
		</p>		
	</form>
	
		
		
	
</u:page2>

