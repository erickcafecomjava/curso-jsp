<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"></jsp:include>

<body>
	<div class="wrapper">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<div class="main">
			<jsp:include page="navbar.jsp"></jsp:include>

			<main class="content">
				<div class="container-fluid p-0">
					<div class="content-table">
						<div class="content-seach">
							<div class="header-title">Pesquisar Livro</div>
							<br> 
							
							 <h4 style="color: red;">
                        ESTA EM FASE DE IMPLEMENTACAO DECULPE-ME EM BREVE ESTARA PRONTO
                       </h4>
							
							<c:if test="${erro !=null }">
							<div id="menssagem"	class="alert alert-danger alert-dismissible fade show"	role="alert">
								<strong> Menssagem ?</strong>${erro }
								<button type="button" class="btn-close" data-bs-dismiss="alert"	 	aria-label="Close"></button>
							</div>
						    </c:if>

                                <br>
                                <form action="livro" method="get">
                                <input name="url" type="hidden" value="pesquisarlivropornome">
								<div class="input-group mb-3"><button class="btn btn-success" type="submit"id="button-addon1">Buscar Livro</button>
									<input type="text" name="livroPesquisado" class="form-control"	placeholder="informe o nome do livro "	aria-label="Example text with button addon"	aria-describedby="button-addon1">
								</div>

							</form>
								
						</div>
                        <div>

                           <div style="overflow: scroll; width: 100% ; ">
                           
                           
                          
							<table class="table">
								<thead>
									<tr>
										<th scope="col">Identificador</th>
										<th scope="col">Nome Do Livro</th>
										<th scope="col">Ano De Publicacao</th>
										
									</tr>
								</thead>
								<tbody>
								       <c:forEach var="itens" items="${livros }">
                                        <tr>
                                           
                                            <td><c:out value="${itens.idLivro }"></c:out></td>
                                            <td><c:out value="${itens.nomeLivro }"></c:out></td>
                                            <td><c:out value="${itens.dataPublicacao }"></c:out></td>
                                            <td>  <a href="<%=request.getContextPath() %>/editarlivro?livroId=${itens.idLivro}" class="btn btn-success active" aria-current="page">Editar</a> </td>
                                            <td>  <a   href="<%=request.getContextPath() %>/cadastrardocumento?livroId=${itens.idLivro}" class="btn btn-danger active" aria-current="page">Upload Documento</a> </td>
                                            
                                        </tr>
								       
								       </c:forEach>
								</tbody>
							</table>

								<nav style="display: flex; justify-content: center; " aria-label="Page navigation example">
								<ul class="pagination">
									<li class="page-item"><a class="page-link" href="javascript:history.back()">voltar</a></li>
								<% 
								 Integer totalPaginas =(Integer)request.getAttribute("total");
								 String contexto = request.getContextPath();
								 for(int posicao = 0 ; posicao < totalPaginas ; posicao++){
									 
								      String url = contexto+"/livro?url=paginacao&numeroPagina="+ (posicao) * 5;
									  out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"\">"+(posicao + 1)+"</a></li>");
								 }
								%>
									<li class="page-item"><a class="page-link" href="javascript:window.history.back()">proximo</a></li>
								
									</ul>
								</nav>
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

     
     
    

	<jsp:include page="script.jsp"></jsp:include>
	</body>