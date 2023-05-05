package services;


import org.apache.commons.mail.HtmlEmail;
import model.Usuario;

public class EmailServices implements Runnable {

	private String emailUser;
	private Usuario senhaUsuario;
	private static final int SMTP_PORT = 587;
	private static final String HOST_NAME = "smtp.gmail.com";
    private static final String AUTHENTICATION_USER = "erickjava2015@gmail.com";
    private static final String AUTHENTICATION_PASSWORD = "ivkwqugjvlqpoxvy";
	private static final String DESTINATARIO = "rickjava2015@gmail.com";

	public EmailServices(String email, Usuario senhaUsuario) {
		// TODO Auto-generated constructor stub
		this.senhaUsuario = senhaUsuario;
		this.emailUser = email;
	}

	public EmailServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		try {
			StringBuffer msgs = new StringBuffer();
			msgs.append("<html><body>");
			msgs.append("<strong>Ola " + senhaUsuario.getNome() + "</strong>");
			msgs.append("<strong>Sua Nova Senha e <p style='color: blue; text-decoration: underline; ' >  "
					+ senhaUsuario.getSenha() + "</p></strong> ");
			msgs.append("<a style=\"  background-color: #f44336;\r\n" + "  color: white;\r\n"
					+ "  padding: 14px 25px;\r\n" + "  text-align: center;\r\n" + "  text-decoration: none;\r\n"
					+ "  display: inline-block;  \" href=\"http://localhost:8080/MeuEbook//\">Acessa!</a>");

			msgs.append("</body></html>");
			HtmlEmail emailConection = new HtmlEmail();
			
			emailConection.setHostName(HOST_NAME);
			emailConection.setSmtpPort(SMTP_PORT);
			emailConection.setAuthentication(AUTHENTICATION_USER, AUTHENTICATION_PASSWORD);
			emailConection.setSSLOnConnect(true);
			emailConection.setFrom(DESTINATARIO);

			emailConection.setSubject("Redifinicao de Senha ");
			emailConection.setHtmlMsg(msgs.toString());
			emailConection.addTo(emailUser);
			emailConection.send();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
