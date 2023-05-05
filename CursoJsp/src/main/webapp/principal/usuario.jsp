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
					     <h1 id="header-title">Cadastrar Usuario</h1>
					</div>
						<c:if test="${menssagem !=null }">
							<div id="menssagem"	class="alert alert-danger alert-dismissible fade show"	role="alert">
								<strong> Menssagem ?</strong>${menssagem }
								<button type="button" class="btn-close" data-bs-dismiss="alert"		aria-label="Close"></button>
							</div>
						</c:if>

						<div class="row">
							<div class="col-2">
								<div class="content-img-perfil">
									<c:if test="${usuario.foto != null }">
										<img id=" preview" style="border-radius: 50% 50%;"
											class="img-foto-user" alt="" src="${usuario.foto }"
											height="120px;" width="120px;">
									</c:if>

									<c:if test="${usuario.foto == null }">
										<img id=" preview" style="border-radius: 50% 50%;"
											class="img-foto-user" alt="" src="img/avatars/avatar-6.png"
											height="120px;" width="120px;">
									</c:if>
								</div>

							</div>
						</div>
						<div style="height: 2px;" class="line-separator"></div>

						<div class="row">
							<div class=" col col-lg 8">
								<form id="formUser" class="row g-3"
									enctype="multipart/form-data"
									action="<%=request.getContextPath()%>/salvar" method="post">

									<div class="row g-1">
										<div class="col-md-6">
											<label for="foto" class="form-label">Coloque Sua
												Imagem Aqui</label> <input accept="image/*" type="file" name="foto"
												class="form-control" id="foto">
										</div>
									</div>

									<div style="height: 2px;" class="line-separator"></div>

									<div class="row">
										<div class="col-md-3">
											<label for="id" class="form-label">Id</label> <input
												value="${usuario.id }" readonly="readonly" type="text"
												name="id" class="form-control"
												placeholder="Seu Indentificador E Automatico" id="id">
										</div>


										<div class="col-md-6">
											<label for="nome" class="form-label">Seu Nome</label> <input
												value="${usuario.nome }" type="text" class="form-control"
												id="nome" name="nome" placeholder="Digite Aqui Seu Nome">
										</div>
										
										
										<div class="col-md-4">
											<label for="datanascimento" class="form-label">Data De Nascimento</label>
											 <input type="date" class="form-control"
												id="datanascimento" name="datanascimento" value="${usuario.dataNascimento }">
										</div>


                                       
										<div class="col-md-6">
											<label for="email" class="form-label">Seu E-meail</label> <input
												value="${usuario.email }" type="email" class="form-control"
												id="email" name="email" placeholder="Digite Aqui Seu Email">
										</div>


										<div class="col-md-6">
											<label for="senha" class="form-label">Sua Senha</label> <input
												value="${usuario.senha }" type="text"
												class="form-control" id="senha" name="senha"
												placeholder="Digite Aqui Sua Senha">
										</div>

									</div>

									<div class="col-12">
										<button type="submit" class="btn btn-primary">Salvar</button>
										<button onclick="deletarUsuario()" id="btn-deletar"
											type="button" class="btn btn-primary">Deletar</button>
										<button id="btn-limpar" onclick="limpaForm()" type="button"
											class="btn btn-primary">Limpar</button>
										<button type="button" class="btn btn-primary"
											data-bs-toggle="modal" data-bs-target="#exampleModal">Pesquisar
											Usuario</button>

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
		function deletarUsuario() {
			var id = $("#id").val();
			if (id === null || id === '') {

				alert("Campos Id Obrigratorio")
			} else {
				if (window.confirm("Deseja Realmente Deletar Esse Usuario")) {
					$.ajax({
						method : 'get',
						url : 'deletar?idUsuario=' + id,
						dataType : 'text',
						success : function(resposta, textStatus) {
							alert(resposta)
							limpaForm()
						}
					}).fail(function(xhr) {
						alert(xhr.responseText, "Erro")
					});
				} else {
					alert("Operacao Abortada")
				}
			}
		}

		function limpaForm() {
			var form = document.getElementById("formUser").elements;
			for (var i = 0; i < form.length; i++) {
				form[i].value = null;
			}
		}

		function pesquisaUsuario() {
			var nome = $("#nomeUser").val();

			if (nome == '' || nome == null) {

				alert("Campos obrigatorio")
			}

			else {
				$
						.ajax(
								{
									method : 'get',
									url : 'pesquisar?nomeUsuario=' + nome,
									success : function(resposta) {
										var objeto = JSON.parse(resposta);

										if (objeto.length === 0) {

											document.getElementById("total").innerHTML = " Total De Registros Encotrado "
													+ objeto.length

											alert("Nada Foi Encontrado");
											$("#tabela > tbody  > tr").remove()

										} else {
											$("#tabela > tbody  > tr ")
													.remove()
											for (var index = 0; index < objeto.length; index++) {
												$('#tabela > tbody')
														.append(
																'<tr><td>'
																		+ objeto[index].id
																		+ '</td> <td>'
																		+ objeto[index].nome
																		+ '</td>  <td>'
																		+ objeto[index].email
																		+ '</td> <td> <button type="button" class="btn btn-success" onclick="editar('
																		+ objeto[index].id
																		+ ')"> editar </button> </td> </tr>')

											}

											document.getElementById("total").innerHTML = " Total De Registros Encotrado "
													+ objeto.length

										}
									}

								}).fail(function(xhr) {
							alert(xhr.responseText)
						});
			}

		}

		function editar(id) {
           window.location.href="editarusuario?idUser=" + id;
		}
	</script>





</body>
<jsp:include page="script.jsp"></jsp:include>

</html>