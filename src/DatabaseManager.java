


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Fabre.Bean.ArticleBean;
import Fabre.Bean.CrawlerBean;
import Fabre.Bean.UC_SettingBean;
import Fabre.Bean.UserBean;

public class DatabaseManager {

	private static final Lock lock = new ReentrantLock();
	private static final String database = "C:\\workspace\\Fabre\\web.db";
	private Connection conn;

	
	public DatabaseManager() throws ClassNotFoundException, SQLException {
		// database = "web.db" 디폴트 삭제;
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:" + database);
		conn.setAutoCommit(false);

		// conn.close(); 항상 해줄것.
	}

	public void insertUser(UserBean user) throws Exception {

		// registrationServlet에 사용

		lock.lock();

		try {

			PreparedStatement prepared = conn
					.prepareStatement("insert into user (u_email, u_nickname, u_password) values (?1,?2,?3);");
			prepared.setString(1, user.getU_email()); // 문자
			prepared.setString(2, user.getU_nickname());
			prepared.setString(3, user.getU_password());

			prepared.addBatch();
			prepared.executeBatch();
			prepared.close();

			conn.commit();

		} catch (Exception e) {
			conn.close();
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public UserBean retrieveUser(String u_email) throws SQLException {

		// loginServlet에 사용

		UserBean user = new UserBean();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "select * from user where u_email = ?";
			ps = conn.prepareStatement(query);

			ps.setString(1, u_email);

			rs = ps.executeQuery();

			while (rs.next()) {

				user.setU_email(rs.getString("u_email"));
				user.setU_nickname(rs.getString("u_nickname"));
				user.setU_password(rs.getString("u_password"));

			}

			rs.close();

		} catch (Exception e) {
			conn.close();
			e.printStackTrace();
			// System.err.println( e.getClass().getName() + ": " +
			// e.getMessage() );
			// System.exit(0);
		}
		System.out.println("Operation done successfully");

		return user;
	}

	
//	public boolean retrieveUser1(String u_email) throws SQLException {
//
//		// loginServlet에 사용
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		boolean unduplicated = false;
//
//		try {
//			String query = "select * from user where u_email =?";
//
//			ps = conn.prepareStatement(query);
//
//			ps.setString(1, u_email);
//
//			rs = ps.executeQuery();
//
//			if (!rs.next()) {
//				unduplicated = true;
//			}
//
//			rs.close();
//
//		} catch (Exception e) {
//			conn.close();
//			e.printStackTrace();
//		}
//
//		System.out.println("Operation done successfully");
//
//		return unduplicated;
//	}

	
	public ArrayList<CrawlerBean> retrieveDisplay_C(UserBean user) throws SQLException {

		ArrayList<CrawlerBean> cList = new ArrayList();
		CrawlerBean crawler;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "select distinct c_id, c_name, c_url from display where u_email = ?";

			ps = conn.prepareStatement(query);

			ps.setString(1, user.getU_email());

			rs = ps.executeQuery();

			while (rs.next()) {

				crawler = new CrawlerBean();

				crawler.setC_id(rs.getInt("c_id"));
				crawler.setC_name(rs.getString("c_name"));
				crawler.setC_url(rs.getString("c_url"));

				cList.add(crawler);

			}

			rs.close();

		} catch (Exception e) {
			conn.close();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}

		return cList;

	}

	public ArrayList<ArticleBean> retrieveDisplay_A(UserBean user) throws SQLException {

		// display에 사용

		ArrayList<ArticleBean> aList = new ArrayList<ArticleBean>();
		ArticleBean article;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "select a_id, a_url, a_title, a_date from display where u_email = ?;";

			ps = conn.prepareStatement(query);
			ps.setString(1, user.getU_email());

			rs = ps.executeQuery();

			while (rs.next()) {

				article  = new ArticleBean();
				

				article.setA_id(rs.getInt("a_id"));
				article.setA_url(rs.getString("a_url"));
				article.setA_title(rs.getString("a_title"));
				article.setA_date(rs.getString("a_date"));

				aList.add(article);

			}
			rs.close();

		} catch (Exception e) {

			conn.close();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}

