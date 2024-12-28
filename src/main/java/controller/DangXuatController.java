package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ListOrderDetails;
import model.ListOrderDetailsItem;
import model.User;

/**
 * Servlet implementation class DangXuatController
 */
@WebServlet("/dang-xuat")
public class DangXuatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DangXuatController() {
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
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("admin");
		if (user != null) {
			session.invalidate();
			System.out.println("huyphien");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login-form.jsp");
			rd.forward(request, response);
		} else {

			Object obj = session.getAttribute("khachHang");
			if (obj != null) {
				ListOrderDetailsItem li = (ListOrderDetailsItem) session.getAttribute("listItem");
				if (li != null) {
					li.getList().clear();
				}
				ListOrderDetails li1 = (ListOrderDetails) session.getAttribute("cart");
				if (li1 != null) {
					li1.getMap().clear();
				}
				session.invalidate();
			}

			System.out.println("huyphien");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoadDataMain");
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
