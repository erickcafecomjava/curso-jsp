


package controle;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;

import model.Usuario;
import repository.entity.RepositoryUsuarioDao;
import utilitario.Generic;

@MultipartConfig
@WebServlet(urlPatterns = { "/salvar", "/deletar", "/pesquisar", "/editarusuario" })
public class ControleUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RepositoryUsuarioDao dao = new RepositoryUsuarioDao();

	public ControleUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getServletPath();
			Usuario usuario = new Usuario();
			if (acao.equals("/deletar")) {
				usuario.setId(Integer.parseInt(request.getParameter("idUsuario")));
				usuario = dao.deletar(usuario);
				if (usuario.isNull()) {
					response.getWriter().write("Usuario    deletado Com Sucesso");
				} else {
					response.getWriter().write("Algo deu errado na hora de deletar ");
				}
			} else if (acao.equals("/pesquisar")) {
				String nome = request.getParameter("nomeUsuario");

				List<Usuario> list = dao.pesquisar(nome, new Generic().idUsuarioResponssavel(request));
				Gson gson = new Gson();
				String formtJason = gson.toJson(list);
				response.getWriter().write(formtJason);

			} else if (acao.equals("/editarusuario")) {

				Integer id = Integer.parseInt(request.getParameter("idUser"));
				usuario = dao.pesquisarPorIdentificador(id);
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("menssagem", e.getMessage());
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			String menssagem = "";
			String destino = "";
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String dataNascimento = request.getParameter("datanascimento");
			Usuario usuario;
			if (nome != null && !nome.isEmpty() && email != null && !email.isEmpty() & senha != null && !senha.isEmpty()
					&& dataNascimento != null && !dataNascimento.isEmpty()) {
				usuario = new Usuario();

				usuario.setNome(nome);
				usuario.setEmail(email);
				usuario.setSenha(senha);
				usuario.setDataNascimento(
						new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(dataNascimento).getTime()));
				usuario.setId(request.getParameter("id") != null && !request.getParameter("id").isEmpty()
						? Integer.parseInt(request.getParameter("id"))
						: null);
				usuario.setPermissao(false);
				Part part = request.getPart("foto");

				if (part.getSize() > 0) {
					byte[] fotoBynario = part.getInputStream().readAllBytes();
					String fotoBase64 = "data:" + part.getContentType() + ";base64,"
							+ new Base64().encodeAsString(fotoBynario);
					usuario.setFoto(fotoBase64);
				}

				if (dao.verificaUsuarioExiste(usuario) && usuario.usuarioNovo()) {
					menssagem = " Usuario E-mail e Senha Ja existe";
				} else {
					if (usuario.usuarioNovo()) {
						menssagem = "Dados Enviado Com Sucesso";
					} else {
						menssagem = "Seus Dados Foi Atualizado Com Sucesso";
					}
					usuario = dao.salvar(usuario, new Generic().idUsuarioResponssavel(request));
				}

				request.setAttribute("usuario", usuario);

			} else {
				menssagem = "Campos Obrigatorios";

			}

			request.setAttribute("menssagem", menssagem);
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("menssagem", e.getMessage());
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		} catch (ParseException e) {
			// TODO: handle exception
			e.printStackTrace();

			request.setAttribute("menssagem", " ERRO FORMATO DA DATA INCORRETO");
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		}
	}

}
