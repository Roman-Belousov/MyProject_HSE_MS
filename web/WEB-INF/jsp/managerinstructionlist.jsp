
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ page import="org.itstep.domain.Instruction.Instruction"%>
<%@ page import="java.util.List"%>


<u:page2>

	<h1 style="color: red; text-align:left;position:absolute; top: 330px; margin: 10px">Инструкции</h1>
	
	

		<table border="1" style="background: grey; text-align:center; position:absolute; top: 400px; margin: 10px">
			<tr>
				<th></th>
				<th>Тип</th>
				<th>Название</th>
				<th>Номер</th>
				<th>Дата утверждения</th>
				<th>Дата окончания действия</th>
				<th></th>
			</tr>
			<c:forEach var="instruction" items="${instructions}">
				<tr>
					<td><input type="checkbox" name="id"
						value="${employeecard.id}"></td>
					<td>${instruction.instructionType.name}</td>
					<td>${instruction.name}</td>
					<td>${instruction.serialnumber}</td>
					<td>${instruction.dateofcreation}</td>
					<td>${instruction.expirationdate}</td>
					
					
					<c:url var="fileBaseUrl" value="/upload"/>
					<td><button><a href="${fileBaseUrl}/${instruction.filename}">Открыть</a></button></td>
				</tr>
			</c:forEach>
		</table>
		
		
		
	
</u:page2>

