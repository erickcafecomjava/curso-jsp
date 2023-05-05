<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<jsp:include page="header.jsp"></jsp:include>

<body>
	<div class="wrapper">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div class="main">
			<jsp:include page="navbar.jsp"></jsp:include>

			<main class="content">
				<div class="container-fluid p-0">
					<h1 class="h3 mb-3">
						<strong>Bem Vindo </strong>${ dados.nome }</h1>

					<div>


						<div  style="display: flex; justify-content: center; align-items: center; " class="sidebar-brand">
							<img alt="" 
								src="img/avatars/avatar-7.jpg">
						</div>
					</div>

				</div>
			</main>

		</div>
	</div>


	<jsp:include page="script.jsp"></jsp:include>






</body>

</html>