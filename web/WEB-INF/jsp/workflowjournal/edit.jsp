<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<jsp:useBean id="workflowjournal" class="org.itstep.domain.Workflowjournal.Workflowjournal"/>
<jsp:useBean id="instruction" class="org.itstep.domain.Instruction.Instruction"/>
<jsp:useBean id="employeecard" class="org.itstep.domain.EmployeeCard.EmployeeCard"/>


<u:page2>

	<h1
		style="background: grey; color: yellow; text-align: left; padding: 5px; position: absolute; top: 230px">${title}</h1>
	<c:url var="saveUrl" value="/workflowjournal/save.html"/>
	<form action="${saveUrl}" method="post" style="text-align: left; position: absolute; top: 280px; left: 15px">
		
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

		<div>
			<label for="surname">Фамилия работника</label> <br>
			<p>
				<input type="text" id="surname" name="surname">
					
			</p>
			<br>
		</div>

		<div>
			<label for="briefingtype">Вид инструктажа:</label> <br>
			<p></p>

			<select id="briefingtype" name="briefingtype">
				<c:forEach var="briefingtype" items="${briefingtypes}" >
				<c:choose>
					<c:when test="${workflowjournal.id == briefingtype.id}">
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
			<br> <label for="serialnumber">Номер инструкции</label> <br>
			<p>
				<input type="text" id="serialnumber" name="serialnumber">
					
			</p>
			<br>
		</div>

		<button type="submit">Сохранить</button>
		<button type="reset">Очистить</button>

	</form>

</u:page2>