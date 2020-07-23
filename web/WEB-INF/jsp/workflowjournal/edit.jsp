<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ page import="org.itstep.domain.Instruction.Instruction"%>
<%@ page import="org.itstep.domain.EmployeeCard.EmployeeCard"%>
<%@ page import="org.itstep.domain.Workflowjournal.Workflowjournal"%>


<c:set var="title" value="Проведение инструктажа"/>
				


<u:page2>

	<h1
		style="background: grey; color: yellow; text-align: left; padding: 5px; position: absolute; top: 230px">${title} (Работник ${workflowjournal.employeecard.surname})</h1>
	<c:url var="saveUrl" value="/workflowjournal/save.html"/>
	<form action="${saveUrl}" method="post" style="text-align: left; position: absolute; top: 310px; left: 15px">
		
		<c:if test="${not empty workflowjournal.id}">
			<input type="hidden" name="id" value="${workflowjournal.id}">
			</c:if>
			
			

		<div>

			<label for="briefingdate">Дата проведения</label> <br>
			<p>
				<input  type="text" id="briefingdate" name="briefingdate" value="${workflowjournal.briefingdate}">
					
			</p>
			<br>
		</div>

		<input type="hidden" id="employeecard-id" name="employeecard-id" value="${workflowjournal.employeecard.id}">

		<div>
			<label for="briefingtype">Вид инструктажа:</label> <br>
			<p></p>

			<select id="briefingtype" name="briefingtype">
				<c:forEach var="briefingtype" items="${briefingtypes}" >
				<c:choose>
					<c:when test="${briefingtype.id == workflowjournal.briefingtype.id}">
						<c:set var="selected" value="selected" />
					</c:when>
					<c:otherwise>
						<c:remove var="selected" />
					</c:otherwise>
				</c:choose>
		
					<option value="${briefingtype.id}" ${selected}>${briefingtype.name}</option>
				</c:forEach>
			</select>

		</div>

		<div>
			 <label for="serialnumber">Номер инструкции</label> 
			<p></p>
				<select id="serialnumber" name="serialnumber">
				<c:forEach var="serialnumber" items="${serialnumbers}" >
				<c:choose>
					<c:when test="${instruction.serialnumber == workflowjournal.instruction.serialnumber}">
						<c:set var="selected" value="selected" />
					</c:when>
					<c:otherwise>
						<c:remove var="selected" />
					</c:otherwise>
				</c:choose>
		
					<option value="${instruction.serialnumber}" ${selected}>${instruction.serialnumber}</option>
				</c:forEach>
			</select>
							
		</div>

		<button type="submit">Сохранить</button>
		<button type="reset">Отменить</button>

	</form>

</u:page2>