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

						<div style="text-align: center;">
							<h1 id="title">Cadastrar Livro</h1>
						</div>
						
						<c:if test="${erro !=null }">
							<div id="menssagem"	class="alert alert-danger alert-dismissible fade show"	role="alert">
								<strong> Menssagem ?</strong>${erro }
								<button type="button" class="btn-close" data-bs-dismiss="alert"		aria-label="Close"></button>
							</div>
						</c:if>

						<div style="height: 2px;" class="line-separator"></div>

						<div class="row">
							<div class=" col col-lg 8">

								<div class="row">
									<div class="col-2">
										<div class="content-img-perfil">
										<c:if test="${livro.capaLivro != null }"> 
												<img id="visualisador" 	class="img-foto-user" alt="" src="${livro.capaLivro }"	height="160px;" width="150px;">
										</c:if>
										
										<c:if test="${livro.capaLivro == null }"> 
												<img id="visualisador" 	class="img-foto-user" alt="" src="img/avatars/avatar-7.jpg"	height="160px;" width="150px;">
										</c:if>
										</div>

									</div>
								</div>
								<form id="formUser" class="row g-3"		enctype="multipart/form-data"		action="<%=request.getContextPath()%>/salvarlivro"		method="post">


									<div style="height: 2px;" class="line-separator"></div>

                                       

									<div class="row">
											<div class="col-md-6">
												<label for="capalivro" class="form-label">Capa Do Livro </label> 
												<input onchange="carregarCapaLivro()" accept="image/*" type="file"	name="capalivro" class="form-control" id="capalivro">
											</div>
                                     
										<div class="col-md-3">
											<label for="id" class="form-label">Id do Livro </label>
											 <input   value="${livro.idLivro }"	readonly="readonly"  type="text" name="idlivro"	class="form-control"	placeholder=" Indentificador E Automatico" id="id">
										</div>
   


										<div class="col-md-6">
											<label for="nomelivro" class="form-label">Nome Do Livro </label> 
												<input value="${livro.nomeLivro }" type="text" name="nomelivro"	class="form-control"  placeholder="Nome Do Livro"	id="nomelivro">
										</div>

										<div class="col-md-6">
											<label for="titulolivro" class="form-label">Titulo Do	Livro </label> 
												<input value="${livro.tituloLivro }" type="text" name="titulolivro"		class="form-control"  placeholder="Titulo Do Livro"	id="titulolivro">
										</div>


										<div class="col-md-6">
											<label  for="datapublicacao" class="form-label">Ano De Lancamento</label> 
												<input value="${livro.dataPublicacao }" type="date" class="form-control" id="datapublicacao" name="datapublicacao">
										</div>

									</div>


										


										<div class="form-floating">
											<textarea   class="form-control"  maxlength="1000" 	name="sobrelivro"   placeholder="sobre" id="floatingTextarea">${livro.sobreLivro}</textarea>
											<label for="floatingTextarea">Sobre Esse Livro</label>

										</div>

									<div class="col-12">
										<button type="submit" disabled="disabled" class="btn btn-primary">Salvar
											Livro</button>
										<button type="button" class="btn btn-primary">Limpar
										</button>

									</div>
								</form>



							</div>
							<!-- Button trigger modal -->

							<!-- Modal -->
							<div class="modal fade" id="exampleModal" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">

								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Pesquisar</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>

										<div class="modal-body">
											<div class="input-group mb-3">
												<input id="nomeUser" type="text" class="form-control"
													placeholder="Digite Aqui  Sua Buscar"
													aria-label="Recipient's username"
													aria-describedby="button-addon2">
												<button onclick="pesquisaUsuario();" class="btn btn-success"
													type="button" id="buscar">Buscar</button>
											</div>
										</div>

										<div style="overflow: scroll; height: 300px;">
											<table id="tabela" class="table">
												<thead>
													<tr>
														<th scope="col">Identificador</th>
														<th scope="col">Nome</th>
														<th scope="col">E-mail</th>
														<th scope="col">Editar</th>

													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>
										</div>
										<h6 id="total" style="color: red; text-align: center;"></h6>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Encerrar</button>
										</div>
									</div>
								</div>
							</div>



						</div>

					</div>


				</div>
			</main>

		</div>


	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		crossorigin="anonymous"></script>


	<script type="text/javascript">
		function carregarCapaLivro() {
			var formFile = document.getElementById("capalivro");
				var visualisadorFoto = document.getElementById("visualisador");
				visualisadorFoto.src = URL.createObjectURL(event.target.files[0]);
				window.console.log(event.target.files[0])
				visualisadorFoto.addEventListener("load" , function(){
				URL.revokeObjectURL(visualisadorFoto.src);
				     
			
				
			})

		}
	</script>





</body>
<jsp:include page="script.jsp"></jsp:include>

</html>