package com.leehao.mall.dao.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.leehao.mall.dao.ProductDao;
import com.leehao.mall.domain.Product;
import com.leehao.mall.utils.JDBCUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> findNews() throws SQLException {
		// pflag=0,有货
		String sql = "Select * from product where pflag=0 order by pdate desc limit 0, 9";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		return queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> findHots() throws SQLException {
		// pflag=0,有货, is_hot=1最热
		String sql = "Select * from product where pflag=0 and is_hot=1 order by pdate desc limit 0, 9";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		return queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public Product finProductById(String pid) throws Exception {
		String sql = "Select * from product where pid=?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		return queryRunner.query(sql, new BeanHandler<Product>(Product.class), pid);
	}

	@Override
	public int findTotalRecords(String cid) throws Exception {
		String sql = "Select count(*) from product where cid=?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		Long total = (Long) queryRunner.query(sql, new ScalarHandler(), cid); // 返回long对象，num.intValue()
		return total.intValue();
	}

	@Override
	public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception {
		String sql = "Select * from product where cid=? limit ?, ?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		return queryRunner.query(sql, new BeanListHandler<Product>(Product.class), cid, startIndex, pageSize);
	}

	@Override
	public int findAllRecords() throws Exception {
		int pflag = 0;
		String sql = "Select count(*) from product where pflag=?";    //有货商品
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		Long record =  (Long) queryRunner.query(sql, new ScalarHandler(), pflag);
		return record.intValue();
	}

	@Override
	public List<Product> findAllProductsWithPage(int startIndex, int pageSize) throws Exception {
		int pflag = 0;
		String sql = "Select * from product where pflag=? order by pdate desc limit ?, ? ";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		return queryRunner.query(sql, new BeanListHandler<Product>(Product.class), pflag, startIndex, pageSize);
	}

	@Override
	public void saveProduct(Product product) throws Exception {
		String sql="INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid()};
		qr.update(sql,params);
	}
	
	

}
