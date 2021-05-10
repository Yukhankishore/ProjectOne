<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
.container{
	background-color:#3D5B59;
	color:#B5E5CF;
	height:100vh;
	width:100vw;
	display:flex;
	justify-content: center;
	align-items:center;
	flex-direction:column;
}
.view{
	height: 60vh;
	width:40vw;
	background-color:#BACC81;
	color:#000;
	display:flex;
	flex-direction:column;
	justify-content:center;
}
.item{
	padding:10px;
	font-weight:800;
	padding-left:100px;
	text-transform: capitalize;
}
</style>

</head>
<body class="container"> 
	<%
		String name = session.getAttribute("name").toString();
		String phoneNumber = session.getAttribute("phonenumber").toString();
		String salary =  session.getAttribute("salary").toString();
		String pension = session.getAttribute("pension").toString();
		String address = session.getAttribute("address").toString();
	%>
	<div class="view">
		<div class="item">name : <%=name %></div>
		<div class="item">phoneNumber : <%=phoneNumber %></div>
		<div class="item">Salary :<%=salary %></div>
		<div class="item">Pension : <%=pension %></div>
		<div class="item">address : <%=address %></div>
	</div>
	
</body>
</html>