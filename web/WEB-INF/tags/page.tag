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
<c:url var="CssUrl" value="/lg3.css"></c:url>
<link href="${CssUrl}" rel="stylesheet" type="text/css">
</head>
<body>
	<style>
.grid-container {
	height: 100%;
	display: grid;
	grid-template-areas: "header" "content" "footer";
	grid-template-rows: 9em 30em;
}

.header {
	grid-area: header;
	text-align: center;
	color: white;
	background-image: url(upload/dark-gray-metal-grid-2.png);
}

.content {
	grid-area: content;
	background-image: url(upload/hse-max2.jpg);
}

.footer {
	grid-area: footer;
	color: white;
	background-image: url(upload/dark-gray-metal-grid-2.png);
}
</style>

	<div class="grid-container">

		<div class="header">
			<img src="upload/39951585746861.png"
				style="position: absolute; padding: 0px; margin: 0px; width: 70px; height: 70px; top: 15px; border-radius: 60px; left: 560px">
			<h1
				style="position: absolute; padding: 0px; margin: 0px; top: 95px; left: 450px">
				<a>ROM DEVELOPMENT</a>
			</h1>
		</div>

		<div class="content">
			<jsp:doBody></jsp:doBody>
		</div>

		<div class="footer">
			<p>© copyright 2020 Roman Belousov</p>
		</div>
		
	</div>
</body>
</html>