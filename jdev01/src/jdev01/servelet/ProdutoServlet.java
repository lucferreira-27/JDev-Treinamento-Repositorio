package jdev01.servelet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdev01.bean.Produto;
import jdev01.bean.Usuario;
import jdev01.dao.DaoProdutos;

/**
 * Servlet implementation class ProdutoServlet
 */
@WebServlet("/salvarProduto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoProdutos daoProdutos = new DaoProdutos();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProdutoServlet() {
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
				daoProdutos.delete(id);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroprodutos.jsp");

				request.setAttribute("produtos", daoProdutos.listar());

				view.forward(request, response);

			} else if (acao.equals("editar")) {
				Produto produto = daoProdutos.consultar(id);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroprodutos.jsp");

				request.setAttribute("prod", produto);

				view.forward(request, response);
			} else if (acao.equals("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroprodutos.jsp");

				request.setAttribute("produtos", daoProdutos.listar());
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
			RequestDispatcher view = request.getRequestDispatcher("/cadastroprodutos.jsp");
			try {
				request.setAttribute("produtos", daoProdutos.listar());
				view.forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {

			String id = request.getParameter("id");
			if(id == null)
				id = "0";
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");
			Produto produto = new Produto();

			try {
			produto.setId(!id.isEmpty() ? Integer.parseInt(id) : 0);
			produto.setNome(nome);
			produto.setQuantidade(Integer.parseInt(quantidade));
			produto.setValor(Double.parseDouble(valor));
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			try {
				boolean permitirInserir = false;
				if (id == null || id.isEmpty() && !daoProdutos.validarNome(nome)) {

				}
				if ((id == null || id.isEmpty()) && daoProdutos.validarNome(nome)) {
					permitirInserir = true;
					daoProdutos.salvar(produto);
					request.setAttribute("msg", "Produto salvo com sucesso!");

				} else if (id != null || id.isEmpty()) {
					if (!daoProdutos.validarNomeUpdate(nome, id)) {
						request.setAttribute("msg", "Produto já existe");

					} else {
						daoProdutos.atualizar(produto);
						permitirInserir = true;
						request.setAttribute("msg", "Produto atualizado com sucesso!");
					}
				}
				if (!permitirInserir)
					request.setAttribute("prod", produto);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroprodutos.jsp");

				request.setAttribute("produtos", daoProdutos.listar());
				view.forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
