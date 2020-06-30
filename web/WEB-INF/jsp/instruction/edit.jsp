<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
    <c:choose>
	<c:when test="${not empty instruction}">
		<c:set var="title" value="Редактирование инструкции № &laquo;${instruction.serialnumber}&raquo;"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Добавление новой инструкции"/>
		<jsp:useBean id="instruction" class="org.itstep.domain.Instruction.Instruction"/>
	</c:otherwise>
</c:choose>
    

<u:page1>

<h1 style="background: grey;color: yellow ;text-align:left;padding: 5px;position:absolute; top: 230px">${title}</h1>
<c:url var="saveUrl" value="/instruction/save.html"/>
		<form action="${saveUrl}" method="post" style="text-align:left;position:absolute; top: 280px; left :15px">
		<c:if test="${not empty instruction.id}">
			<input type="hidden" name="id" value="${instruction.id}">
			</c:if>
			
			<br>
			<label  for="instructiontype">Тип инструкции:</label>
			
			<br>
		<select  id="instructiontype" name="instructiontype">
			<c:forEach var="instructiontype" items="${instructiontypes}">
				<c:choose>
					<c:when test="${instruction.id == instructiontype.id}">
						<c:set var="selected" value="selected"/>
					</c:when>
					<c:otherwise>
						<c:remove var="selected"/>
					</c:otherwise>
				</c:choose>
				<option value="${instructiontype.id}" ${selected}>${instructiontype.name}</option>
			</c:forEach>
		</select>
							
			<br>
			<label for="name">Название</label>
			<br>
			<input  pattern="^[А-Яа-яЁё\s]+$" type="text" id="name" name="name" value="${instruction.name}">
			<br>
			<br>
			<label for="serialnumber">Номер:</label>
			<br>
			<input type="text" pattern="^[ 0-9]+$" id="serialnumber" name="serialnumber" 
			value="${instruction.serialnumber}" required >
			<br>
			<br>
			<label for="dateofcreation">Дата утверждения</label>
			<br>
			<input type="text" id="dateofcreation" name="dateofcreation" value="${instruction.dateofcreation}">
			<br>
			<br>
			<label for="expirationdate">Дата окончания действия</label>
			<br>
			<input type="text" id="expirationdate" name="expirationdate" value="${instruction.expirationdate}">
			<br>
			<br>
			<button type="submit">Сохранить</button>
		</form>

</u:page1>