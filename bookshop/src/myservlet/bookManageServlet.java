package myservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.Book;

/**
 * Servlet implementation class bookManageServlet
 */
@WebServlet("/bookManageServlet")
public class bookManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookManageServlet() {
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
		HttpSession se = request.getSession();
		if(se.getAttribute("id")==null||se.getAttribute("level")==null){
			request.setAttribute("msg", "请使用管理员账号登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		Connection conn=Conn.getConn();
		String sql = "select * from Booklist ";
		try{
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			List<Book> books = new ArrayList<Book>();
			while(rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("bookId")); 
				book.setBookName(rs.getString("bookName")); 
				book.setBookPrice(rs.getDouble("bookPrice"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setBookType(rs.getString("bookType"));
				book.setBookDetail(rs.getString("bookDetail"));
				book.setBookIsbn(rs.getInt("bookIsbn"));
				book.setBookFile(rs.getString("bookFile"));
				books.add(book);
			}
			request.setAttribute("books", books);
			rs.close();
			pst.close();
			conn.close();
			request.getRequestDispatcher("bookManage.jsp").forward(request, response);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
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
