package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dao.Dao;
import domain.Category;
import domain.Order;
import domain.Product;
import domain.User;

public class Service {

	public boolean insert(User user) throws SQLException {
		boolean flag=false;
		Dao dao=new Dao();
		flag=dao.insert(user);
		return flag;
	}

	public boolean login(String username, String password) throws SQLException {
			Dao dao=new Dao();
			boolean flag=false;
			flag=dao.login(username,password);
		return flag;
	}

	public List<Category> findcategory() throws SQLException {
		Dao dao=new Dao();
		List<Category> list=dao.findcategory();
		return list;
	}

	public List<Product> findnewproduct() throws SQLException {
		Dao dao=new Dao();
		List<Product> list=dao.findnewproduct();
		return list;
	}

	

	public Product findproduct(String pid) throws SQLException {
			Dao dao=new Dao();
			Product pro=dao.findproduct(pid);
		return pro;
	}

	public List<Product> findproductbyid(String cid) throws SQLException {
		Dao dao=new Dao();
		List<Product> list=dao.findproductbyid(cid);
		return list;
	}

	public void submitOrder(Order order) throws SQLException {
		Dao dao=new Dao();
		dao.insertorders(order);
		dao.insertorderitem(order);
		
	}

	public User finduser(String username, String password) throws SQLException {
		Dao dao=new Dao();
		User user=dao.finduser(username,password);
		return user;
	}

	public void updateOrderAdrr(Order order) throws SQLException {
		Dao dao=new Dao();
		dao.updateorder(order);
		
	}

	public List<Order> findAllOrders(String uid) throws SQLException {
		Dao dao=new Dao();
		 List<Order> list=dao.findorder(uid);
		return list;
	}

	public List<Map<String, Object>> findAllOrderItemByOid(String oid) {
		Dao dao = new Dao();
		List<Map<String, Object>> mapList = null;
		try {
			mapList = dao.findAllOrderItemByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapList;
	
	}

	public boolean checkuser(String username) throws SQLException {
		Dao dao=new Dao();
		boolean flag=dao.check(username);
		return flag;
	}

	public List<Object> findProductByWord(String word) throws SQLException {
		Dao dao=new Dao();
		return dao.findProductByWord(word);
	}

}
