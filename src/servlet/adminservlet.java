package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import domain.BeanFactory;
import domain.Category;
import domain.Product;
import service.adminservice;
import utils.CommonsUtils;



public class adminservlet extends BaseServlet {
	public void addproduct(HttpServletRequest request, HttpServletResponse response){
		//目的：收集表单的数据 封装一个Product实体 将上传图片存到服务器磁盘上
		
				Product product = new Product();
				
				//收集数据的容器
				Map<String,Object> map = new HashMap<String,Object>();
				
				try {
					//创建磁盘文件项工厂
					DiskFileItemFactory factory = new DiskFileItemFactory();
					//创建文件上传核心对象
					ServletFileUpload upload = new ServletFileUpload(factory);
					//解析request获得文件项对象集合

					List<FileItem> parseRequest = upload.parseRequest(request);
					for(FileItem item : parseRequest){
						//判断是否是普通表单项
						boolean formField = item.isFormField();
						if(formField){
							//普通表单项 获得表单的数据 封装到Product实体中
							String fieldName = item.getFieldName();
							String fieldValue = item.getString("UTF-8");
							
							map.put(fieldName, fieldValue);
							
						}else{
							//文件上传项 获得文件名称 获得文件的内容
							String fileName = item.getName();
							String path = this.getServletContext().getRealPath("products/1");
							InputStream in = item.getInputStream();
							OutputStream out = new FileOutputStream(path+"/"+fileName);//I:/xxx/xx/xxx/xxx.jpg
							IOUtils.copy(in, out);
							in.close();
							out.close();
							item.delete();
							
							map.put("pimage", "products/1/"+fileName);
							
						}
						
					}
					
					BeanUtils.populate(product, map);
					//是否product对象封装数据完全
					//private String pid;
					product.setPid(CommonsUtils.getUUID());
					//private Date pdate;
					product.setPdate(new Date());
					String cid = map.get("cid").toString();
				
					product.setCid(cid);;
					
					//将product传递给service层
					adminservice service = (adminservice)BeanFactory.getBean("adminservice");
					service.saveProduct(product);
					
					
					
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			}

		
	
	public void categorylist(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		adminservice ad=new adminservice();
		List<Category> list=ad.findcategorylist();
		Gson gson =new Gson();
		String json = gson.toJson(list);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	
	}
	public void productlist(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		adminservice ad=new adminservice();
		List<Product> list=ad.checkproductlist();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/productlist.jsp").forward(request, response);
	}
}
