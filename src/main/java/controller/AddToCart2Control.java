package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import controller.DeleteFeedBackInLstControl.JsonResponse;
import database.ProductDao;
import model.ListOrderDetails;
import model.ListOrderDetailsItem;
import model.OrderDetails;
import model.Orders;
import model.Product;
import model.User;

/**
 * Servlet implementation class AddToCart2Control
 */
@WebServlet("/add-to-cart2")
public class AddToCart2Control extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCart2Control() {
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
		try {
			HttpSession session = request.getSession(false);
			User user = (User) session.getAttribute("khachHang");
			boolean success = false;
			int slsp = 0;
			if (user != null) {
				ListOrderDetailsItem listItem = (ListOrderDetailsItem) session.getAttribute("listItem");
				ListOrderDetails listOrder = (ListOrderDetails) session.getAttribute("cart");
				ProductDao proDAO = new ProductDao();
				String id = request.getParameter("productID");
				Product pro = proDAO.selectProByID(id);
				String orderDetailsID = getOrderDetailsIDCur(listItem);
				String next = xuLy(orderDetailsID);
				Orders order = listOrder.getMap().keySet().iterator().next();
				OrderDetails orderDetails = new OrderDetails(next, 1, pro, order,
						Double.valueOf(xuLyChuoi(pro.getPrice())));
				listItem.addItem(orderDetails);
				listOrder.getMap().replace(order, listItem);
				session.setAttribute("listItem", listItem);
				session.setAttribute("cart", listOrder);
				success = true;
				slsp = listItem.getList().size();
				JsonResponse jsonResponse = new JsonResponse(success, slsp);
				PrintWriter out = response.getWriter();
				Gson gson = new Gson();
				out.print(gson.toJson(jsonResponse));
				out.flush();
			} else {
				

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public class JsonResponse {
		public boolean success;
		public int soluongSP;

		public JsonResponse(boolean success, int soLg) {
			this.success = success;
			this.soluongSP = soLg;
		}

		// Getters và Setters
		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public int getSoluongSP() {
			return soluongSP;
		}

		public void setSoluongSP(int soluongSP) {
			this.soluongSP = soluongSP;
		}

	}

	private String getOrderDetailsIDCur(ListOrderDetailsItem listItem2) {
		// TODO Auto-generated method stub
		return listItem2.getList().get(listItem2.getList().size() - 1).getOrderDetailsID();
	}

	private String xuLy(String orderIDCurrent) {
		// TODO Auto-generated method stub
		String ans = "";
		boolean mo = false;
		for (int i = 0; i < orderIDCurrent.length(); i++) {
			if (orderIDCurrent.charAt(i) != '0' || mo) {
				ans += orderIDCurrent.charAt(i);
				mo = true;
			}
		}
		System.out.println(ans + "số là số");
		int ansInt = Integer.parseInt(ans);
		System.out.println(ansInt + "labubu");
		int ansIntNext = ansInt + 1;
		String result = "";
		if (ansIntNext < 10) {
			result = "000" + ansIntNext;
		} else if (ansIntNext < 100) {
			result = "00" + ansIntNext;
		} else if (ansIntNext < 1000) {
			result = "0" + ansIntNext;
		} else {
			result = String.valueOf(ansIntNext);
		}
		return result;
	}

	private String xuLyChuoi(String price) {
		// TODO Auto-generated method stub
		String ans = "";
		for (int i = 0; i < price.length(); i++) {
			if (price.charAt(i) != '.') {
				ans += price.charAt(i);
			}
		}
		return ans;
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
