package myservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.Book;
import mybean.Orderlist;

/**
 * Servlet implementation class orderServlet
 */
@WebServlet("/orderServlet")
public class orderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		int tag=0;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		String sum=request.getParameter("sum");
		HttpSession se = request.getSession();
		int userId=(int)se.getAttribute("id");
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String orderId=sdf.format(date);
		Connection conn=Conn.getConn();
		String sql = "select * from Cart where userid=?";
		try{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			if(!rs.next()){
				request.setAttribute("msg", "购物车为空");
				pst.close();
				conn.close();
				rs.close();
				request.getRequestDispatcher("cart_showServlet").forward(request, response);
				return;
			}
			rs.absolute(0);
			if(rs.next()){
				sql = "insert into orderform values(?,?,?,'未发货')";
				try{
					pst = conn.prepareStatement(sql);
					pst.setString(1,orderId);
					pst.setInt(2, userId);
					pst.setString(3, sum);
					if(pst.executeUpdate()>0) {
						//查购物车表获取图书信息
						sql = "select * from Cart where userId=?";
						pst = conn.prepareStatement(sql);
						pst.setInt(1,userId);
						rs = pst.executeQuery();
						while(rs.next()){
							String bookName=rs.getString("bookName");
							String bookPrice=rs.getString("bookPrice");
							String number=rs.getString("number");
							String bookFile=rs.getString("bookFile");
							sql = "insert into orderlist values(null,?,?,?,?,?,?)";
							pst = conn.prepareStatement(sql);
							pst.setString(1, orderId);
							pst.setInt(2,userId);
							pst.setString(3, bookName);
							pst.setString(4, bookPrice);
							pst.setString(5, number);
							pst.setString(6, bookFile);
							 tag=pst.executeUpdate();
						}	
						if(tag>0){
							 sql = "delete from Cart where userid=?";
							 pst = conn.prepareStatement(sql);
							 pst.setInt(1,userId);
							 if(pst.executeUpdate()>0){
									pst.close();
									rs.close();
									conn.close();
									request.setAttribute("msg", "购买成功");
									request.getRequestDispatcher("cart_showServlet").forward(request, response);
								}
						}
					}else {
						rs.close();
						pst.close();
						conn.close();
						request.setAttribute("msg", "Error");
						request.getRequestDispatcher("cart_showServlet").forward(request, response);
						return;
					}
				}catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}else{
				rs.close();
				pst.close();
				conn.close();
				request.setAttribute("msg", "订单已存在");
				request.getRequestDispatcher("cart_showServlet").forward(request, response);
				return;
			}
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
