package com.leehao.mall.dao.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.leehao.mall.dao.CategoryDao;
import com.leehao.mall.domain.Category;
import com.leehao.mall.utils.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> getAllCats() throws SQLException {
		// 调用dao
		String sql = "Select * from category";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		List<Category> list = queryRunner.query(sql, new BeanListHandler<Category>(Category.class));
		// System.out.println(list.size());
		return list;
	}

	@Override
	public void addCategory(Category category) throws Exception {
		//
		String sql = "insert into category values(?,?)";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		queryRunner.update(sql, category.getCid(), category.getCname());
	}

	@Override
	public Category findCategoryById(String cid) throws Exception {
		String sql = "select * from category where cid = ?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		return queryRunner.query(sql, new BeanHandler<Category>(Category.class), cid);
	}

	@Override
	public void editCategory(Category category) throws Exception {
		String sql = "update category set cname=? where cid=?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		queryRunner.update(sql, category.getCname(), category.getCid());
	}

	@Override
	public void deleteCategoryById(String cid) throws Exception {
		//将商品参照的cid置为空
		String sql = "update product set cid = null where cid = ?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());		
		queryRunner.update(sql, cid);
		
		//删除分类
		sql = "delete from category where cid = ?";
		queryRunner.update(sql, cid);
	}
	
	

}
