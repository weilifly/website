<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/taglib.jsp"%>
	<div>
		<table id="help" style="width: 100%; color: #333333;"
			cellspacing="1" cellpadding="3" bgcolor="#dee7ef" border="0">
			<tbody>
				<tr class=gvHeader>
					<th style="width: 35px;"  scope="col">
						选中
					</th>
					<th scope="col">
						产品系列
					</th>
					<th scope="col">
						型号
					</th>
					<th scope="col">
						型号图片
					</th>
					<th scope="col">
						添加时间
					</th>
					<th scope="col">
						是否显示
					</th>
					
					<th scope="col">
						操作
					</th>
				</tr>
				<s:if test="pageBean.page==null || pageBean.page.size==0">
					<tr align="center" class="gvItem">
						<td colspan="10">暂无数据</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="pageBean.page" var="bean" status="st">
						<tr class="gvItem">
							<td>
								<input id="gvNews_ctl02_cbID" class="objId" type="checkbox"
									value="${bean.id }" name="cb_ids" />
							</td>
							<td align="center">
								${bean.categoryName}
							</td>
							<td align="center">
								${bean.name}
							</td>
							<td align="center">
								<img alt="" src="../${bean.image}" width="80"/>
							</td>
							<td align="center">
								<s:date name="#bean.addTime" format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td align="center">
								<s:if test="#bean.status==1">是</s:if>
								<s:elseif test="#bean.status==2">否</s:elseif>
							</td>
							
							<td>
								<input class="bt_select" value="选择" type="button" name="${bean.name }" pid="${bean.id }" />
							</td>
						</tr>
					</s:iterator>
				</s:else>
			</tbody>
		</table>
	</div>
	<table style="border-collapse: collapse; border-color: #cccccc"
		cellspacing="1" cellpadding="3" width="100%" align="center" border="1">
		<tbody>
			<tr>
				<td class="blue12" style="padding-left: 8px" align="left">
					<input id="chkAll" onclick="checkAll(this); " type="checkbox" value="checkbox" name="chkAll" />
					全选 &nbsp;&nbsp;&nbsp;&nbsp;
					<input id="btnDel" onclick="dels();" type="button"
						value="删除选中记录" name="btnDel" />
				</td>
			</tr>
		</tbody>
	</table>
	<shove:page curPage="${pageBean.pageNum}" showPageCount="10" pageSize="${pageBean.pageSize }" theme="jsNumber" totalCount="${pageBean.totalNum}"></shove:page>
