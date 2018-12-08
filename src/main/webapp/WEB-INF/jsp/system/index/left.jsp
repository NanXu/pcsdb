<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<!--框架必需start-->
<script type="text/javascript" src="<%=basePath%>/js/qui/3.3/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/qui/3.3/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/qui/3.3/libs/js/framework.js"></script>
<link href="<%=basePath%>/js/qui/3.3/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=basePath%>/js/qui/3.3/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->

<!--纵向抽屉容器start-->
<script type="text/javascript" src="<%=basePath%>/js/qui/3.3/libs/js/nav/accordion.js"></script>
<!--纵向抽屉容器end-->
<script type="text/javascript">
	function loadMenu(url, content) {
		//alert(content);
		url = "<%=basePath%>/"+url;
		window.parent.changeIFrame(url);
		window.parent.currentPosition(content);
	}
</script>
</head>
<body>
<div class="accordition" style="height: 580px;">
  		<c:forEach items="${menuList }" var="menu">
  			<c:if test="${menu.parentId eq '0' }">
  				<a>${menu.name }</a>
  				<div>
  					<c:forEach items="${menuList }" var="childMenu">
  						<c:if test="${childMenu.parentId eq menu.id }">
  							<c:set var="parent" value="${menu.name }" />
  							<c:set var="current" value="${childMenu.name }" />
  							<a onclick="loadMenu('${childMenu.url}', '${parent }'+'>'+'${current }')">&nbsp;${childMenu.name }</a><br />
  						</c:if>
  					</c:forEach>
  				</div>
  			</c:if>
  		</c:forEach>
</div>
</body>
</html>