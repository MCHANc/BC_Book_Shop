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

import mybean.User;

/**
 * Servlet implementation class user_searchServlet
 */
@WebServlet("/user_searchServlet")
public class user_searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public user_searchServlet() {
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
			String sql = "select * from User where userName like '%' ? '%'";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, search);
				ResultSet rs = pst.executeQuery();
				if(rs.next()==false){
					request.setAttribute("msg", "该用户不存在");
				}
				rs.absolute(0);
				List<User> users = new ArrayList<User>();
				while(rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setUserName(rs.getString("userName"));
					user.setPassword(rs.getString("password"));
					user.setBan(rs.getInt("ban"));
					users.add(user);
				}
				request.setAttribute("users", users);
				rs.close();
				pst.close();
				conn.close();
				request.getRequestDispatcher("userManage.jsp").forward(request, response);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(select==2){
			String sql = "select * from User where id like '%' ? '%'";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, search);
				ResultSet rs = pst.executeQuery();
				if(rs.next()==false){
					request.setAttribute("msg", "该用户不存在");
				}
				rs.absolute(0);
				List<User> users = new ArrayList<User>();
				while(rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setUserName(rs.getString("userName"));
					user.setPassword(rs.getString("password"));
					user.setBan(rs.getInt("ban"));
					users.add(user);
				}
				request.setAttribute("users", users);
				rs.close();
				pst.close();
				conn.close();
				request.getRequestDispatcher("userManage.jsp").forward(request, response);
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
