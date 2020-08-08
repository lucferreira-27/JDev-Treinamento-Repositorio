package jdev01.servelet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdev01.bean.Usuario;
import jdev01.dao.DaoUsuario;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/salvarUsuario")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoUsuario daoUsuario = new DaoUsuario();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String acao = request.getParameter("acao");
		String id = request.getParameter("id");
		System.out.println(acao);
		try {
			if (acao.equals("delete")) {
				daoUsuario.delete(id);

				RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");

				request.setAttribute("usuarios", daoUsuario.listar());

				view.forward(request, response);

			} else if (acao.equals("editar")) {
				Usuario beanCursoJsp = daoUsuario.consultar(id);
				RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");

				request.setAttribute("user", beanCursoJsp);

				view.forward(request, response);
			} else if (acao.equals("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");

				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String acao = request.getParameter("acao");
		if (acao != null && acao.equals("reset")) {
			RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");
			try {
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			
			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String nome = request.getParameter("nome");
			String fone = request.getParameter("fone");
			System.out.println(nome);
			System.out.println(fone);
			Usuario usuario = new Usuario();
			try {
			usuario.setId(!id.isEmpty() ? Integer.parseInt(id) : 0);
			usuario.setLogin(login);
			usuario.setSenha(password);
			usuario.setNome(nome);
			usuario.setFone(fone);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			String test = "ts";
			try {
				boolean permitirInserir = false;
				if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					request.setAttribute("userExiste", true);
					request.setAttribute("msg", test);
				}
				if ((id == null || id.isEmpty()) && daoUsuario.validarLogin(login)) {
					permitirInserir = true;
					daoUsuario.salvar(usuario);
					request.setAttribute("msg", "Usuário salvo com sucesso!");

				} else if (id != null || id.isEmpty()) {
					if (!daoUsuario.validarLoginUpdate(login, id)) {
						request.setAttribute("msg", "Login já existe");

					} else {
						daoUsuario.atualizar(usuario);
						permitirInserir = true;
						request.setAttribute("msg", "Usuário atualizado com sucesso!");
					}
				}
				if(!permitirInserir)
					request.setAttribute("user", usuario);

				

				RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");

				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	

}
