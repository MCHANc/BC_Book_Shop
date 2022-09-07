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

import mybean.Cart;
import mybean.Order;

/**
 * Servlet implementation class myOrderServlet
 */
@WebServlet("/myOrderServlet")
public class myOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myOrderServlet() {
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
		String sql = "select * from Orderform where userid=?";
		try{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, userid);
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

			rs.close();
			pst.close();
			conn.close();
			request.getRequestDispatcher("order.jsp").forward(request, response);
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
