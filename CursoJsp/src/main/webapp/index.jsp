<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<!------ Include the above in your HEAD tag ---------->
<meta charset="ISO-8859-1">
<title>Login</title>
</head>

<style>
body {
	padding-top: 15px;
	font-size: 12px
}

.main {
	max-width: 320px;
	margin: 0 auto;
}

.login-or {
	position: relative;
	font-size: 18px;
	color: #aaa;
	margin-top: 10px;
	margin-bottom: 10px;
	padding-top: 10px;
	padding-bottom: 10px;
}

.span-or {
	display: block;
	position: absolute;
	left: 50%;
	top: -2px;
	margin-left: -25px;
	background-color: #fff;
	width: 50px;
	text-align: center;
}

.hr-or {
	background-color: #cdcdcd;
	height: 1px;
	margin-top: 0px !important;
	margin-bottom: 0px !important;
}

h3 {
	text-align: center;
	line-height: 300%;
}

.line {
	height: 100px;
}

.alerta {
	color: #664d03;
	background-color: #fff3cd;
	border-color: #ffecb5;
	font-size: 15px;
}

.btn-control {
	margin: auto;
}

</style>

<body>
	<div class="line"></div>
	<div class="container">
		<div class="row">

			<div class="main">

				<c:if test="${menssagem != null }">
					<div id="alerta" class="alert alert-success">
						<button id="btn-closed" type="button" class="close"
							data-dismiss="alert" aria-hidden="true">&times;</button>
						<strong>Algo Ocorreu.. </strong> ${menssagem }
					</div>
				</c:if>

				<div style="text-align: center;">
					<h3>Realize Seu Login</h3>

				</div>

				<form id="formlogin" role="form"
					action="<%=request.getContextPath()%>/logar" method="post">
					<div class="form-group">
						<label for="email">Usuario E-email</label> <input type="email"
							class="form-control" id="email" name="email"
							placeholder="seu e-mail@ ">
					</div>
					<div class="form-group">
						<a  class="pull-right" href="novasenha">Recuperar Senha </a> <label
							for="senha">Sua Senha </label> <input type="password"
							class="form-control" id="senha" name="senha"
							placeholder="sua senha ">
					</div>
					<div
					
						class="content-bt">

						<button id="btn-enviar" type="submit"	class="btn btn btn-primary btn-control">Acessar</button>
						<button onclick="recuperaSenha();" id="btn-recuperasenha" type="button" class="btn btn btn-danger btn-control">Recuperar Senha</button>

					</div>


					<div></div>
				</form>



			</div>

		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="js-login/alert.js"></script>
      
      <script type="text/javascript">
      
        function recuperaSenha(){
        	var email = $("#email").val();
        	if(email == null || email == ''){
        		alert("informe seu email")
        	}else{
        		$.ajax({
        			method:'get',
        			url:'recuperasenha?emailUsuario='+email,
        			success:function(resposta){
        				alert(resposta)
        			}
        		}).fail(function(xhr){
        			alert("ERRO " + xhr.responseText)
        		});
        	}
        }
        
        var btnclosed = document.getElementById("btn-closed");
        btnclosed.addEventListener("click" , function(){
        	document.getElementById("alerta").style.display = "none";
        })
      </script>
      

</body>

</html>