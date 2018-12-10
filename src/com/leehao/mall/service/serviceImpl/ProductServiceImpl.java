package com.leehao.mall.service.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.leehao.mall.dao.ProductDao;
import com.leehao.mall.dao.daoImpl.ProductDaoImpl;
import com.leehao.mall.domain.PageModel;
import com.leehao.mall.domain.Product;
import com.leehao.mall.service.ProductService;

public class ProductServiceImpl implements ProductService {
	private ProductDao productDao = new ProductDaoImpl();

	@Override
	public List<Product> findNews() throws SQLException {
		List<Product> newsProductList = productDao.findNews();
		return newsProductList;
	}

	@Override
	public List<Product> findHots() throws SQLException {
		List<Product> hotProductList = productDao.findHots();
		return hotProductList;
	}

	@Override
	public Product finProductById(String pid) throws Exception {
		return productDao.finProductById(pid);
	}

	@Override
	public int findTotalRecords(String cid) throws Exception {
		return productDao.findTotalRecords(cid);
	}

	@Override
	public PageModel findProductsByCidWithPage(String cid, int currentPageNum) throws Exception {
		// 调用分页pageModel对象-----需要几个参数：查看页码，总记录数，分页记录list
		int totalRecords = productDao.findTotalRecords(cid);
		PageModel pageModel = new PageModel(currentPageNum, totalRecords, 12);
		//分页对象关联记录集合
		List list = productDao.findProductsByCidWithPage(cid, pageModel.getStartIndex(), pageModel.getPageSize());
		pageModel.setList(list);
		
		// 分页对象的设置：url
		String url = "ProductServlet?method=findProductsByCidWithPage&cid=" + cid;
		pageModel.setUrl(url);
		return pageModel;
	}

}