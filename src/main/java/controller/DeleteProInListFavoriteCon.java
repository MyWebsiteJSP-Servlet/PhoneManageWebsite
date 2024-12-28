package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.ProductFavoriteDAO;
import model.ProductFavorite;
import model.User;

/**
 * Servlet implementation class DeleteProInListFavoriteCon
 */
@WebServlet("/delete-product-in-list-favorite")
public class DeleteProInListFavoriteCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProInListFavoriteCon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		HttpSession session = request.getSession(false);
		List<ProductFavorite> ans = new ArrayList<ProductFavorite>();
		if(session != null) {
			User user = (User) session.getAttribute("khachHang");
			if(user != null) {
				ProductFavoriteDAO proFaDao = new ProductFavoriteDAO();
				String productID = request.getParameter("productID");
				if(proFaDao.deleteProFavoriteInList(productID, user.getUserID()) > 0) {
					List<ProductFavorite> lstProductFavoriteDao = proFaDao.getLstProFavorite(user.getUserID());
					request.setAttribute("soLuongSanPhamLike", lstProductFavoriteDao.size());
					List<ProductFavorite> lst = proFaDao.getLstProFavorite(user.getUserID());
					request.setAttribute("danhSach", lst);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/favorite-list.jsp");
					rd.forward(request, response);
				}
			}
		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
