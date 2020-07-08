
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ page import="org.itstep.domain.Instruction.Instruction"%>
<%@ page import="org.itstep.domain.EmployeeCard.EmployeeCard"%>
<%@ page import="org.itstep.domain.Workflowjournal.Workflowjournal"%>
<%@ page import="java.util.List"%>


<u:page2>

	<h1 style="color: red; text-align:left;position:absolute; top: 330px; margin: 10px">Журнал инструктажа</h1>
	
	

		<table border="1" style="background: grey; text-align:center; position:absolute; top: 400px; margin: 10px">
			<tr>
				<th></th>
				<th>Вид инструктажа</th>
				<th>Название инструкции</th>
				<th>Сотрудник</th>
				<th>Дата проведения</th>
				<th></th>
			</tr>
			
			<c:forEach var="workflowjournal" items="${workflowjournals}">
			
				<tr>
					<td><input type="checkbox" name="id"
						value="${workflowjournal.id}"></td>
					<td>${workflowjournal.briefingtype.name}</td>					
					<td>${workflowjournal.instruction.name}</td>
					<td>${workflowjournal.employeecard.surname}</td>
					<td>${workflowjournal.briefingdate}</td>
					
					
										
					<td><button><a href="#">Редактировать</a></button></td>
				</tr>
			</c:forEach>
		</table>
		
		
		
	
</u:page2>

