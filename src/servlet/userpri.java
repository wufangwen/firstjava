package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.Service;
import utils.CommonsUtils;
import domain.Cart;
import domain.CartItem;
import domain.Order;
import domain.OrderItem;
import domain.User;

public class userpri extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");

		//目的：封装好一个Order对象 传递给service层
		Order order = new Order();

		//1、private String oid;//该订单的订单号
		String oid = CommonsUtils.getUUID();
		order.setOid(oid);

		//2、private Date ordertime;//下单时间
		order.setOrdertime(new Date());

		//3、private double total;//该订单的总金额
		//获得session中的购物车
		Cart cart = (Cart) session.getAttribute("cart");
		double total = cart.getTotal();
		order.setTotal(total);

		//5、private String address;//收货地址
		order.setAddress(null);

		//6、private String name;//收货人
		order.setName(null);

		//7、private String telephone;//收货人电话
		order.setTelephone(null);

		//8、private User user;//该订单属于哪个用户
		order.setUser(user);

		//9、该订单中有多少订单项List<OrderItem> orderItems = new ArrayList<OrderItem>();
		//获得购物车中的购物项的集合map
		Map<String, CartItem> cartItems = cart.getCartItems();
		for(Map.Entry<String, CartItem> entry : cartItems.entrySet()){
			//取出每一个购物项
			CartItem cartItem = entry.getValue();
			//创建新的订单项
			OrderItem orderItem = new OrderItem();
			//1)private String itemid;//订单项的id
			orderItem.setItemid(CommonsUtils.getUUID());
			//2)private int count;//订单项内商品的购买数量
			orderItem.setCount(cartItem.getBuyNum());
			//3)private double subtotal;//订单项小计
			orderItem.setSubtotal(cartItem.getSubtotal());
			//4)private Product product;//订单项内部的商品
			orderItem.setProduct(cartItem.getProduct());
			//5)private Order order;//该订单项属于哪个订单
			orderItem.setOrder(order);

			//将该订单项添加到订单的订单项集合中
			order.getOrderItems().add(orderItem);
		}


		//order对象封装完毕
		//传递数据到service层
		Service service = new Service();
		try {
			service.submitOrder(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		session.setAttribute("order", order);

		//页面跳转
		response.sendRedirect(request.getContextPath()+"/orderinfo.jsp");


	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}