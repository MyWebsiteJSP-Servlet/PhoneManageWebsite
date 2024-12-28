package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckOTPController
 */
@WebServlet("/check-OTP")
public class CheckOTPController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOTPController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String soNgauNhien = request.getParameter("soNgauNhien");
		System.out.println(soNgauNhien+"\\\\");
		String maOTP = request.getParameter("maOTP");
		System.out.println(maOTP+"\\\\");
		String baoLoi = "";
		if(maOTP.trim().equals(soNgauNhien.trim())) {
			request.setAttribute("sourceServlet", "checkOTPController");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/get-password.jsp");
			rd.forward(request, response);
		}else {
			baoLoi = "Mã OTP bạn nhập không chính xác.\nVui lòng kiểm tra lại tin nhắn hoặc nhấn gửi lại mã OTP";
			request.setAttribute("soNgauNhien", soNgauNhien);
			request.setAttribute("sourceServlet", "checkOTPController");
			request.setAttribute("baoLoi", baoLoi);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/checkOTP.jsp");
			rd.forward(request, response);
		}
	}

}
