package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import utils.DataSourceUtils;
import domain.Category;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import domain.User;

public class Dao {

	public boolean insert(User user) throws SQLException {
		boolean flag=false;
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into user values(?,?,?,?,?,?,?)";
		int in = qr.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getSex() );
		if(in>0){
			flag=true;
		}
				return flag;
	}

	public boolean login(String username, String password) throws SQLException {
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			String sql="select * from user where username=? and password=?";
			User user = qr.query(sql, new BeanHandler<User>(User.class), username,password);
			boolean flag=false;
			if(user!=null){
				flag=true;
			}
		return flag;
	}

	public List<Category> findcategory() throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from category";
		List<Category> list = qr.query(sql, new BeanListHandler<Category>(Category.class));
		return list;
	}

	public List<Product> findnewproduct() throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="SELECT * FROM product ORDER BY pdate DESC LIMIT 0,8";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}
	public Product findproduct(String pid) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="SELECT * from product WHERE pid=?";
		Product pro = qr.query(sql, new BeanHandler<Product>(Product.class), pid);
		return pro;
	}

	public List<Product> findproductbyid(String cid) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="SELECT * FROM product where cid=? LIMIT 0,8";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class),cid);
		return list;
	}

	public void insertorders(Order order) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into orders values(?,?,?,?,?,?,?)";
		qr.update(sql, order.getOid(),order.getOrdertime(),order.getTotal(),
				order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid());
		
	}

	public void insertorderitem(Order order) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into orderitem values(?,?,?,?,?)";
		List<OrderItem> orderItems = order.getOrderItems();
		for(OrderItem item : orderItems){
			qr.update(sql,item.getItemid(),item.getCount(),item.getSubtotal(),item.getProduct().getPid(),item.getOrder().getOid());
		}
		
	}

	public User finduser(String username, String password) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * from `user` where username=? and `password`=?";
		User user = qr.query(sql, new BeanHandler<User>(User.class), username,password);
		return user;
	}

	public void updateorder(Order order) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orders set address=?,name=?,telephone=? where oid=?";
		qr.update(sql, order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
	}

	public List<Order> findorder(String uid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where uid=?";
		return qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
		
	}

	public List<Map<String, Object>> findAllOrderItemByOid(String oid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select i.count,i.subtotal,p.pimage,p.pname,p.shop_price from orderitem i,product p where i.pid=p.pid and i.oid=?";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), oid);
		return mapList;
	
	}

	public boolean check(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username=?";
		User user = qr.query(sql, new BeanHandler<User>(User.class), username);
		boolean flag=true;
		if(user==null){
			flag=false;
		}
		return flag;
	}

	public List<Object> findProductByWord(String word) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pname like ? limit 0,8";
		List<Object> query = qr.query(sql, new ColumnListHandler("pname"), "%"+word+"%");
		return query;
	}

}
