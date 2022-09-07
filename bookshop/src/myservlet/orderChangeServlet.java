package myservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class orderChangeServlet
 */
@WebServlet("/orderChangeServlet")
public class orderChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderChangeServlet() {
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
		String orderId=request.getParameter("orderId");
		int s=Integer.parseInt(request.getParameter("select"));
		Connection conn=Conn.getConn();
		if(s==1){
			String sql = "update Orderform set state='未发货' where orderId=?";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, orderId);
				if(pst.executeUpdate()>0) {
					pst.close();
					conn.close();
					response.sendRedirect("orderManageServlet");
				}	
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(s==2){
			String sql = "update Orderform set state='已发货' where orderId=?";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, orderId);
				if(pst.executeUpdate()>0) {
					pst.close();
					conn.close();
					response.sendRedirect("orderManageServlet");
				}	
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(s==3){
			String sql = "update Orderform set state='已取消' where orderId=?";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, orderId);
				if(pst.executeUpdate()>0) {
					pst.close();
					conn.close();
					response.sendRedirect("orderManageServlet");
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
