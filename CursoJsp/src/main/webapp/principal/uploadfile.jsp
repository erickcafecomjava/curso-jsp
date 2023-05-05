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
					<div class="container-sm">
					 <div style="text-align: center;">
					     <h1 id="header-title">Cadastrar Arquivo  Do Livro</h1>
					 </div>
					 
					  <h4 style="color: red;">
                        ESTA EM FASE DE IMPLEMENTACAO DECULPE-ME EM BREVE ESTARA PRONTO
                       </h4>

							<div id="menssagem"	class="alert alert-danger alert-dismissible fade show"	role="alert">
								<strong> Menssagem ?</strong>${menssagem }
								<button type="button" class="btn-close" data-bs-dismiss="alert"		aria-label="Close"></button>
							</div>


						<div class="row">
							<form id="formupload" action="<%=request.getContextPath() %>/UploadPdf" enctype="multipart/form-data"  method="post">
							    <div class="col-md-8">
									<label for="anexo" class="form-label">Arquivo</label>
									 <input accept="application/pdf" type="file" name="anexo"	class="form-control" id="anexo">
								</div>
								
								 <div class="col-md-5">
								
									<label for="" class="form-label">indentificador</label>
									 <input  type="text" readonly="readonly" name="identificado" value="${dadoslivro.idLivro }"	class="form-control" id="anexo">
								</div>
								
								<div class="col-md-5">
								
									<label for="" class="form-label">Livro</label>
									 <input type="text" readonly="readonly" value="${dadoslivro.nomeLivro }"	class="form-control" id="anexo">
								</div>
								
									

								<div style="height: 60px;"></div>
                                 
                                 
                              
                              <div class="col-12">
									<button style="width: 300px;" type="submit" id="btn-enviar-arquivo" class="btn btn-primary">Enviar Arquivo </button>
                             
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