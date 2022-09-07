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
import mybean.Order;
import mybean.Orderlist;

/**
 * Servlet implementation class orderManageServlet
 */
@WebServlet("/orderManageServlet")
public class orderManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderManageServlet() {
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
		String bt=request.getParameter("bt");
		String orderId=request.getParameter("orderId");
		HttpSession se = request.getSession();
		if(se.getAttribute("id")==null||se.getAttribute("level")==null){
			request.setAttribute("msg", "请使用管理员账号登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		Connection conn=Conn.getConn();
			String sql = "select * from Orderform ";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				List<Order> orders = new ArrayList<Order>();
				while(rs.next()) {
					Order order = new Order();
					order.setOrderId(rs.getString("orderId")); 
					order.setUserId(rs.getInt("userId"));
					order.setSum(rs.getDouble("sum"));
					order.setState(rs.getString("state"));
					orders.add(order);
				}
				request.setAttribute("orders", orders);
				if(bt!=null){
					if(bt.equals("1")){
					request.setAttribute("tag", "true");
					sql = "select * from Orderlist where orderId=?";
					pst = conn.prepareStatement(sql);
					pst.setString(1, orderId);
					rs = pst.executeQuery();
					List<Orderlist> ols = new ArrayList<Orderlist>();
					while(rs.next()){
						Orderlist ol=new Orderlist();
						ol.setOrderId(rs.getString("orderId"));
						ol.setUserId(rs.getInt("userId"));
						ol.setBookName(rs.getString("bookName"));
						ol.setBookPrice(rs.getDouble("bookPrice"));
						ol.setNumber(rs.getInt("number"));
						ol.setBookFile(rs.getString("bookFile"));
						ols.add(ol);
					}
					request.setAttribute("ols", ols);
					}
				}
				rs.close();
				pst.close();
				conn.close();
				request.getRequestDispatcher("orderManage.jsp").forward(request, response);
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
