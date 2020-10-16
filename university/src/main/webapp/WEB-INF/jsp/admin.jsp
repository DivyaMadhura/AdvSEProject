<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Page</title>
</head>
<body>
	<h1 style="align:center">Admin Page</h1>
	<hr>
	<form id="registerForm">
	<table>
		<tr><td colspan="2"><h3>Student Registration Portal</h3></td></tr>
		<tr><td>First Name</td><td><input type="text" name="firstName"></td></tr>
		<tr><td>Last Name</td><td><input type="text" name="lastName"></td></tr>
		<tr><td>Mobile Number</td><td><input type="text" name="phoneNumber"></td></tr>
		<tr><td>Date of birth:</td><td><input type="text" name="dob"></td></tr>
		<tr><td>Address</td><td><input type="text" name="address"></td></tr>
		<tr><td>Department</td><td><select id="departmentId" name="departmentId"></select></td></tr>
		<tr><td colspan="2"><input type="button" value="Register Student" id="registerStudent"></td></tr>
	</table>
	</form>
	
	<hr>
	<form id="studentsListForm">
	<table>
		<tr><td colspan="3"><h3>Students List Form</h3></td></tr>
		<tr><td>Department Name: </td><td><select id="deptId" name="deptId"></select></td><td><input type="button" value="Get List" id="studentListId"></td></tr>
	</table>
	</form>
	<ol id="studentListResultId">
		
	</ol>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	<script src="/js/jquery.serializejson.js"></script>
	<script>
		$(document).ready(function(){
			function loadDepartments(){
				$.get("/web/departments",function(data){
					for(i=0;i<data.length; i++){
						$("#departmentId").append("<option value='"+data[i].departmentId+"'>"+data[i].departmentName+"</option>");
						$("#deptId").append("<option value='"+data[i].departmentId+"'>"+data[i].departmentName+"</option>");
					}
				});
			}
			loadDepartments();
			$("#registerStudent").click(function(){
				uploadData = JSON.stringify($("#registerForm").serializeJSON());
				console.log(uploadData);
				$.ajax({
					url:"/web/registerstudent",
					type:"POST",
					data:uploadData,
					contentType:"application/json",
					success:function(){
						alert("Student Registration Success")
					}
				});
			});
			
			$("#studentListId").click(function(){
				$.get("/web/studentslist/"+$("#deptId").val(), function(data){
					console.log(data);
					$("#studentListResultId").html("");
					for(i=0;i<data.length;i++){
						$("#studentListResultId").append("<li>"+data[i].firstName+ " " + data[i].lastName + " " + data[i].phoneNumber+ " "+ data[i].dob+"</li>")
					}
				})
			})
		});
	</script>
</body>
</html>