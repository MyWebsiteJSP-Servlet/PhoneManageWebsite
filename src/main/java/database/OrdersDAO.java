package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Orders;
import model.Product;

public class OrdersDAO implements DAOInterface<Orders> {

	@Override
	public ArrayList<Orders> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Orders> res = new ArrayList<Orders>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM orders";
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			UserDao userDAO = new UserDao();
			while(rs.next()) {
				String ordersID = rs.getString("ordersID");
				Date date = rs.getDate("ordersDate");
				String userID = rs.getString("userID");
				String status = rs.getString("status");
				double totalAmount = rs.getDouble("totalAmount");
				String shippingAddress = rs.getString("shippingAddress");
				String phone = rs.getString("phone");
				Orders orders = new Orders(ordersID, date, userDAO.selectUserById(userID), status, totalAmount, shippingAddress, phone);
				res.add(orders);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Orders selectById(Orders t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Orders t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertAll(ArrayList<Orders> arr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Orders t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<Orders> arr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Orders t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getOrderIDCurrent() {
		// TODO Auto-generated method stub
		String ans = "";
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM orders ORDER BY ordersID DESC LIMIT 1";
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			UserDao userDAO = new UserDao();
			while(rs.next()) {
				String ordersID = rs.getString("ordersID");
//				Date date = rs.getDate("ordersDate");
//				String userID = rs.getString("userID");
//				String status = rs.getString("status");
//				double totalAmount = rs.getDouble("totalAmount");
//				String shippingAddress = rs.getString("shippingAddress");
//				String phone = rs.getString("phone");
	//			Orders orders = new Orders(ordersID, date, userDAO.selectUserById(userID), status, totalAmount, shippingAddress, phone);
				ans = ordersID;
				break;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ans;
		
	}

	public Orders selectOrderByID(String orderID) {
		// TODO Auto-generated method stub
		Orders orders = null;
		try {
			ArrayList<Orders> lst = selectAll();
			for (Orders orders2 : lst) {
				if(orders2.getOrderID().equalsIgnoreCase(orderID)) {
					orders = orders2;
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return orders;
	}

	public int insertOrderInDB(Orders order, double totalAmount, String phone, String diaChi) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO orders (ordersID, ordersDate, userID, status, totalAmount, shippingAddress, phone) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, order.getOrderID());
			stm.setDate(2, order.getOrdersDate());
			stm.setString(3, order.getUser().getUserID());
			stm.setString(4, order.getStatus());
			stm.setDouble(5, totalAmount);
			stm.setString(6, diaChi);
			stm.setString(7, phone);
			res = stm.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Orders> getListOrdersByPage(String userID, String page) {
		// TODO Auto-generated method stub
		ArrayList<Orders> ans = new ArrayList<Orders>();
		try {
			ArrayList<Orders> lst = selectAll();
			ArrayList<Orders> lstRevert = new ArrayList<Orders>();
			ArrayList<Orders> lstReal = new ArrayList<Orders>();
			for (int i = lst.size() - 1; i >= 0; i--) {
				lstRevert.add(lst.get(i));
			}
			for (Orders orders : lstRevert) {
				if(orders.getUser().getUserID().equalsIgnoreCase(userID.trim())) {
					lstReal.add(orders);
				}
			}
			int end = Integer.parseInt(page) * 4;
			int start = end - 4;
			int count = 0;
			for (int i = start; i < lstReal.size(); i++) {
				ans.add(lstRevert.get(i));
				count++;
				if(count == 4) {
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ans;
	}

	public boolean kiemTraUserIsOrder(String userID) {
		// TODO Auto-generated method stub
		boolean res = false;
		try {
			ArrayList<Orders> lst = selectAll();
			for (Orders orders : lst) {
				if(orders.getUser().getUserID().equalsIgnoreCase(userID.trim())) {
					res = true;
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

	public int getTongSoOrder(String userID) {
		// TODO Auto-generated method stub
		int ans = 0;
		try {
			ArrayList<Orders> lst = selectAll();
			for (Orders orders : lst) {
				if(orders.getUser().getUserID().equalsIgnoreCase(userID.trim())) {
					ans++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ans;
	}

	public Orders getOrdersByID(String orderID) {
		// TODO Auto-generated method stub
		Orders or = null;
		try {
			ArrayList<Orders> lst = selectAll();
			for (Orders orders : lst) {
				if(orders.getOrderID().equalsIgnoreCase(orderID.trim())) {
					or = orders;
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return or;
	}

	public int updateStatus(Orders order) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE orders SET status = ? WHERE ordersID =?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, order.getStatus());
			stm.setString(2, order.getOrderID());
			res = stm.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Orders> getListOrdersCancel() {
		// TODO Auto-generated method stub
	    ArrayList<Orders> ans = new ArrayList<Orders>();
	    try {
			ArrayList<Orders> lst = selectAll();
			for (Orders orders : lst) {
				if(orders.getStatus().equalsIgnoreCase("đã hủy")) {
					ans.add(orders);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	    return ans;
	}

	
}
