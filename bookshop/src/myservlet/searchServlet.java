package myservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import mybean.Book;

/**
 * Servlet implementation class searchServlet
 */
@WebServlet("/searchServlet")
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchServlet() {
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
		String search=request.getParameter("search");
		int select=Integer.parseInt(request.getParameter("select"));
		Connection conn=Conn.getConn();
		if(select==1){
			String sql = "select * from Booklist where bookName like '%' ? '%'";
			try{
				
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, search);
				ResultSet rs = pst.executeQuery();
				if(rs.next()==false){
					request.setAttribute("msg", "查找不到该书籍");
				}
				rs.absolute(0);
				List<Book> books = new ArrayList<Book>();
				while(rs.next()) {
					Book book = new Book();
					book.setBookId(rs.getInt("bookId")); 
					book.setBookName(rs.getString("bookName")); 
					book.setBookPrice(rs.getDouble("bookPrice"));
					book.setBookFile(rs.getString("bookFile"));
					books.add(book);
				}
				request.setAttribute("books", books);
				rs.close();
				pst.close();
				conn.close();
				request.setAttribute("tag", "2");
				request.getRequestDispatcher("book_view.jsp").forward(request, response);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(select==2){
			String sql = "select * from Booklist where bookType like '%' ? '%'";
			try{
				
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, search);
				ResultSet rs = pst.executeQuery();
				if(rs.next()==false){
					request.setAttribute("msg", "查找不到该类别");
				}
				rs.absolute(0);
				List<Book> books = new ArrayList<Book>();
				while(rs.next()) {
					Book book = new Book();
					book.setBookId(rs.getInt("bookId")); 
					book.setBookName(rs.getString("bookName")); 
					book.setBookPrice(rs.getDouble("bookPrice"));
					book.setBookFile(rs.getString("bookFile"));
					books.add(book);
				}
				request.setAttribute("books", books);
				rs.close();
				pst.close();
				conn.close();
				request.setAttribute("tag", "2");
				request.getRequestDispatcher("book_view.jsp").forward(request, response);
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
