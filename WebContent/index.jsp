<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Receipts</title>
</head>
<body>

	<h1>Manage Receipts!</h1>
	<div>
		Enter client name: <input type="text" name="client"
			id="client_name_input" required="required"> Enter
		description: <input type="text" name="description" 
			id="description_input"> <input type="submit" value="Add"
			onclick="insertRecipt()" />
	</div>
	<div id="datatablediv">
		<table id="datatable"></table>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$.ajax({
				url : "/RecieptCRUD/recieptServc",
				type : "post",
				context : document.body,
				success : function(data) {
					fillTableReciept(data);
					//$( "#datatable" ).append( data );

				}
			});
		});
		function fillTableReciept(data) {
			$('#datatable').empty();
			$('#datatable').append(
					"<tr><th>ID</th><th>Client Name</th>"
							+ "<th>Description</th><th></th><th></th></tr>");

			const datajson = JSON.parse(data);
			$
					.each(
							datajson,
							function(key, value) {
								var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td></tr>");
								rowNew.children().eq(0).text(value.id);
								const nameInput = $('<input>', {
									type : 'text',
									id : 'nameInput_' + value.id,
									value : value.client_name
								});
								rowNew.children().eq(1).append(nameInput);
								const descInput = $('<input>', {
									type : 'text',
									id : 'descInput_' + value.id,
									value : value.description
								});
								rowNew.children().eq(2).append(descInput);
								//rowNew.children().eq(1).text(value.client_name);
								//rowNew.children().eq(2).text(value.description);
								const updatelink = $('<a>', {
									text : 'update',
									title : 'update',
									href : '#',
									click : function() {
										updatefunc(value.id)
									}
								});
								rowNew.children().eq(3).append(updatelink);
								const deletelink = $('<a>', {
									text : 'delete',
									title : 'delete',
									href : '#',
									click : function() {
										deletefunc(value.id)
									}
								});
								rowNew.children().eq(4).append(deletelink);
								$('#datatable').append(rowNew);
							});
		}

		function updatefunc(id) {
			console.log(id);
			//do some validations
			console.log(document.getElementById("nameInput_"+id).value);
			const name = document.getElementById("nameInput_"+id).value;
			//	$("#nameInput_"+id).val();
			const desc = document.getElementById("descInput_"+id).value;
				//$("#descInput_"+id).val();
			updateReciept(id,name, desc);
		}
		function updateReciept(id,name, desc) {
			$.post('/RecieptCRUD/updateReciept', {
				client_id : id,
				client_name : name,
				description : desc
			}, function(returnedData) {
				fillTableReciept(returnedData);
				alert("Done");
			});
		}
		
		function deletefunc(id) {
			$.post('/RecieptCRUD/deleteReciept', {
				recieptID : id
			}, function(returnedData) {
				fillTableReciept(returnedData);
				alert("deleted!");
			});
		}

		function insertRecipt() {
			//do some validations
			const name = $("#client_name_input").val();
			const desc = $("#description_input").val();
			insertfunc(name, desc);
		}
		function insertfunc(name, desc) {
			$.post('/RecieptCRUD/addReciept', {
				client_name : name,
				description : desc
			}, function(returnedData) {
				fillTableReciept(returnedData);
				alert("Done");
			});
		}
	</script>
</body>
</html>