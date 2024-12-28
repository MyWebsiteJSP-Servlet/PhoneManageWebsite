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
import database.UserDao;
import model.ListOrderDetailsItem;
import model.Product;
import model.User;
import util.MaHoa;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login-Servlet")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("password");
		String url = "";
		String notify = "";
		String error = "";
		String passWordMaHoa = MaHoa.toSHA1(passWord);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println(passWordMaHoa);
		User user = new User();
		user.setUserName(userName);
		user.setPassWord(passWordMaHoa);
		UserDao userDao = new UserDao();
		ProductFavoriteDAO proFaDao = new ProductFavoriteDAO();
		User us = userDao.selectByUserNameAndPassWord(user);
		if (us != null) {
			if (us.getRole().getRoleID() == 2) {
				if (us.getStatus() == 1) {
					if (us.getIsKey().equalsIgnoreCase("Hoạt động")) {
						HttpSession session = request.getSession();
						session.setAttribute("khachHang", us);
						User user2 = (User) session.getAttribute("khachHang");
						url = "/index.jsp";
						int soLuongSanPhamLike = proFaDao.getSoLuong(user2.getUserID().trim());
						ListOrderDetailsItem li = (ListOrderDetailsItem) session.getAttribute("listItem");
						String slSP = "";
						if (li != null) {
							slSP = li.getList().size() + "";
							slSP = (slSP == "0") ? "0" : slSP;
						} else {
							slSP = "0";
						}
						ProductDao proDao = new ProductDao();
						List<Product> lstPro1 = proDao.getProductMain();
						List<Product> lstPro2 = proDao.getProductMain();
						List<Product> lstPro3 = proDao.getProductMain();
						request.setAttribute("uri", "/LoadDataMain");
						request.setAttribute("soLuongSP", slSP);
						request.setAttribute("danhSachMain1", lstPro1);
						request.setAttribute("danhSachMain2", lstPro2);
						request.setAttribute("danhSachMain3", lstPro3);
						System.out.println("ok");
						request.setAttribute("sourceServlet", "loginController");
						request.setAttribute("soLuongSanPhamLike", soLuongSanPhamLike);
						RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
						rd.forward(request, response);
					} else {
						url = "/login-form.jsp";
						notify = "Tài khoản của bạn đang bị khóa.Liên hệ Admin để biết thêm chi tiết";
						error = "taikhoanbikhoa";
						request.setAttribute("sourceServlet", "loginController");
						request.setAttribute("error", error);
						request.setAttribute("thongBao", notify);
						RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
						rd.forward(request, response);
					}
				} else {
					url = "/login-form.jsp";
					error = "taiKhoanChuaXacNhan";
					notify = "Bạn chưa xác thực tài khoản. Vui lòng xác thực tài khoản qua email đế tiếp tục đăng nhập";
					request.setAttribute("sourceServlet", "loginController");
					request.setAttribute("error", error);
					request.setAttribute("thongBao", notify);
					RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
					rd.forward(request, response);
				}
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("admin", us);
				url = "/Admin/admin.jsp";
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
				rd.forward(request, response);
			}
		} else {
			url = "/login-form.jsp";
			notify = "Tên đăng nhập hoặc mật khẩu không chính xác";
			error = "errorUserNameOrPass";
			request.setAttribute("sourceServlet", "loginController");
			request.setAttribute("error", error);
			request.setAttribute("thongBao", notify);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

}
