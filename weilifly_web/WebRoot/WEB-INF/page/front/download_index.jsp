<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>${seoMap.seoTitle}</title>
		<meta name="keywords" content="${seoMap.seoKeyWord}"/>
		<meta name="description" content="${seoMap.seoDescription}" />
		<link href="css/Common.css" type="text/css" rel="stylesheet"/>
		<link href="css/site.css" type="text/css" rel="stylesheet"/>
		<script src="script/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="script/jquery.shove-1.0.js"></script>
		<script src="script/jquery.slider.js"></script>
		<script src="script/easing.js"></script> 

		<script type="text/javascript">
			$(function(){
				//$('#pic-list-s').Slider();
				$(".path").click(function(){
					window.location.href = "downloadProgram.do?filePath="+$(this).attr("path");
				});
				
				$("li[name=li]").click(function(){
					$("li[name=li]").removeClass("cur");
					$(this).addClass("cur");
				});
				   $("#pic-list-s").Slider(); 
			});
			function productTab(pcId){
				$("li[name ^= divCategory_]").hide();
				$("li[name=divCategory_"+pcId+"]").show();
			}

			    

		</script>
	</head>
	<body>
		<!-- 动态包含top开始 -->
		<jsp:include page="/include/top.jsp"></jsp:include>
		<!-- 动态包含top结束 -->
		<!--头部外围盒子结束 head-box-->
		<div class="content">
			<div class="main-content3 clearfix" style="border: 0; width: 1000px;">
				<div class="left-box left-box-1">
					
					<!-- 资料下载《案例下载》开始 -->
					<div class="m-s-ban1">
						资料下载《案例下载》
					</div>
					
					<%--	
					<div class="n-s-nav n-s-nav-1">
						<div class="s-block"></div>
						<ul>
							<s:iterator value="#request.categoryList" var="bean" status="st">
								<li <s:if test="#st.index==0">class="cur"</s:if> name="li">
									<a href="javascript:productTab(${bean.id});" class="pcp" pcId="${bean.id}">${bean.name}</a>
								</li>
							</s:iterator>
						</ul>
					</div>
					--%>
					
				<div class="pic-list-s" id="pic-list-s">
						<em class="left" id="lefta"></em>
						<em class="right" id="righta"></em>
						 <div class="roll-box">
						<ul class="roll-list">
							<s:iterator value="#request.categoryList" var="bean" status="st">
								<s:iterator value="#request.productList.{?#this.categoryId==#bean.id}" var="beans" status="sta">
									<s:if test="#st.index==0"><li style="display: block;" name="divCategory_${bean.id }" class="ssa"></s:if>
									<s:else><li style="display: none;" name="divCategory_${bean.id }" class="ssa"></s:else>
										<a href="materialsIndex.do?id=${beans.id}" class="pic"><img src="${beans.image}" />
										</a>
										<div class="title">
											<a href="materialsIndex.do?id=${beans.id}">${beans.name}</a>
										</div>
										</li>
								</s:iterator>
							</s:iterator>
						</ul>
						</div>
					</div>
					<!-- 资料下载《案例下载》结束 -->
					
					<!-- 下载专区《行业报告》开始 -->
					<div class="m-s-ban m-s-ban-1">
						<a href="updateProgramIndex.do">more+</a>下载专区《行业报告》
					</div>
					<ul class="down-list">
						<s:iterator value="#request.updateProgramList" var="bean" status="st">
							<li>
								<a href="javascript:;" class="tit">${bean.name}</a>
								<div class="other">
									<span><s:date name="#bean.addTime" format="yyyy-MM-dd HH:mm:ss" /></span><a href="javascript:;" class="path" path="${bean.path}">下载</a>
								</div>
							</li>
						</s:iterator>
					</ul>
					<!-- 下载专区《行业报告》结束 -->
					
					<!-- 下载专区《精品课件》开始 -->
					<div class="m-s-ban m-s-ban-1">
						<a href="updateProgramIndex.do">more+</a>下载专区《精品课件》
					</div>
					<ul class="down-list">
						<s:iterator value="#request.updateProgramList" var="bean" status="st">
							<li>
								<a href="javascript:;" class="tit">${bean.name}</a>
								<div class="other">
									<span><s:date name="#bean.addTime" format="yyyy-MM-dd HH:mm:ss" /></span><a href="javascript:;" class="path" path="${bean.path}">下载</a>
								</div>
							</li>
						</s:iterator>
					</ul>
					<!-- 下载专区《精品课件》结束 -->
				</div>
				
				<!---左侧列表结束--->
				<div class="right-box">
					<%--<div class="m-s-ban" style="border: 1px solid #e1e1e1;">
						<a href="queryDownloadHelpInit.do">more+</a>下载帮助
					</div>
					
					<s:iterator value="#request.downloadHelpList" var="bean" status="st">
						<div class="down-item-list">
							<!-- <dt>
								<a href="queryDownloadHelpInit.do?id=${bean.id}"><img src="${bean.image }" alt="${bean.title}" style="width:57px;height:57px;" /></a>
							</dt>
							<dd>
								适用机型：
								<span title="${bean.fit}">${bean.fit}</span>
							</dd> -->
							<a href="queryDownloadHelpInit.do?id=${bean.id}"><img src="${bean.image }" alt="${bean.title}" style="width:264px;height:73px;" /></a>
						</div>
					</s:iterator>--%>
					<!-- 下载专区《精品视频》开始 -->
					<div class="m-s-ban m-s-ban-1">
						<a href="advertiseIndex.do">more+</a>App专区《精品视频》
					</div>
					<s:iterator value="#request.advertiseList" var="bean" status="st">
						<div class="ad-video">
							<a href="#" class="pic"><img src="${bean.image}" /><span
								class="play">播放</span>
							</a>
							<!--downloadAndroidMaterials.do?id=${bean.id }   downloadIosMaterials.do?id=${bean.id}
							 -->
							<div class="bk-btn"><a target="_blank" href="${bean.androidPath}"  class="Android">安卓</a>
							<a  target="_blank" href="${bean.iosPath}" class="ios">IOS</a>
							<span class="wx"><em class="img">
							<img src="${maMap.var}"/>
							</em></span></div>
							</div>
					</s:iterator>
					<!-- 下载专区《精品视频》结束 -->
					
					
					
				<!-- 下面代码是表单 -->
				<%--<div id="container">
	<script type="text/javascript">
		$(function(){$("#b_send").click(function(param) {
								//提交表单
								//alert("jjjjjjjjjjjjjjjjjjjj");
								param["paramMap.cName"] = $("#cName").val();
								param["paramMap.cTelephone"] = $("#cTelephone").val();
								param["paramMap.address"] = $("#address").val();
								param["paramMap.needId"] = $("#needId").val();
								param["paramMap.needContent"] = $("#needContent").val();
								
								sendToUs(param);
								/*$.shovePost("sendToUs.do",param,function(data) {
													alert("jjjjjjjjjjjjjjjjjjjj");
													if (data.returnId > 0) {
														alert("恭喜发送成功，我们将会及时的为你提出解决方案！");
														return;
													}
												});*/
								//return false;
							});
		
		function sendToUs(param){
		
			alert($("#cName").val() + "~"
					+ $("#cTelephone").val() + "~"
					+ $("#address").val() + "~"
					+ $("#needId").val());
			/*debugger;
			$.shovePost("sendToUs.do",param,function(data) {
				alert("jjjjjjjjjjjjjjjjjjjj");
				if (data.returnId > 0) {
					alert("恭喜发送成功，我们将会及时的为你提出解决方案！");
					return;
				}
			});*/
			$("#form3").submit();
		}
		
		});
	</script>
	<form id="form3" action="sendToUs.do" method="post">
		<h3>
			<span>联系我们</span>
		</h3>
		<fieldset>
			<p class="first">
				<label for="cName">姓名/公司名：</label> <input type="text" name="cName"
					id="cName" size="30" />
			</p>
			<p>
				<label for="cTelephone">手机号：</label> <input type="text"
					name="cTelephone" id="cTelephone" size="30" />
			</p>
			<p>
				<label for="address">地址：</label> <input type="text" name="address"
					id="address" size="30" />
			</p>
			<p>
				<label for=“needId”>需求类型：</label>
				<s:select list="needId" name="paramMap.needId" listKey="id" listValue="needs_name" headerKey="-2" headerValue="--请选择--"></s:select>
		
				<s:select name="needId" id="needId" theme="simple"
					list="#{-1:'-请选择-',1:'O2O网站',2:'O2O商城','3':'App','4':'其他'}"
					value="-1">
				</s:select>
			</p>
			<p>
				<label for="needContent">需求描述：</label>
				<textarea name="needContent" id="needContent" cols="30" rows="10"></textarea>
			</p>
			<p class="submit">
				<button id="b_send" type="submit">
					<img src="images/form3/form_button.gif" />
				</button>
			</p>
		</fieldset>

	</form>
</div>
				
				
				
				
				--%></div>


			</div>
			<!--内容区域main-content 结束-->
		</div>
		<!--内容区域结束-->
		<jsp:include page="/include/footer.jsp"></jsp:include>
		<!--底部结束-->
	    <script src="script/commom.js"></script>
		<script type="text/javascript">
		  $(function(){
		  		$(".nav li:eq(2)").addClass("cur");
		  		$.sideLi(".nav",".sliding-block");
		  		$.sideLi(".n-s-nav-1",".s-block","click");
		  })
		</script>
	    <script >
$(function(){
    $(".nav li:eq(2)").addClass("cur");
    $.sideLi(".nav",".sliding-block");
    $.sideLi(".n-s-nav",".s-block");
})

</script>



	</body>
</html>
