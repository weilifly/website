<%@page import="com.sun.org.apache.xml.internal.serialize.Printer"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/include/taglib.jsp"%>
<link rel="shortcut icon"  href="images/favicon.ico"  />
		<link href="css/Common.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet" href="css/base.css" id="camera-css" />
		<link rel="stylesheet" href="css/framework.css" />
		<link rel="stylesheet" href="css/noscript.css" media="screen,all" id="noscript" />

<!-- 悬浮窗体开始 -->
<div class="suspend-box">
   <div class="jtn" id="jt"></div>
			<ul class="sus-ul">
				<jsp:include page="sendToUs.jsp"></jsp:include>
			</ul>
</div>
<!-- 悬浮窗体结束 -->
<div class="back-top" title="返回顶部"></div>

<div class="head-box">
	<div class="head">
				<a href="index.do" class="logo">微力科技，为企业插上微翅膀，让生意更轻松</a>
				<div id="mainmenu">
					<div class="sliding-block"></div>
					<ul id="menu">
					<!--
					<s:iterator value="#request.navigationBarFirstList" var="firstBean" status="st">
						<li><a href="index.do">首页</a></li>
					</s:iterator>
					-->
						 <li class="selected dropdown"><a href="index.do">首页</a>
						</li>
						<li>微力研究院
							<ul>
								<li><a href="queryWeiliDisplay.do?typeId=6">方法论</a>
								</li>
								<li><a href="queryWeiliDisplay.do?typeId=7">最连续沙龙</a>
								</li>
								<li><a href="queryWeiliDisplay.do?typeId=8">必读书籍</a>
								</li>
							</ul>
						</li>
						<li>O2O兵器库
							<ul>
								<li><a href="queryWeiliDisplay.do?typeId=11">O2O型网站</a>
								</li>
								<li><a href="queryWeiliDisplay.do?typeId=12">O2O型商城</a>
								</li>
								<li><a href="queryWeiliDisplay.do?typeId=13">手机APP</a>
								</li>
								<li><a href="queryWeiliDisplay.do?typeId=14">腾讯系列</a>
								</li>
								<li><a href="queryWeiliDisplay.do?typeId=15">阿里系列</a>
								</li>
								<li><a href="queryWeiliDisplay.do?typeId=16">百度系列</a>
								</li>
							</ul>
						</li>
						<li><a href="#">标杆案例</a>
							<ul>
								<li><a href="queryWeiliDisplay.do?typeId=9"> 专业市场</a>
								</li>
								<li><a href="queryWeiliDisplay.do?typeId=10"> 传统商户</a>
								</li>
							</ul>
						</li>
						<li><a href="downloadIndex.do">下载专区</a>
							<ul>
								<li><a href="preDownloadIndex.do?categoryId=1">精品课件</a>
								</li>
								<li><a href="preDownloadIndex.do?categoryId=2">精品视频</a>
								</li>
								<li><a href="preDownloadIndex.do?categoryId=3">行业报告</a>
								</li>
								<li><a href="preDownloadIndex.do?categoryId=4">案例下载</a>
								</li>
							</ul>
						</li>
						<li><a href="www.weilifly.com">微力论坛</a>
						</li> 
					</ul>
				</div>
			</div>
</div>
<!--头部外围盒子结束 head-box-->




