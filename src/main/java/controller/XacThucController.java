package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UserDao;
import model.User;

/**
 * Servlet implementation class XacThucController
 */
@WebServlet("/xac-thuc")
public class XacThucController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XacThucController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String maKhachHang = request.getParameter("maKhachHang");
			String maXacThuc = request.getParameter("maXacThuc");
			UserDao userDao = new UserDao();
			User user = new User();
			user.setUserID(maKhachHang);
			User us = userDao.selectById(user);
			String msg = "";
			boolean xacThuc = false;
			if(us != null) {
				// kiểm tra xem mã xác thực có giống nhau hay không
				if(us.getAuthenticationCode().equals(maXacThuc)) {
					// Thành công
					us.setStatus(1);
					userDao.updateVertifyInformation(us);
					msg = "Xác thực thành công!";
					xacThuc = true;
				}else {
					msg = "Xác thực không thành công";
					xacThuc = false;
				}
			}else {
				msg = "Tài khoản không tồn tại";
			}
			String url = "signup-form.jsp";
			request.setAttribute("baoLoi", msg);
			request.setAttribute("xacThuc", xacThuc);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
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
