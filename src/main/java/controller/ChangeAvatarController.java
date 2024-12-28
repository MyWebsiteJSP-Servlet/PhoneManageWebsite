package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class ChangeAvatarController
 */
@WebServlet("/change-Avatar")
public class ChangeAvatarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeAvatarController() {
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
		try {
			File file = null;
			boolean kiemTra = false;
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			diskFileItemFactory.setRepository(new File("E:\\LaptrinhWeb\\MobileWebApp\\src\\main\\webapp"));
			ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
			List<FileItem> fileItems = fileUpload.parseRequest(request);
			for (FileItem fileItem : fileItems) {
				if (!fileItem.isFormField()) {
					if (fileItem.getFieldName().equals("file")) {
						file = new File(
								"E:\\LaptrinhWeb\\MobileWebApp\\src\\main\\webapp\\avatar\\" + fileItem.getName());
						if (file.exists()) {
							kiemTra = true;
							break;
						}
						fileItem.write(file);
//						request.setAttribute("duongDan", file.getName());
//						RequestDispatcher rd = getServletContext().getRequestDispatcher("/insert-img");
//						rd.forward(request, response);
					}
				}
			}
			if (kiemTra == true) {
				request.setAttribute("hopThu", "Không thể cập nhật mới vì ảnh đại diện đã tồn tại rồi");
				request.setAttribute("sourceServlet", "changeAvatarFaild");
				request.setAttribute("duongDan", file.getName());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/account.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("duongDan", file.getName());
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/change-image");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}