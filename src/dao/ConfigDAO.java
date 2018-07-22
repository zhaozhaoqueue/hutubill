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
	//��ȡȫ����¼����
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
	
	//���������һ����¼
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
	
	//����һ����¼
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
	
	//�޸�һ����¼
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
	
	//ɾ��һ����¼
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
	
	//��ѯ����
	public List<Config> list(){
		return list(0, Short.MAX_VALUE);
	}
	
	//��ҳ��ѯ
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
