<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
					<div class="container-sm">
						<div class="header-title">
							<h1>Relatorios De Usuarios</h1>
						</div>
						
						 <h4 style="color: red;">
                        ESTA EM FASE DE IMPLEMENTACAO DECULPE-ME EM BREVE ESTARA PRONTO
                       </h4>
						<div class="row">
							<form action="gerarrelatorio">
							        
							        <label for="dataaniversario">Pesquisar Por Data </label>
									<div class="input-group mb-3">
										 <input type="date" class="form-control" id="dataaniversario" name="dataaniversario">
									</div>
									<div class="col">
										 <button style="margin: 5px;" class="btn btn-success" type="button"	id="button-addon2">Gerar Relatorio  PDF</button>
										 <button disabled="disabled" style="margin: 5px;" class="btn btn-success" type="button"	id="button-addon2">Gerar Relatorio HTML</button>
									</div>

							</form>
							
							
						</div>


					</div>
				</div>
			</main>

		</div>
	</div>


	<jsp:include page="script.jsp"></jsp:include>



</body>

</html>