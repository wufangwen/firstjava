package service;

import java.sql.SQLException;
import java.util.List;

import dao.admindao;
import domain.Admin;
import domain.Category;
import domain.Product;

public class adminservice {

	public Admin adminlogin(String adminname, String adminpassword) throws SQLException {
	admindao ad=new admindao();
	Admin admin=ad.adminlogin(adminname,adminpassword);
		return admin;
	}

	public List<Product> checkproductlist() throws SQLException {
		admindao ad=new admindao();
		List<Product> list=ad.checkproductlist();
		return list;
	}

	public List<Category> findcategorylist() throws SQLException {
		admindao ad=new admindao();
		List<Category> list=ad.findcategorylist();
		return list;
	}

	public void saveProduct(Product product) throws SQLException {
		admindao ad=new admindao();
		ad.saveProduct(product);
	}

}
