<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="span12">
		<img src="${pageContext.request.contextPath}/images/logo.png">
	</div>
</div>
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="index">首页</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<c:forEach var="newsType" items="${newsTypeList}">
					<li><a href="foreground/newsList?typeId=${newsType.newsTypeId }">${newsType.typeName }</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</nav>

