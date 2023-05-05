
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<nav id="sidebar" class="sidebar js-sidebar">
	<div class="sidebar-content js-simplebar">
		<a class="sidebar-brand" href="index.html"> <span
			class="align-middle">AdminKit</span>
		</a>

		<ul class="sidebar-nav">
        <c:if test="${dados.permissao }">
	        <li class="sidebar-item active"><a class="sidebar-link" href="#"><i class="align-middle" data-feather="sliders"></i> <span class="align-middle">Area Administrativa</span></a>
			<li class="sidebar-item"><a class="sidebar-link"	href="<%=request.getContextPath() %>/novousuario"> <i class="align-middle"		data-feather="user"></i> <span class="align-middle">Cadastrar Usuario</span></a></li>
			<li class="sidebar-item"><a class="sidebar-link"	href="<%=request.getContextPath() %>/novolivro"> <i class="align-middle"		data-feather="log-in"></i> <span class="align-middle">Cadastrar Livro</span></a></li>
			<li class="sidebar-item"><a class="sidebar-link"	href="<%=request.getContextPath() %>/pesquisarlivro"> <i class="align-middle"		data-feather="log-in"></i> <span class="align-middle">Pesquisar Livro</span></a></li>
			<li class="sidebar-item"><a class="sidebar-link"	href="<%=request.getContextPath() %>/relatorio"> <i class="align-middle"		data-feather="log-in"></i> <span class="align-middle">Relatorio</span></a></li>
			<li class="sidebar-item"><a class="sidebar-link"	href="<%=request.getContextPath() %>/biblioteca"> <i class="align-middle"		data-feather="log-in"></i> <span class="align-middle">Bibliteca</span></a></li>
			
			<hr>
		</c:if>
			<li class="sidebar-item active"><a class="sidebar-link" href="#"><i class="align-middle" data-feather="sliders"></i> <span class="align-middle">Area Do Usuario</span></a>
			<li class="sidebar-item"><a class="sidebar-link"	href="#"> <i class="align-middle"		data-feather="user"></i> <span class="align-middle">Editar Meus Dados</span></a></li>
			<li class="sidebar-item"><a class="sidebar-link"	href="#"> <i class="align-middle"		data-feather="log-in"></i> <span class="align-middle">Biblioteca</span></a></li>
			<li class="sidebar-item"><a class="sidebar-link"	href="#"> <i class="align-middle"		data-feather="log-in"></i> <span class="align-middle">Meus Favoritos</span></a></li>
		    <hr>
			
           <li class="sidebar-item"><a class="sidebar-link"	href="voltar"> <i class="align-middle"		data-feather="log-in"></i> <span class="align-middle">voltar</span></a></li>
			<li class="sidebar-item"><a class="sidebar-link"	href="deslogar"> <i class="align-middle"		data-feather="log-in"></i> <span class="align-middle">sair</span></a></li>

			
		</ul>
		
		

	</div>
</nav>