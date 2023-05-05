package filtro.autenticacao.usuario;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter(urlPatterns = { "/principal/*" })
public class FilterAutenticacao implements Filter {

	public FilterAutenticacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String user = (String) req.getSession().getAttribute("dados");
		String url = req.getServletPath();
		if (user == null || (user != null && user.isEmpty() && !url.equals("/logar"))) {
			req.setAttribute("menssagem", "voce nao tem permissao ");
			req.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {

			chain.doFilter(request, response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	
	}

}
