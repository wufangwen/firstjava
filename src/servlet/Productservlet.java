package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import service.Service;
import service.adminservice;
import utils.CommonsUtils;

import com.google.gson.Gson;

import domain.Admin;
import domain.Cart;
import domain.CartItem;
import domain.Category;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import domain.User;


public class Productservlet extends BaseServlet{
	public void clearword(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String word = request.getParameter("word");
		String cha="";
		Gson gson=new Gson();
		String json = gson.toJson(cha);
		response.getWriter().write(json);
		
	}
	public void searchword(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//获得关键字
				String word = request.getParameter("word");
				
				//查询该关键字的所有商品
				Service service = new Service();
				List<Object> productList = null;
				try {
					productList = service.findProductByWord(word);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				//["xiaomi","huawei",""...]
				
				//使用json的转换工具将对象或集合转成json格式的字符串
				/*JSONArray fromObject = JSONArray.fromObject(productList);
				String string = fromObject.toString();
				System.out.println(string);*/
				
				Gson gson = new Gson();
				String json = gson.toJson(productList);
				
				
				response.setContentType("text/html;charset=UTF-8");
				
				response.getWriter().write(json);
				
				
		
	}
	public void adminlogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		String adminname = request.getParameter("adminname");
		String adminpassword = request.getParameter("adminpassword");
		adminservice ad=new adminservice();
		Admin admin=ad.adminlogin(adminname,adminpassword);
		if(admin!=null){
			 response.sendRedirect(request.getContextPath()+"/admin/index.jsp");
				
			 HttpSession session = request.getSession();
			 session.setAttribute("admin",admin );

		 }else{
			 response.sendRedirect(request.getContextPath()+"/adminlogin.jsp");
		 }
		
		
	}
	//更新购物车
	public void updateorderitem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		Map<String, String[]> properties = request.getParameterMap();
		Order order = new Order();
		try {
			BeanUtils.populate(order, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		Service service = new Service();
		service.updateOrderAdrr(order);
		response.sendRedirect(request.getContextPath()+"/error.jsp");
							
	}
	
	//提交购物车
	public void submitcart(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		
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
		service.submitOrder(order);

System.out.println("ssss");
		session.setAttribute("order", order);

		//页面跳转
		response.sendRedirect(request.getContextPath()+"/orderinfo.jsp");


	}
		

	//清空购物车
	public void clear(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		session.removeAttribute("cart");
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}
	//删除商品
	public void deletepid(HttpServletRequest request, HttpServletResponse response) throws IOException{
		System.out.println("ss");
		String pid = request.getParameter("pid");
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		Map<String, CartItem> cartItems = cart.getCartItems();
		if(cart!=null){
			cart.setTotal(cart.getTotal()-cartItems.get(pid).getSubtotal());
			cartItems.remove(pid);
		}
		session.setAttribute("cart", cart);

		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		
	}
	//添加商品
	public void addproduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{

		HttpSession session = request.getSession();

		Service service = new Service ();


		//获得要放到购物车的商品的pid
		String pid = request.getParameter("pid");
		//获得该商品的购买数量
		int buyNum = Integer.parseInt(request.getParameter("buynumber"));

		//获得product对象
		Product product = service.findproduct(pid);
		//计算小计
		double subtotal = product.getShop_price()*buyNum;
		//封装CartItem
		CartItem item = new CartItem();
		item.setProduct(product);
		item.setBuyNum(buyNum);
		item.setSubtotal(subtotal);

		//获得购物车---判断是否在session中已经存在购物车
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart==null){
			cart = new Cart();
		}

		//将购物项放到车中---key是pid
		//先判断购物车中是否已将包含此购物项了 ----- 判断key是否已经存在
		//如果购物车中已经存在该商品----将现在买的数量与原有的数量进行相加操作
		Map<String, CartItem> cartItems = cart.getCartItems();

		double newsubtotal = 0.0;

		if(cartItems.containsKey(pid)){
			//取出原有商品的数量
			CartItem cartItem = cartItems.get(pid);
			int oldBuyNum = cartItem.getBuyNum();
			oldBuyNum+=buyNum;
			cartItem.setBuyNum(oldBuyNum);
			cart.setCartItems(cartItems);
			//修改小计
			//原来该商品的小计
			double oldsubtotal = cartItem.getSubtotal();
			//新买的商品的小计
			newsubtotal = buyNum*product.getShop_price();
			cartItem.setSubtotal(oldsubtotal+newsubtotal);

		}else{
			//如果车中没有该商品
			cart.getCartItems().put(product.getPid(), item);
			newsubtotal = buyNum*product.getShop_price();
		}

		//计算总计
		double total = cart.getTotal()+newsubtotal;
		cart.setTotal(total);


		//将车再次访问session
		session.setAttribute("cart", cart);

		//直接跳转到购物车页面
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}
		
	
	//根据分类查找
	public void findproductbyid(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		
		String cid = request.getParameter("cid");
		Service ser=new Service();
		List<Product> list=ser.findproductbyid(cid);
	
		request.setAttribute("list", list);
		request.getRequestDispatcher("/productlist.jsp").forward(request, response);
		
	}
	//查找物品
	public void productinfo(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			String pid = request.getParameter("pid");
			Service ser=new Service();
			Product pro= ser.findproduct(pid);
			request.setAttribute("product", pro);
			request.getRequestDispatcher("/productinfo.jsp").forward(request, response);
		
	}
	//首页最新商品
	public void newproduct(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Service ser=new Service();
		List<Product> list=null;
		try {
			list=ser.findnewproduct();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson gson=new Gson();
		String json = gson.toJson(list);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
		
	}
	//商品目录
	public void categorylist(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		
		Service ser=new Service();
		List<Category> list=ser.findcategory();
		Gson gson=new Gson();
		String json = gson.toJson(list);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
		
		
		
	}
	//用户退出
	public void away(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		//将存储在客户端的cookie删除掉
		Cookie cookie_username = new Cookie("cookie_username","");
		cookie_username.setMaxAge(0);
		//创建存储密码的cookie
		Cookie cookie_password = new Cookie("cookie_password","");
		cookie_password.setMaxAge(0);

		response.addCookie(cookie_username);
		response.addCookie(cookie_password);
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		
		
	}
	//登陆校验
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Service ser=new Service();
		User user=ser.finduser(username,password);
		
		 if(user!=null){
			 String aotulogin = request.getParameter("autologin");
			 if("aotulogin".equals(aotulogin)){
				//要自动登录
					//创建存储用户名的cookie
				 String name = user.getUsername();
				 String username1 = URLEncoder.encode(name, "utf-8");
					Cookie cookie_username = new Cookie("cookie_username",username1);
					cookie_username.setMaxAge(10*60);
					//创建存储密码的cookie
					String word = user.getPassword();
					String word1 = URLEncoder.encode(word, "utf-8");
					Cookie cookie_password = new Cookie("cookie_password",word1);
					cookie_password.setMaxAge(10*60);
					
					response.addCookie(cookie_username);
				
					response.addCookie(cookie_password);
			
			 }
			
			
			 HttpSession session = request.getSession();
			 session.setAttribute("user",user );
			 response.sendRedirect(request.getContextPath()+"/index.jsp");

		 }else{
			 response.sendRedirect(request.getContextPath()+"/error.jsp");
		 }
		
		
	}
	//注册用户
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, String[]> properties = request.getParameterMap();
		User user=new User();
		user.setUid(CommonsUtils.getUUID());
		try {
			BeanUtils.populate(user, properties);
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Service ser=new Service();
		boolean flag=false;
		try {
			flag=ser.insert(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag){
			try {
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{   response.sendRedirect(request.getContextPath()+"/error.jsp");
			
		}
	}
	
}
