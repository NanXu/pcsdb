<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../include/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../../include/head.jsp"%>
<script type="text/javascript">
	var setting = {
		check: {
			enable: true
		}
	};
	
	var zNodes = ${menuList};

	function initComplete(){
		$.fn.zTree.init($("#tree-1"), setting, zNodes);
		//显示“父子关联关系”用的 实际项目中不用
	}

	//选择结果
	function getSelectValue(){
		//获取zTree对象
		var zTree = $.fn.zTree.getZTreeObj("tree-1");
		//得到选中的数据集
		var checkedNodes = zTree.getCheckedNodes(true);
		
		var msg = "";
		
		for(var i = 0; i < checkedNodes.length; i++){
			msg += "," + checkedNodes[i].id;
		}
		if(msg != ""){
			msg = msg.substring(1);
		}
		return msg;
	}
	
$(function() {
	$("#saveRoleMenuAssociationButton").click(function() {
		var checkedNode = getSelectValue();
		if(checkedNode == "") {
			window.parent.topWin.Dialog.alert("请选择至少一个子菜单");
		} else {
			//alert(checkedNode);
			var roleId = $("#roleId").val();
			$.ajax({
				  type: 'POST',
				  url: '${ctx}/system/role/associationMenu',
				  data: {'roleId': roleId, 'menuIds' : checkedNode},
				  dataType: 'JSON',
				  success: function(data){
						if(data.success){
							 window.parent.topWin.Dialog.alert(data.msg + "|提示",function(){
								 window.parent.changeIFrame('${ctx}/system/role/listRoles');
								 window.parent.topWin.Dialog.close();
							 });
						}else{
							 window.parent.topWin.Dialog.alert(data.msg + "|提示",function(){
							  });
						}
				  },
				  error:function(){
				  }			  
			});
		}
		//alert(checkedNode);
	});
	

	$("#saveRoleMenuAssociationCloseButton").click(function() {
		//window.parent.changeIFrame('${ctx}/system/role/listRoles');
		window.parent.topWin.Dialog.close();
	});
});


</script>
</head>
<body>
	<div class="box1" panelTitle="角色菜单列表" style="height: 300">
		<input type="hidden" value="${roleId }" name="roleId" id="roleId">
		<ul id="tree-1" class="ztree"></ul>
	</div>
	<div>
		<center>
	    	<br/>
	    	<button type="button" id="saveRoleMenuAssociationButton"><span>保存</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" id="saveRoleMenuAssociationCloseButton"><span>关闭</span></button>
			<br/>
			<br/>
		</center>
	</div>
</body>
</html>