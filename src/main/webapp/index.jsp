<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String basePath = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<script type="text/javascript">
	function send(){
		document.getElementById("sendform").submit();
	}
</script>
<script type="text/javascript">
       var intervalID = 0;
       var progressBar;
       var fileUpload;
       var form;       
       // 进度条      
       function pageLoad(){           
           $addHandler($get('upload'), 'click', onUploadClick);
           progressBar = $find('progress');
       }
       // 注册表单       
       function register(form, fileUpload){            
           this.form = form;
           this.fileUpload = fileUpload;
       }        
       //上传验证
       function onUploadClick() {        
           var vaild = fileUpload.value.length > 0;
           if(vaild){              
               $get('upload').disabled = 'disabled';             
               updateMessage('info', '初始化上传...');                
               //提交上传
               form.submit();                
               // 隐藏frame
               Sys.UI.DomElement.addCssClass($get('uploadFrame'), 'hidden');
               // 0开始显示进度条
               progressBar.set_percentage(0);
               progressBar.show();           
               // 上传过程
               intervalID = window.setInterval(function(){
                   PageMethods.GetUploadStatus(function(result){
                       if(result){
                           //  更新进度条为新值
                           progressBar.set_percentage(result.percentComplete);
                           //更新信息
                           updateMessage('info', result.message);
                           
                           if(result == 100){
                               // 自动消失
                               window.clearInterval(intervalID);                        
                           }
                       }
                   });
               }, 500);                
           }
           else{
               onComplete('error', '您必需选择一个文件');
           }
       }       
   
       function onComplete(type, msg){
           // 自动消失
           window.clearInterval(intervalID);
           // 显示消息
           updateMessage(type, msg);
           // 隐藏进度条
           progressBar.hide();
           progressBar.set_percentage(0);
           // 重新启用按钮
           $get('upload').disabled = '';
           //  显示frame
           Sys.UI.DomElement.removeCssClass($get('uploadFrame'), 'hidden');
       }        
       function updateMessage(type, value){
           var status = $get('status');
           status.innerHTML = value;
           // 移除样式
           status.className = '';
           Sys.UI.DomElement.addCssClass(status, type);
       }
   
   </script>
</head>
<body>
hello!!!<br/>
<a href="<%=basePath%>/god/fdafa">啥都没有点什么</a><br/>
<form id="sendform" enctype="multipart/form-data" action="<%=basePath%>/dosome" method="post" >
	id:<input name="id" type="text"><br>
	name:<input name="name" type="text"><br>
	<input name="uf" type="file">
	<a href="javascript:send();">造点数据</a>
</form>
</body>
</html>