<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand navbar-light navbar-bg">
				<a class="sidebar-toggle js-sidebar-toggle">
          <i class="hamburger align-self-center"></i>
        </a>

				<div class="navbar-collapse collapse">
					<ul class="navbar-nav navbar-align">
						
						<li class="nav-item dropdown">
							<a class="nav-icon dropdown-toggle" href="#" id="messagesDropdown" data-bs-toggle="dropdown">
								<div class="position-relative">
									<i class="align-middle" data-feather="message-square"></i>
								</div>
							</a>
							<div class="dropdown-menu dropdown-menu-lg dropdown-menu-end py-0" aria-labelledby="messagesDropdown">
							
								<div class="dropdown-menu-footer">
									<a href="#" class="text-muted"></a>
								</div>
							</div>
						</li>
						<li class="nav-item dropdown">
							<a class="nav-icon dropdown-toggle d-inline-block d-sm-none" href="#" data-bs-toggle="dropdown"><i class="align-middle" data-feather="settings"></i></a>
						   <c:if test="${dados.foto!= null }">
							   <a  class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#" data-bs-toggle="dropdown"><img  src="${dados.foto }" class="avatar img-fluid rounded me-1" alt="Charles Hall" /> <span class="text-dark">Bem Vindo : ${dados.nome } </span></a>	
							</c:if>	
							
							<c:if test="${dados.foto == null }">
							   <a  class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#" data-bs-toggle="dropdown"><img  src="img/avatars/avatar.jpg" class="avatar img-fluid rounded me-1" alt="Charles Hall" /> <span class="text-dark">Bem Vindo : ${dados.nome }</span></a>	
							</c:if>	
							 
                           </a>
							
							
							
						</li>
					</ul>
				</div>
			</nav>