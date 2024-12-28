package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.ProductDao;
import database.ProductFavoriteDAO;
import model.Product;
import model.ProductFavorite;
import model.User;

/**
 * Servlet implementation class AddProductListFavorite
 */
@WebServlet("/add-product-list-favorite")
public class AddProductListFavorite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductListFavorite() {
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
		if (session != null) {
			System.out.println("bedebede");
			User user = (User) session.getAttribute("khachHang");
			String baoLoi = "";
			if (user != null) {
				System.out.println("alaalala");
				String productID = request.getParameter("productID");
				ProductFavoriteDAO proFavoriteDao = new ProductFavoriteDAO();
				boolean check = proFavoriteDao.selectProductFavorite(productID, user.getUserID());
				boolean check2 = false;
				if (check) {
					baoLoi = "Sản phẩm đã tồn tại trong danh mục yêu thích của bạn rồi nha";
					request.setAttribute("baoLoi", baoLoi);
					check2 = true;
					ProductDao proDao = new ProductDao();
					List<Product> lstPro1 = proDao.getProductMain();
					List<Product> lstPro2 = proDao.getProductMain();
					List<Product> lstPro3 = proDao.getProductMain();
					ProductFavoriteDAO productFaDao = new ProductFavoriteDAO();
					List<ProductFavorite> lstProductFavoriteDao = productFaDao.getLstProFavorite(user.getUserID());
					request.setAttribute("soLuongSanPhamLike", lstProductFavoriteDao.size());
					request.setAttribute("danhSachMain1", lstPro1);
					request.setAttribute("danhSachMain2", lstPro2);
					request.setAttribute("danhSachMain3", lstPro3);
					request.setAttribute("baoLoi2", check2);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
					rd.forward(request, response);
				} else {
					String productFavoriteID = proFavoriteDao.selectSttLastNext();
					if (proFavoriteDao.insertProduct(productID, user.getUserID(), productFavoriteID) > 0) {
						request.setAttribute("userID", user.getUserID());
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/load-page-favorite-list");
						rd.forward(request, response);
					}

				}

			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/noAccount.jsp");
				rd.forward(request, response);
			}
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/noAccount.jsp");
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
