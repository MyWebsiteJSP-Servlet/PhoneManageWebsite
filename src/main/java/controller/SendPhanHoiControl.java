package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.CustomerFeedBackDAO;
import model.CustomerFeedback;

/**
 * Servlet implementation class SendPhanHoiControl
 */
@WebServlet("/gui-phan-hoi-khach-hang")
public class SendPhanHoiControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendPhanHoiControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			String feedbackID = request.getParameter("feedbackID");
			String text = request.getParameter("feedback");
			CustomerFeedBackDAO dao = new CustomerFeedBackDAO();
			CustomerFeedback cusFB = dao.selectByIDFB(feedbackID);
			cusFB.setMessageAdmin(text);
			if(dao.update(cusFB) > 0) {
				ArrayList<CustomerFeedback> lstALL = dao.selectAll();
				request.setAttribute("danhSachFB", lstALL);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/feedback.jsp");
				rd.forward(request, response);
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
