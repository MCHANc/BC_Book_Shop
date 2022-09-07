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
 * Servlet implementation class userChangeServlet
 */
@WebServlet("/userChangeServlet")
public class userChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userChangeServlet() {
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
		String id = request.getParameter("id"); 
		String userName = request.getParameter("userName"); 
		String password = request.getParameter("password"); 
		int bt =Integer.parseInt(request.getParameter("bt")); 
		if(userName.equals("")) {
			request.setAttribute("msg", "用户名为空");
			request.getRequestDispatcher("userManageServlet").forward(request, response);
			return;
		}
		
		if(password.equals("")) {
			request.setAttribute("msg", "密码为空");
			request.getRequestDispatcher("userManageServlet").forward(request, response);
			return;
		}
		if(bt==1){
			Connection conn=Conn.getConn();
			String sql = "update User set ban=1 where id=?";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, id);
				if(pst.executeUpdate()>0) {
					pst.close();
					conn.close();
					response.sendRedirect("userManageServlet");
				}	
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(bt==2){
			Connection conn=Conn.getConn();
			String sql = "update User set ban=0 where id=?";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, id);
				if(pst.executeUpdate()>0) {
					pst.close();
					conn.close();
					response.sendRedirect("userManageServlet");
				}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(bt==3){
			Connection conn=Conn.getConn();
			String sql = "update User set userName=? , password=? where id=?";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, userName);
				pst.setString(2, password);
				pst.setString(3, id);
				if(pst.executeUpdate()>0){
					pst.close();
					conn.close();
					response.sendRedirect("userManageServlet");
				}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(bt==4){
			Connection conn=Conn.getConn();
			String sql = "delete from User where id=?";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, id);
				if(pst.executeUpdate()>0){
					pst.close();
					conn.close();
					response.sendRedirect("userManageServlet");
				}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(bt==5){
			Connection conn=Conn.getConn();
			String sql = "select * from User where id=?";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, id);
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {//存在
					request.setAttribute("msg", "ID已存在");
					request.getRequestDispatcher("userManageServlet").forward(request, response);
					return;
				}else{
					sql = "select * from User where userName=?";
					pst = conn.prepareStatement(sql);
					pst.setString(1,userName);
					rs = pst.executeQuery();
					if(rs.next()) {//存在
						request.setAttribute("msg", "用户名已存在");
						request.getRequestDispatcher("userManageServlet").forward(request, response);
						return;
					}else{
						sql = "insert into User values(?,?,?,0)";
						pst = conn.prepareStatement(sql);
						pst.setString(1,id);
						pst.setString(2, userName);
						pst.setString(3, password);
						if(pst.executeUpdate()>0) {
							request.setAttribute("msg", "添加成功");
							request.getRequestDispatcher("userManageServlet").forward(request, response);
						}else {
							request.setAttribute("msg", "Error");
							request.getRequestDispatcher("userManageServlet").forward(request, response);
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
