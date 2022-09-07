package myservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.Book;

/**
 * Servlet implementation class cartServlet
 */
@WebServlet("/cartServlet")
public class cartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cartServlet() {
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
			request.setAttribute("msg", "请使用用户账号登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		if(se.getAttribute("id")==null){
			response.sendRedirect("login.jsp");
			return;
		}
		String bookId=request.getParameter("bookId");
		String num = request.getParameter("num"); 
		int flag=Integer.parseInt(request.getParameter("flag"));
		int userid = (int)se.getAttribute("id");
		Connection conn=Conn.getConn();
		if(flag==1){
			String sql = "update cart set number=? where bookid=? and userid=?"; 
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst = conn.prepareStatement(sql);
				pst.setString(1, num);
				pst.setString(2, bookId);
				pst.setInt(3, userid);
				if(pst.executeUpdate()>0) {
					pst.close();
					conn.close();
					response.sendRedirect("cart_showServlet");
				}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}	
		}
		if(flag==0){
			String sql = "select * from Cart where bookid=? and userid=?";
		try{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, bookId);
			pst.setInt(2, userid);
			ResultSet rs = pst.executeQuery();
			if(!rs.next()){
				
				 sql = "select * from Booklist where bookid=?";
				 pst = conn.prepareStatement(sql);
				  pst.setString(1, bookId);
				 rs = pst.executeQuery();
				 rs.next();
				int bookid=rs.getInt("bookid");
				String bookName=rs.getString("bookName");
				Double bookPrice=rs.getDouble("bookPrice");
				String bookFile=rs.getString("bookFile");
				sql = "insert into cart values(?,?,?,?,?,?)";
				pst = conn.prepareStatement(sql);
				
				pst.setInt(1, userid);
				pst.setInt(2,bookid);
				pst.setString(3, bookName);
				pst.setDouble(4, bookPrice);
				pst.setString(5, num);
				pst.setString(6, bookFile);
				pst.executeUpdate();
				
			}else{

				int number=rs.getInt("number")+Integer.parseInt(num);
				sql = "update cart set number=? where bookid=? and userid=?";
				pst = conn.prepareStatement(sql);
				pst.setInt(1, number);
				pst.setString(2, bookId);
				pst.setInt(3, userid);
				pst.executeUpdate();
				
			}
			
			rs.close();
			pst.close();
			conn.close();
			request.getRequestDispatcher("bookServlet").forward(request, response);
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		}
		if(flag==2){
			
			String sql = "delete from Cart where bookid=?";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, bookId);
				if(pst.executeUpdate()>0){
					pst.close();
					conn.close();
					response.sendRedirect("cart_showServlet");
				}
			}catch (SQLException e) {
				// TODO: handle exception
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
