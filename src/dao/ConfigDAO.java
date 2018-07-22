package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import entity.Config;
import util.DBUtil;

public class ConfigDAO {
	//获取全部记录数量
	public int getTotal() {
		int total = 0;
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "select count(*) from config ";
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				total = rs.getInt(1);
			}
			System.out.println("total: " + total);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	//向表中增加一条记录
	public void add(Config config) {
		String sql = "insert into config values(null, ?, ?)";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, config.key);
			ps.setString(2, config.value);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				config.id = rs.getInt(1);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//查找一条记录
	public Config get(int id) {
		Config config = null;
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "select * from config where id= " + id;
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()){
				config = new Config();
				config.id = id;
				config.key = rs.getString("key_");
				config.value = rs.getString("value");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return config;
	}
	
	//修改一条记录
	public void update(Config config) {
		String sql = "update config set key_=?, value=? where id=?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, config.key);
			ps.setString(2, config.value);
			ps.setInt(3, config.id);
			ps.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//删除一条记录
	public void delete(int id) {
		String sql = "delete from config where id=?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, id);
			ps.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//查询所有
	public List<Config> list(){
		return list(0, Short.MAX_VALUE);
	}
	
	//分页查询
	public List<Config> list(int start, int count){
		List<Config> cs = new ArrayList<Config>();
		String sql = "select * from config order by id desc limit ?, ?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Config config = new Config();
				config.id = rs.getInt(1);
				config.key = rs.getString("key_");
				config.value = rs.getString("value");
				cs.add(config);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return cs;
	}
	
	public Config getByKey(String key) {
		Config config = null;
		String sql = "select * from config where key_=?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				config = new Config();
				config.id = rs.getInt(1);
				config.key = key;
				config.value = rs.getString("value");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return config;
	}
}
