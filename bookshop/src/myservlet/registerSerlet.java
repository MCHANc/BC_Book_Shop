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

/**
 * Servlet implementation class regosterSerlet
 */
@WebServlet("/registerSerlet")
public class registerSerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerSerlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		
		String userName = request.getParameter("userName"); 
		String password1 = request.getParameter("password1"); 
		String password2 = request.getParameter("password2"); 
		String value = request.getParameter("admin");
		//密码不相同
		if(!password1.equals(password2)) {
			request.setAttribute("msg", "密码不一致");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		if(userName.equals("")) {
			request.setAttribute("msg", "用户名为空");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		
		if(password1.equals("")) {
			request.setAttribute("msg", "密码为空");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		
		
		Connection conn=Conn.getConn();
		
		String sql = "select * from User where userName=?";
		String sql2 = "select * from Admin where admin_Name=?";
		//管理员注册
		if(value!=null){
			String admin_Name = userName; 
			try{
				PreparedStatement pst = conn.prepareStatement(sql2);
				pst.setString(1, admin_Name);
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {//存在
					request.setAttribute("msg", "用户名已存在");
					request.getRequestDispatcher("register.jsp").forward(request, response);
					return;
				}else {
					sql2 = "insert into Admin values(null,?,?)";
					pst = conn.prepareStatement(sql2);
					pst.setString(1,admin_Name);
					pst.setString(2, password1);
					if(pst.executeUpdate()>0) {
						response.sendRedirect("login.jsp");
					}else {
						request.setAttribute("msg", "Error");
						request.getRequestDispatcher("register.jsp").forward(request, response);
						return;
					}
				}
				pst.close();
				rs.close();
				conn.close();
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		//用户注册
		if(value==null){
			try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {//存在
				request.setAttribute("msg", "用户名已存在");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}else {
				sql = "insert into User values(null,?,?,0)";
				pst = conn.prepareStatement(sql);
				pst.setString(1,userName);
				pst.setString(2, password1);
				if(pst.executeUpdate()>0) {
					response.sendRedirect("login.jsp");
				}else {
					request.setAttribute("msg", "Error");
					request.getRequestDispatcher("register.jsp").forward(request, response);
					return;
				}
			}
			pst.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
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
