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

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		String userName = request.getParameter("userName"); 
		String password = request.getParameter("password"); 
		String value = request.getParameter("admin");
		if(userName.equals("")) {
			request.setAttribute("msg", "用户名为空");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		
		if(password.equals("")) {
			request.setAttribute("msg", "密码为空");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		Connection conn=Conn.getConn();
		
		String sql = "select * from User where userName=?";
		String sql2 = "select * from Admin where admin_Name=?";
		//管理员登录
		if(value!=null){
			
			String admin_Name = userName; 
			try{
				PreparedStatement pst = conn.prepareStatement(sql2);
				pst.setString(1, admin_Name);
				ResultSet rs = pst.executeQuery();	
				if(rs.next()) {
					
					if(!rs.getString("admin_password").equals(password)){
						request.setAttribute("msg", "密码错误");
						request.getRequestDispatcher("login.jsp").forward(request, response);
						return;
					}
					HttpSession se = request.getSession();
					se.setAttribute("id", rs.getInt("id"));
					se.setAttribute("userName", rs.getString("admin_Name"));
					se.setAttribute("level", rs.getInt("level"));
				}else {
					request.setAttribute("msg", "用户不存在");
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				rs.close();
				pst.close();
				conn.close();
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		//用户登录
		if(value==null){
			try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();			
			if(rs.next()) {
				
				if(rs.getInt("ban")==1){
					request.setAttribute("msg", "该用户已被禁止访问");
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				if(!rs.getString("password").equals(password)){
					request.setAttribute("msg", "密码错误");
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				HttpSession se = request.getSession();
				se.setAttribute("id", rs.getInt("id"));
				se.setAttribute("userName", rs.getString("userName"));
				se.removeAttribute("level");
			}else {
				request.setAttribute("msg", "用户不存在");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			rs.close();
			pst.close();
			conn.close();
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