		return aList;

	}

	// 이거 수정

	public void close() throws SQLException {
		// 중요 항상 해줄것
		conn.close();
	}

	public ArrayList<CrawlerBean> retrieveCrawler(String keyword) throws SQLException {

		ArrayList<CrawlerBean> cList = new ArrayList<CrawlerBean>();

		CrawlerBean crawler = new CrawlerBean();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "select * from crawler " + "where c_name = ?;";
//			 or c_url = ? 
			ps = conn.prepareStatement(query);
			ps.setString(1, keyword);

			rs = ps.executeQuery();

			while (rs.next()) {

				crawler.setC_id(rs.getInt("c_id"));
				crawler.setC_url(rs.getString("c_url"));
				crawler.setC_name(rs.getString("c_name"));

				cList.add(crawler);

			}
			rs.close();

		} catch (Exception e) {

			conn.close();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}

		return cList;
	}

	public void deleteUC_setting(String u_email, int c_id) throws SQLException {

		UC_SettingBean setting = new UC_SettingBean();

		lock.lock();

		try {

			PreparedStatement prepared = conn.prepareStatement("delete from UC_Setting where c_id= ? and u_email=?");

			prepared.setInt(1, c_id); // e_mail을 어떻게 자동적으로 할까.
			prepared.setString(2, u_email); // e_mail을 어떻게 자동적으로 할까.

			prepared.addBatch();
			prepared.executeBatch();
			prepared.close();

			conn.commit();

		} catch (Exception e) {
			conn.close();
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void updateUC_Setting(String u_email, int c_id, Boolean uc_favorite) throws SQLException {
		
		
		lock.lock();

		try {

			PreparedStatement prepared = conn
					.prepareStatement("update user set uc_favorite = ?" + " where u_email= ? and c_id=?");

			prepared.setBoolean(1, uc_favorite); // e_mail을 어떻게 자동적으로 할까.
			prepared.setString(2, u_email); // e_mail을 어떻게 자동적으로 할까.
			prepared.setInt(3, c_id); // e_mail을 어떻게 자동적으로 할까.

			prepared.addBatch();
			prepared.executeBatch();
			prepared.close();

			conn.commit();

		} catch (Exception e) {
			conn.close();
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void insertUC_Setting(String u_email, int c_id) throws SQLException {

		// registrationServlet에 사용
		lock.lock();

		try {

			PreparedStatement prepared = conn.prepareStatement("insert into uc_setting (u_email, c_id) values (?1,?2);");
			prepared.setString(1, u_email); // 문자
			prepared.setInt(2, c_id);

			prepared.addBatch();
			prepared.executeBatch();
			prepared.close();

			conn.commit();

		} catch (Exception e) {
			conn.close();
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void updateUser(String u_password, String u_email) throws SQLException {

		lock.lock();
		UserBean user = new UserBean();
		try {

			PreparedStatement prepared = conn.prepareStatement("update user set u_password = ? where u_email= ?");
			prepared.setString(1, u_password);
			prepared.setString(2, u_email); // e_mail을 어떻게 자동적으로 할까.
			// prepared.setString(2, user.getU_nickname());

			prepared.addBatch();
			prepared.executeBatch();
			prepared.close();

			conn.commit();

		} catch (Exception e) {
			conn.close();
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		DatabaseManager dm = new DatabaseManager();

		// PreparedStatement prepared = dm.conn
		// .prepareStatement("insert into crawler (c_name , c_url) values
		// (\"yonsei\", \"www.yonsei.ac.kr\");");
		//// .prepareStatement("insert into article (a_title , a_url, a_date)
		// values (\"yonsei\", \"www.yonsei.ac.kr\", \"20170529\");");
		// prepared.addBatch();
		// prepared.executeBatch();
		// prepared.close();

		UserBean user = new UserBean();
		user.setU_email("sparrow_a1@naver.com");
		user.setU_password("123123");
		user.setU_nickname("Liver");
		
		try{
			
			System.out.println(user.getU_email());
			
			ArrayList<CrawlerBean> al = dm.retrieveDisplay_C(user);
			System.out.println(al.size());
			
			
			
			for (int i = 0; i<al.size(); i++){
				
				CrawlerBean c = new CrawlerBean();
				c = al.get(i);				
				System.out.println(c.getC_id());
			}
			
		}catch(Exception e ){
			e.printStackTrace();
		}
		

	}

}
