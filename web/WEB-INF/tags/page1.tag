<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false" rtexprvalue="true"
	type="java.lang.String"%>
<%@ attribute name="css" required="false" rtexprvalue="true"
	type="java.lang.String"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<title>Web application for Health Safety Work environment</title>
<c:url var="lgCssUrl" value="/lg4.css"></c:url>
<link href="${lgCssUrl}" rel="stylesheet" type="text/css">

</head>
<body>
	<style>
* {
	box-sizing: border-box;
}

.grid-container {
	height: 100vh;
	display: grid;
	grid-template-areas: "header" "menu" "content" "footer";
	
	grid-template-rows: 10em  5em  36em ;
}

.header {
	grid-area: header;
	text-align: center;
	color:white;
	background-image: url(../upload/dark-gray-metal-grid-2.png)
}


.menu {
	grid-area: menu;
	background-color: #F4A460;
	
	
}

.content {
	grid-area: content;
	background-image: url(../upload/hse-max2.jpg)
}

.footer {
	grid-area: footer;
	color:white;
	background-image: url(../upload/dark-gray-metal-grid-2.png)
}


</style>

<body>

	<div class="grid-container">
	
		<div class="header">
		
		<img  src="../upload/84461585746713.png" style=" position: absolute;
	padding: 0px;
	margin: 0px;
	width: 130px;
	height: 130px;
	top: 15px;
	border-radius: 60px;
	left: 20px">
			<h1>
				<a>ROM DEVELOPMENT</a>
			</h1>
			<h2>
				<a>Helth safety environment management system</a>
			</h2>
		</div>
		<div class="content">
			<jsp:doBody></jsp:doBody>
		</div>
		<div class="menu">
			<ul>
   
   <li><a href="#"><i class="fa fa-shopping-cart"></i>Сотрудники</a>
      <ul>
      <c:url var="editUrl" value="/employeecard/edit.html">
						<c:param name="id" value="${employeecard.id}" />
					</c:url>
      <c:url var="editUrl" value="/employeecard/edit.html" />
         <li><a href="${editUrl}">Добавить нового</a> </li>
         <li><a href="#">Найти сотрудника</a>
            
      </ul>
   </li>
   <li><a href="#"><i class="fa fa-cogs"></i>Документы</a>
      <ul>
         <li><a href="#">Приказы</a>
          <ul>
               <li><a href="#">Добавить новый</a></li>
                <li><a href="#">Найти существующий</a></li>
               </ul>
               </li>
         <li><a href="#">Предписания</a>
         <ul>
               <li><a href="#">Добавить новое</a></li>
                <li><a href="#">Найти существующее</a></li>
               </ul>
               </li>
         <li><a href="#">Информационные письма</a></li>
      </ul>
   </li>
   <c:url var="listUrl" value="/instruction/list.html" />
   <li><a href="${listUrl}"><i class="fa fa-th-list"></i>Инструкции</a>
   <ul>
         <li><a href="#">Должностные</a></li>
      <c:url var="editUrl" value="/instruction/edit.html">
						<c:param name="id" value="${instruction.id}" />
					</c:url>
      <c:url var="editUrl" value="/instruction/edit.html" />
         <li><a href="№">По охране труда</a></li>
         <li><a href="#">По работе на оборудовании</a></li>
         <li><a href="#">Карты техпроцесса</a></li>
          <li><a href="${editUrl}">Добавить новую</a></li>
      </ul>
   </li>
   <li><a href="#"><i class="fa fa-envelope-open"></i>Контакты</a></li>
   <li><a href="#"><i class="fa fa-home"></i>Профиль</a></li>
   <c:if test="${not empty sessionUser}">
   <c:url var="logoutUrl" value="/logout.html"/>
    <li><a href="${logoutUrl}"><i class="fa fa-home"></i>Выйти</a></li>
    </c:if>
</ul>   
		</div>

		<div class="footer">
			<p>© copyright 2020 Roman Belousov</p>
		</div>
	</div>
</body>
</body>
</html>