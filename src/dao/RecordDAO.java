package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Date;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import entity.Record;
import util.DBUtil;
import util.DateUtil;

public class RecordDAO {
	public int getTotal() {
		int total = 0;
		try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement();){
			String sql = "select count(*) from record";
			ResultSet rs = s.executeQuery(sql);
			while(rs.next())
				total = rs.getInt(1);
			System.out.println("total: " + total);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public void add(Record record) {
		String sql = "insert into record values(null, ?, ?, ?, ?)";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, record.spend);
			ps.setInt(2, record.cid);
			ps.setString(3, record.comment);
			ps.setDate(4, DateUtil.util2sql(record.date));
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next())
				record.id = rs.getInt(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		String sql = "delete from record where id=?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, id);
			ps.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Record get(int id) {
		Record record = null;
		String sql = "select * from record where id=?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				record = new Record();
				record.id = id;
				record.cid = rs.getInt("cid");
				record.comment = rs.getString("comment");
				record.spend = rs.getInt("spend");
				
				//获取cid的名字
				record.category = new CategoryDAO().get(rs.getInt("cid")).getName();
				
				//record.date = rs.getDate("date");
				//保险起见
				Date date = rs.getDate("date");
				record.date = date;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return record;
	}
	
	public void update(Record record) {
		String sql = "update record set spend=?, cid=?, comment=?, date=? where id=?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, record.spend);
			ps.setInt(2, record.cid);
			ps.setString(3, record.comment);
			ps.setDate(4, DateUtil.util2sql(record.date));
			ps.setInt(5, record.id);
			ps.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public List<Record> list(){
		return list(0, Short.MAX_VALUE);
	}
	
	public List<Record> list(int start, int count){
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record order by id desc limit ?, ?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Record record = new Record();
				record.id = rs.getInt(1);
				record.cid = rs.getInt("cid");
				record.comment = rs.getString("comment");
				record.spend = rs.getInt("spend");
				Date date = rs.getDate("date");
				record.date = date;
				//获取cid的名字
				record.category = new CategoryDAO().get(rs.getInt("cid")).getName();
				records.add(record);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return records;
	}
	
	public List<Record> list(int cid){
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record where cid=?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1,  cid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Record record = new Record();
				record.id = rs.getInt(1);
				record.cid = cid;
				record.spend = rs.getInt("spend");
				record.comment = rs.getString("comment");
				Date date = rs.getDate("date");
				record.date = date;
				//获取cid的名字
				record.category = new CategoryDAO().get(cid).getName();
				records.add(record);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return records;
	}
	
	//获取本月的消费记录
	public List<Record> listThisMonth(){
		return list(DateUtil.monthBegin(), DateUtil.monthEnd());
	}
	
	//获取某个时间段内的记录
		public List<Record> list(Date start, Date end){
			List<Record> records = new ArrayList<Record>();
			String sql = "select * from record where date>=? and date<=?";
			try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
				ps.setDate(1, DateUtil.util2sql(start));
				ps.setDate(2, DateUtil.util2sql(end));
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Record record = new Record();
					record.id = rs.getInt(1);
					record.cid = rs.getInt("cid");
					record.comment = rs.getString("comment");
					Date date = rs.getDate("date");
					record.date = date;
					record.spend = rs.getInt("spend");
					//获取cid的名字
					record.category = new CategoryDAO().get(rs.getInt("cid")).getName();
					records.add(record);
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return records;
		}
	
	//获取某天的消费记录
	public List<Record> list(Date day){
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record where date=?";
		try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			ps.setDate(1, DateUtil.util2sql(day));
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Record record = new Record();
				record.id = rs.getInt(1);
				record.cid = rs.getInt("cid");
				record.comment = rs.getString("comment");
				record.spend = rs.getInt("spend");
				Date date = rs.getDate("date");
				record.date = date;
				//获取cid的名字
				record.category = new CategoryDAO().get(rs.getInt("cid")).getName();
				records.add(record);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return records;
	}
	
	//获取今天的消费记录
	public List<Record> listToday(){
		return list(DateUtil.today());
	}
}
