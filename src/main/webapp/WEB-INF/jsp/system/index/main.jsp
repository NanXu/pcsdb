<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=9" ></meta>
<title>气候相关民用飞机事故数据库</title>
<%@ include file="qui.jsp"%>
<script>
function bookmarksite(title, url){
    if (window.sidebar) // firefox
        window.sidebar.addPanel(title, url, "");
    else 
        if (window.opera && window.print) { // opera
            var elem = document.createElement('a');
            elem.setAttribute('href', url);
            elem.setAttribute('title', title);
            elem.setAttribute('rel', 'sidebar');
            elem.click();
        }
        else 
            if (document.all)// ie
                window.external.AddFavorite(url, title);
}
function exitHandler(){

	top.Dialog.confirm("确定要退出系统吗",function(){});
}

$(function(){
	/* if(broswerFlag!="IE6"){
		var cookTip=jQuery.jCookie('closeTip');
		if(!cookTip){
			$("#lbox").tip({content: "分隔条现在可以左右拖拽啦！<span class='red'><a onclick='closeTip()'>我知道了</a></span>。",distanceY:100,distanceX:10,arrowDirection:"left",width:160,showCloseBtn:true,onClose:function(){
				jQuery.jCookie('closeTip',"sure");
			}});
		}
	} */
})
function closeTip(){
	jQuery.jCookie('closeTip',"sure");
	$("#lbox").hideTip();
}

function changeIFrame(url){
	document.getElementById('frmright').src = url;
}

function currentPosition(content) {
	content = "当前位置：>" + content;
	$("#positionContent").html(content);
}
</script>
</head>
<body>
<div id="mainFrame">
<!--头部与导航start-->
<div id="hbox">
	<div id="bs_navcenter">
	<div id="bs_navleft">
	<div id="bs_navright">
		<div class="bs_nav">
			
			<div class="float_left padding_top2 padding_left5">
				【今天是
				<script>
					var weekDayLabels = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
					var now = new Date();
				    var year=now.getFullYear();
					var month=now.getMonth()+1;
					var day=now.getDate()
				    var currentime = year+"年"+month+"月"+day+"日 "+weekDayLabels[now.getDay()]
					document.write(currentime)
				</script>】
			</div>	
			<div class="float_left" style="padding:2px 0 0 20px;" id="positionContent"></div>	
			<div class="float_right padding_top2 padding_right5">
				<!-- <div class="bs_navleft">
					<li class="fontTitle">字号:</li>
					<li class="fontChange"><span><a href="javascript:;" setFont="16">大</a></span></li>
					<li class="fontChange"><span><a href="javascript:;" setFont="14">中</a></span></li>
					<li class="fontChange"><span><a href="javascript:;" setFont="12">小</a></span></li>
					<div class="clear"></div>	
				</div>	 -->
				
				<!-- <div class="bs_navleft">
					<li class="fontTitle">字体:</li>
					<li class="fontFamily"><span><a href="javascript:;" setFont="宋体">宋</a></span></li>
					<li class="fontFamily"><span><a href="javascript:;" setFont="微软雅黑">雅</a></span></li>
					<div class="clear"></div>	
				</div>	 -->
				<span class="icon_user hand">当前用户:${sessionUser.username }&nbsp;</span>
				<span class="icon_fullscreen hand" id="fullSrceen" hideNav="true">开启全屏</span>
				<span class="icon_exit hand" onclick='top.Dialog.confirm("确定要退出系统吗",function(){window.location="<%=basePath%>/logout"});'>退出系统</span>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	</div>
	</div>
</div>
<!--头部与导航end-->

 <div id="mainLayout">
    <div position="left">
			<div id="lbox">
				<div id="lbox_topcenter">
				<div id="lbox_topleft">
				<div id="lbox_topright">
				</div>
				</div>
				</div>
				<div id="lbox_middlecenter">
				<div id="lbox_middleleft">
				<div id="lbox_middleright">
					<div id="bs_left" style="width:100%;">
						<IFRAME height="100%" width="100%"  frameBorder=0 id=frmleft name=frmleft src="<%=basePath%>/system/left"  allowTransparency="true"></IFRAME>
					</div>
					<!--更改左侧栏的宽度需要修改id="bs_left"的样式-->
				</div>
				</div>
				</div>
				<div id="lbox_bottomcenter">
				<div id="lbox_bottomleft">
				<div id="lbox_bottomright">
					<div class="lbox_foot"></div>
				</div>
				</div>
				</div>
			</div>
    </div>
    <div position="center">
   		<div class="ali01 ver01"  width="100%">
			<div id="rbox">
				<div id="rbox_topcenter">
				<div id="rbox_topleft">
				<div id="rbox_topright">
				</div>
				</div>
				</div>
				<div id="rbox_middlecenter">
				<div id="rbox_middleleft">
				<div id="rbox_middleright">
					<div id="bs_right">
				       <IFRAME height="100%" width="100%" frameBorder=0 id=frmright name=frmright allowTransparency="true">
				       	
				       </IFRAME>
					</div>
				</div>
				</div>
				</div>
				<div id="rbox_bottomcenter" >
				<div id="rbox_bottomleft">
				<div id="rbox_bottomright">

				</div>
				</div>
				</div>
			</div>
		</div>
    </div>
</div> 

<!--尾部区域start-->
<div id="fbox">
	<div id="bs_footcenter">
	<div id="bs_footleft">
	<div id="bs_footright">
		版权所有：中国民航大学适航中心
	</div>
	</div>
	</div>
</div>
</div>
<!--尾部区域end-->


<!--窗口任务栏区域start-->
<div id="dialogTask" class="dialogTaskBg" style="display:none;"></div>
<!--窗口任务栏区域end-->

<!--浏览器resize事件修正start-->
<div id="resizeFix"></div>
<!--浏览器resize事件修正end-->

<!--载进度条start-->
<div class="progressBg" id="progress" style="display:none;"><div class="progressBar"></div></div>
<!--载进度条end-->

</body>
</html>
