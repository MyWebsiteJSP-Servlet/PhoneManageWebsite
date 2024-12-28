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

import database.ProductFavoriteDAO;
import model.ListOrderDetailsItem;
import model.ProductFavorite;
import model.User;

/**
 * Servlet implementation class ProfiesResetPassWordCon
 */
@WebServlet("/profile-reset-password")
public class ProfiesResetPassWordCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfiesResetPassWordCon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session != null) {
			User user = (User) session.getAttribute("khachHang");
			if(user != null) {
				ProductFavoriteDAO productFaDao = new ProductFavoriteDAO();
				List<ProductFavorite> lstProductFavoriteDao = productFaDao.getLstProFavorite(user.getUserID());
				ListOrderDetailsItem li = (ListOrderDetailsItem) session.getAttribute("listItem");
				String slSP = "";
				if (li != null) {
					slSP = li.getList().size() + "";
					slSP = (slSP.equals("0")) ? "0" : slSP;
				} else {
					slSP = "0";
				}
				request.setAttribute("soLuongSP", slSP);
				request.setAttribute("soLuongSanPhamLike", lstProductFavoriteDao.size());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile-reset-password.jsp");
				rd.forward(request, response);
			}
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
