package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Roles;
import model.User;

public class UserDao implements DAOInterface<User>{

	@Override
	public ArrayList<User> selectAll() {
		// TODO Auto-generated method stub
	   ArrayList<User> answer = new ArrayList<User>();
	   try {
		// B1: Tạo kết nối đến CSDL
		   Connection con = JDBCUtil.getConnection();
		// B2: Tạo ra đổi tượng Statment
		   String sql = "SELECT * FROM user";
		   PreparedStatement st = con.prepareStatement(sql);
		// B3: Thực thi câu lênh sql
		   System.out.println(sql);
		   ResultSet rs = st.executeQuery();
		// B4: 
		   while(rs.next()) {
			   String userID = rs.getString("userID");
			   String userName = rs.getString("userName");
			   String passWord = rs.getString("passWord");
			   String email = rs.getString("email");
			   String phone = rs.getString("phoneNumber");
			   int roleID = rs.getInt("roleID");
			   Roles role = new Roles();
			   role.setRoleID(roleID);
			   Roles roles = new RolesDao().selectById(role);
			   Date date = rs.getDate("createAt");
			   String image = rs.getString("image");
			   User user = new User(userID, userName, passWord, email, phone, roles, date, image);
			   answer.add(user);
		   }
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	   return answer;
	}

	@Override
	public User selectById(User t) {
		// TODO Auto-generated method stub
	    User ansWer = null;
	    try {
	    	 Connection con = JDBCUtil.getConnection();
	 	    // Tạo đối tg statment
	 	    String sql = "SELECT * FROM user "+" WHERE userID=?";
	 	    PreparedStatement st = con.prepareStatement(sql);
	 	    st.setString(1, t.getUserID());
	 	    //B3: Thuc thi câu lệnh sql
	 	    System.out.println(sql);
	 	    ResultSet rs = st.executeQuery();
	 	    while(rs.next()) {
	 	    	String userID = rs.getString("userID");
	 	    	String userName = rs.getString("userName");
	 	    	String passWord = rs.getString("passWord");
	 	    	String email = rs.getString("email");
	 	    	String phoneNum = rs.getString("phoneNumber");
	 	    	int roleID = rs.getInt("roleID");
	 	    	Roles roles = new Roles();
	 	    	roles.setRoleID(roleID);
	 	    	Roles role = new RolesDao().selectById(roles);
	 	    	Date date = rs.getDate("createAt");
	 	    	String image = rs.getString("image");
	 	    	ansWer = new User(userID, userName, passWord, email, phoneNum, role, date, image);
	 	    	break;
	 	    }
	 	    // B5 : đóng kết nối
	 	    JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	    return ansWer;
	}

	@Override
	public int insert(User t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			// B1: Tạo kết nối đến Csdl
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO User (userID, userName, passWord, email, phoneNumber, roleID, createAt, authenticationCode, confirmationTime, status, image)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getUserID());
			st.setString(2, t.getUserName());
			st.setString(3, t.getPassWord());
			st.setString(4, t.getEmail());
			st.setString(5, t.getPhoneNumber());
			st.setInt(6, t.getRole().getRoleID());
			st.setDate(7, t.getCreateAt());
			st.setString(8, t.getAuthenticationCode());
			st.setDate(9, t.getConfirmationTime());
			st.setInt(10, t.getStatus());
			st.setString(11, t.getImageAvatar());
			// thực thi câu lệnh
			ketQua = st.executeUpdate();
			// bước 4
			System.out.println("Bạn đã thực thi câu lệnh "+sql);
			System.out.println("Có "+ketQua +" dòng bị thay đổi");
			// đóng kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return ketQua;
	}

	@Override
	public int insertAll(ArrayList<User> arr) {
		// TODO Auto-generated method stub
		int count = 0;
		for (User user : arr) {
			count += this.insert(user);
		}
		return count;
	}

	@Override
	public int delete(User t) {
		// TODO Auto-generated method stub
		// mở kết nối
		int ans = 0;
		try {
		Connection con = JDBCUtil.getConnection();
		String sql = "DELETE * FROM user "+ " WHERE userID=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, t.getUserID());
		//B3 thực thi câu lệnh
		System.out.println(sql);
		ans = st.executeUpdate();
		System.out.println("Bạn đã thực thi câu lệnh "+sql);
		System.out.println("Có "+ans+" dòng bị thay đổi");
		JDBCUtil.closeConnection(con);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ans;
	}
		

	@Override
	public int deleteAll(ArrayList<User> arr) {
		// TODO Auto-generated method stub
		int count = 0;
		for (User user : arr) {
			count += this.delete(user);
		}
		return count;

	}

	@Override
	public int update(User t) {
		// TODO Auto-generated method stub
		int ans = 0;
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ans;
	}

	public boolean kiemTraTenDangNhap(String userName) {
		// TODO Auto-generated method stub
		boolean res = false;
		// kết nối 
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM user WHERE username=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, userName);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				res = true;
				break;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

	public int insert2(User user) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO user (userID, userName, passWord, email, phoneNumber, roleID, dateofbirth, sex, address, createAt, authenticationCode, confirmationTime, status, image)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getUserID());
			st.setString(2, user.getUserName());
			st.setString(3, user.getPassWord());
			st.setString(4, user.getEmail());
			st.setString(5, user.getPhoneNumber());
			st.setInt(6, user.getRole().getRoleID());
			st.setDate(7, user.getDateOfBirth());
			st.setString(8, user.getSex());
			st.setString(9, user.getAddress());
			st.setDate(10, user.getCreateAt());
			st.setString(11, null);
			st.setDate(12, null);
			st.setInt(13, 0);
			st.setString(14, user.getImageAvatar());
			res = st.executeUpdate();
			System.out.println("Bạn đã thực thi câu lệnh "+sql);
			System.out.println("Có kq dòng "+res+" bị thay đổi");
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

	public int updateVertifyInformation(User user) {
		// TODO Auto-generated method stub
		int res =0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE user SET authenticationCode=?, confirmationTime=?, status=? WHERE userID=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getAuthenticationCode());
			st.setDate(2, user.getConfirmationTime());
			st.setInt(3, 1);
			st.setString(4, user.getUserID());
			//thục thi câu lệnh sql
			System.out.println("Bạn đã thực thi câu lệnh: "+sql);
			res = st.executeUpdate();
			System.out.println("Có kq dòng bị thay đổi: "+res);
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exceptione
			e.printStackTrace();
		}
		return res;
	}

}
