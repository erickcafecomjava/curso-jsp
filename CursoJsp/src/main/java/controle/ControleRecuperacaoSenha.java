package controle;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import erro.logger.ErroEmailNotExiste;
import model.Usuario;
import repository.entity.RepositoryUsuarioDao;
import services.EmailServices;
import utilitario.GeradorSenha;

@WebServlet(urlPatterns = { "/recuperasenha" })
public class ControleRecuperacaoSenha extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RepositoryUsuarioDao dao = new RepositoryUsuarioDao();

	public ControleRecuperacaoSenha() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String acao = req.getServletPath();
			String resposta = "";

			if (acao.equals("/recuperasenha")) {
				String email = req.getParameter("emailUsuario");
				Usuario user = dao.existeEmail(email);

				if (user.isNull()) {
					throw new ErroEmailNotExiste();
				} else {

					String novaSenhaGerada = GeradorSenha.gerarSenha();
					user.setSenha(novaSenhaGerada);
					user = dao.atualisarSenha(user);
					Thread thread = new Thread(new EmailServices(email, user));
					thread.start();
					resposta = "Sua senha foi redefinida verifique seu email olhe na sua caixa de entrada  ou no  span  ";
				}

				resp.getWriter().write(resposta);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			resp.getWriter().write(e.getMessage());

		} catch (ErroEmailNotExiste e) {
			e.printStackTrace();
			resp.getWriter().write(e.getMessage());
		}
	}

}
