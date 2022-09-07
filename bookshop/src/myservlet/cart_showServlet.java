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
import mybean.Cart;

/**
 * Servlet implementation class cart_showServlet
 */
@WebServlet("/cart_showServlet")
public class cart_showServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cart_showServlet() {
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
		if(se.getAttribute("level")!=null){
			request.setAttribute("msg", "«Î π”√”√ªß’À∫≈µ«¬º");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		if(se.getAttribute("id")==null){
			request.setAttribute("msg", "«Îµ«¬º");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		int userid=(int)se.getAttribute("id");
		Connection conn=Conn.getConn();
		String sql = "select * from Cart where userid=?";
		try{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, userid);
			ResultSet rs = pst.executeQuery();
			List<Cart> carts = new ArrayList<Cart>();
			while(rs.next()) {
				Cart cart = new Cart();
				cart.setUserid(rs.getInt("userid"));
				cart.setBookid(rs.getInt("bookid"));
				cart.setBookName(rs.getString("bookName")); 
				cart.setBookPrice(rs.getDouble("bookPrice"));
				cart.setBookNumber(rs.getInt("number"));
				cart.setBookFile(rs.getString("bookFile"));
				carts.add(cart);
			}
			request.setAttribute("carts", carts);
			
			
			rs.close();
			pst.close();
			conn.close();
			request.getRequestDispatcher("book_cart.jsp").forward(request, response);
		}catch (SQLException e) {
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
