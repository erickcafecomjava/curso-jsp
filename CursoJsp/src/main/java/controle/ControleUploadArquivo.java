package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UploadPdf
 */
@WebServlet("/UploadPdf")
public class ControleUploadArquivo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControleUploadArquivo() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("menssagem", "desculpa essa tarefa do sistema esta em faze de desenvolvimento");
		request.getRequestDispatcher("principal/uploadfile.jsp").forward(request, response);
	}

}
