package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.DataSourceUtils;
import domain.Admin;
import domain.Category;
import domain.Product;

public class admindao {

	public Admin adminlogin(String adminname, String adminpassword) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from admin where adminname=? and adminpassword=?";
		Admin admin = qr.query(sql, new BeanHandler<Admin>(Admin.class),  adminname,adminpassword);
		return admin;
	}

	public List<Product> checkproductlist() throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product ";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}

	public List<Category> findcategorylist() throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from category ";
		List<Category> list = qr.query(sql, new BeanListHandler<Category>(Category.class));
		return list;
	}

	public void saveProduct(Product product) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?)";
		qr.update(sql, product.getPid(),product.getPname(),product.getMarket_price(),
				product.getShop_price(),product.getPimage(),product.getPdate(),
				product.getIs_hot(),product.getPdesc(),product.getCid());
		
	}

}
