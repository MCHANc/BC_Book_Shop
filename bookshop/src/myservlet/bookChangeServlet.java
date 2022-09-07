package myservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class bookChangeServlet
 * 
 */
@WebServlet("/bookChangeServlet")
@MultipartConfig
public class bookChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookChangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		String bookId = request.getParameter("bookId"); 
		String bookName = request.getParameter("bookName"); 
		String bookPrice = request.getParameter("bookPrice"); 
		String bookAuthor = request.getParameter("bookAuthor"); 
		String bookType = request.getParameter("bookType"); 
		String bookDetail = request.getParameter("bookDetail"); 
		String bookIsbn = request.getParameter("bookIsbn"); 
		int bt =Integer.parseInt(request.getParameter("bt")); 
		if(bookName.equals("")) {
			request.setAttribute("msg", "书名为空");
			request.getRequestDispatcher("bookManageServlet").forward(request, response);
			return;
		}
		if(bookPrice.equals("")) {
			request.setAttribute("msg", "价格为空");
			request.getRequestDispatcher("bookManageServlet").forward(request, response);
			return;
		}
		if(bookAuthor.equals("")) {
			request.setAttribute("msg", "作者为空");
			request.getRequestDispatcher("bookManageServlet").forward(request, response);
			return;
		}
		if(bookType.equals("")) {
			request.setAttribute("msg", "图书类别为空");
			request.getRequestDispatcher("bookManageServlet").forward(request, response);
			return;
		}
		if(bookDetail.equals("")) {
			request.setAttribute("msg", "图书详细信息为空");
			request.getRequestDispatcher("bookManageServlet").forward(request, response);
			return;
		}
		if(bookIsbn.equals("")) {
			request.setAttribute("msg", "图书ISBN码为空");
			request.getRequestDispatcher("bookManageServlet").forward(request, response);
			return;
		}
		if(bt==1){
			Connection conn=Conn.getConn();
			String sql = "update Booklist set bookName=?,bookPrice=?,bookAuthor=?,bookType=?,bookDetail=?,bookIsbn=? where bookid=?";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, bookName);
				pst.setString(2, bookPrice);
				pst.setString(3, bookAuthor);
				pst.setString(4, bookType);
				pst.setString(5, bookDetail);
				pst.setString(6, bookIsbn);
				pst.setString(7, bookId);
				if(pst.executeUpdate()>0) {
					pst.close();
					conn.close();
					response.sendRedirect("bookManageServlet");
				}	
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(bt==2){
		
			Connection conn=Conn.getConn();
			String sql = "delete from Booklist where bookid=?";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, bookId);
				if(pst.executeUpdate()>0){
					pst.close();
					conn.close();
					response.sendRedirect("bookManageServlet");
				}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(bt==3){
			//图片上传
			Part part=request.getPart("photo");
			long len =part.getSize();
			String fileName=null;
			if(len>0){
				String s=part.getHeader("Content-Disposition");
				int pos=s.lastIndexOf(".");
				String ext="";//后缀名
				if(pos>0){
					ext=s.substring(pos,s.length()-1);
				}
				fileName=bookId+ext;
				part.write(getServletContext().getRealPath("/")+fileName);
				System.out.println(getServletContext().getRealPath("/")+fileName);
			}
			
			Connection conn=Conn.getConn();
			String sql = "select * from Booklist where bookid=?";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, bookId);
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {//存在
					request.setAttribute("msg", "ID已存在");
					request.getRequestDispatcher("bookManageServlet").forward(request, response);
					return;
				}else{
					sql = "select * from Booklist where bookName=? and bookAuthor=?";
					pst = conn.prepareStatement(sql);
					pst.setString(1,bookName);
					pst.setString(2,bookAuthor);
					rs = pst.executeQuery();
					if(rs.next()) {//存在
						request.setAttribute("msg", "图书已存在");
						request.getRequestDispatcher("bookManageServlet").forward(request, response);
						return;
					}else{
						sql = "insert into Booklist values(?,?,?,?,?,?,?,?)";
						pst = conn.prepareStatement(sql);
						pst.setString(1,bookId);
						pst.setString(2, bookName);
						pst.setString(3, bookPrice);
						pst.setString(4, bookAuthor);
						pst.setString(5, bookType);
						pst.setString(6, bookDetail);
						pst.setString(7, bookIsbn);
						pst.setString(8, fileName);
						if(pst.executeUpdate()>0) {
							request.setAttribute("msg", "图书添加成功");
							request.getRequestDispatcher("bookManageServlet").forward(request, response);
						}else {
							request.setAttribute("msg", "Error");
							request.getRequestDispatcher("bookManageServlet").forward(request, response);
							return;
						}
					}
				}
				pst.close();
				rs.close();
				conn.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
