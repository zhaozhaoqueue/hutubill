package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entity.Category;
import entity.Record;

import java.sql.Connection;

import util.DBUtil;

public class CategoryDAO {
	int total = 0;
	public int getTotal() {
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "select count(*) from category";
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				total = rs.getInt(1);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	//增加一条记录
	public void add(Category category) {
		String sql = "insert into category values(null, ?)";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, category.name);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
				category.id = rs.getInt(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//删除一条记录
	public void delete (int id) {
		String sql = "delete from category where id=?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, id);
			ps.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//修改一条记录
	public void update(Category category) {
		String sql = "update category set name=? where id=?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, category.name);
			ps.setInt(2, category.id);
			ps.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//查询一条记录
	public Category get(int id) {
		Category category = null;
		String sql = "select * from category where id=?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				category = new Category();
				category.id = rs.getInt(1);
				category.name = rs.getString("name");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	
	//查询所有记录
	public List<Category> list(){
		return list(0, Short.MAX_VALUE);
	}
	
	//分页查询
	public List<Category> list(int start, int count){
		String sql = "select * from category order by id desc limit ?,?";
		List<Category> cs = new ArrayList<Category>();
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			
			RecordDAO rDao = new RecordDAO();
			while(rs.next()) {
				Category category = new Category();
				category.id = rs.getInt(1);
				category.name = rs.getString("name");
				
				List<Record> records = rDao.list(rs.getInt(1));
				category.recordNumber = records.size();
				
				cs.add(category);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return cs;
	}
}
