package com.leehao.mall.dao;

import java.sql.SQLException;
import java.util.List;

import com.leehao.mall.domain.Product;

public interface ProductDao {

	List<Product> findNews() throws SQLException;

	List<Product> findHots() throws SQLException;

	Product finProductById(String pid)throws Exception;

}
