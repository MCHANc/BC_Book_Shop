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

import mybean.Order;
import mybean.Orderlist;

/**
 * Servlet implementation class orderListServlet
 */
@WebServlet("/orderListServlet")
public class orderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderListServlet() {
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
		String bookName=request.getParameter("bookName");
		String bookPrice=request.getParameter("bookPrice");
		
		Connection conn=Conn.getConn();
		if(bt!=null){
			String sql="delete from Orderlist where orderId=? and bookName=?";
			try{
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setString(1, orderId);
					pst.setString(2,bookName);
					if(pst.executeUpdate()>0){
					sql = "update Orderform set sum=sum-? where orderId=?";
					pst = conn.prepareStatement(sql);
					pst.setString(1, bookPrice);
					pst.setString(2, orderId);
					if(pst.executeUpdate()>0){
						sql = "select * from Orderlist where orderId=?";
						pst = conn.prepareStatement(sql);
						pst.setString(1, orderId);
						ResultSet rs = pst.executeQuery();
						if(rs.next()==false){
							String sql2 = "update Orderform set state='ÒÑÈ¡Ïû' where orderId=?";
							PreparedStatement pst2 = conn.prepareStatement(sql2);
							pst2.setString(1, orderId);
							pst2.executeUpdate();
						}
						rs.absolute(0);
						List<Orderlist> ols = new ArrayList<Orderlist>();
						while(rs.next()) {
							Orderlist ol = new Orderlist();
							ol.setOrderId(rs.getString("orderId"));
							ol.setUserId(rs.getInt("userId"));
							ol.setBookName(rs.getString("bookName"));
							ol.setBookPrice(rs.getDouble("bookPrice"));
							ol.setNumber(rs.getInt("number"));
							ol.setBookFile(rs.getString("bookFile"));
							ols.add(ol);
						}
						request.setAttribute("ols", ols);
						rs.close();
						pst.close();
						conn.close();
						request.getRequestDispatcher("myOrderList.jsp").forward(request, response);
					}	
				}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else{
			String sql = "select * from Orderlist where orderId=?";
			try{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, orderId);
				ResultSet rs = pst.executeQuery();
				List<Orderlist> ols = new ArrayList<Orderlist>();
				while(rs.next()) {
					Orderlist ol = new Orderlist();
					ol.setOrderId(rs.getString("orderId"));
					ol.setUserId(rs.getInt("userId"));
					ol.setBookName(rs.getString("bookName"));
					ol.setBookPrice(rs.getDouble("bookPrice"));
					ol.setNumber(rs.getInt("number"));
					ol.setBookFile(rs.getString("bookFile"));
					ols.add(ol);
				}
				request.setAttribute("ols", ols);
				rs.close();
				pst.close();
				conn.close();
				request.getRequestDispatcher("myOrderList.jsp").forward(request, response);
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
