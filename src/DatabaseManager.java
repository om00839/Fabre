


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class DatabaseManager {
	
	
	private static final Lock lock = new ReentrantLock();
	String database;
	
	public DatabaseManager()
	{
		database = "D:\\workspace\\Fabre\\web.db"; //패스 수정을 해줘야 한다.
		//database = "web.db" 디폴트 삭제;
	}
	
	public void createUser(String table)
	{
		lock.lock();
		try {
			Class.forName("org.sqlite.JDBC"); //패키지 폴더 web-inf lib에 있다.

			// if database not exist
			//if (!(new File(database).isFile())) {
				Statement stmt = null;
				try {
					Connection conn = DriverManager
							.getConnection("jdbc:sqlite:" + database); //ㅅ생성자에 위치 나온다
					stmt = conn.createStatement(); //스테이트먼트를 데이터베이스에 보내
					String sql = "CREATE TABLE " + table //컬럼 5개
							+ "(u_email char(100) primary key, "
							+ "u_nickname char(100) not null,"
							+ "u_password char(100) not null);";
					stmt.executeUpdate(sql); //실행을 하는것 
					stmt.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
					
				System.out.println("Table created successfully");
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertUser(String table) throws Exception
	{
		lock.lock();
		try {
			Class.forName("org.sqlite.JDBC");
			
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"
					+ database);
			PreparedStatement prepared = conn
					.prepareStatement("insert into user (u_email,u_nickname, u_password) values (?1, ?2,?3);");//?=다이나믹하게 외부에서 정보를 준다.

			prepared.setString(1, "sparrow_a1@naver.com"); //문자
			prepared.setString(2, "Liver");
			prepared.setString(3, "123123");
			prepared.addBatch();

			conn.setAutoCommit(false);
			prepared.executeBatch();
			
			prepared.close();
		    conn.commit();
		    conn.close();
		      
		} finally {
			lock.unlock();
		}
	}
	
	public void retrieveUser(String table)
	{
		    Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      Connection conn = DriverManager.getConnection("jdbc:sqlite:"
						+ database);
		      conn.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery( "select * from " + table + ";" );
		      while ( rs.next() ) {
		         String u_email=rs.getString("u_email");
		         String u_nickname=rs.getString("u_nickname");
		         String u_password = rs.getString("u_password");
		         System.out.println("u_email : "+u_email+"u_nickname : "+u_nickname);
		      }
		      
		      rs.close();
		      stmt.close();
		      conn.close();
		    } catch ( Exception e ) {
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		        System.exit(0);
		    }
		      
		    System.out.println("Operation done successfully");
		    
	}
	
	
	
	
	
	
	

	public void createCrawler(String table)
	{
		lock.lock();
		try {
			Class.forName("org.sqlite.JDBC"); //패키지 폴더 web-inf lib에 있다.

			// if database not exist
			//if (!(new File(database).isFile())) {
				Statement stmt = null;
				try {
					Connection conn = DriverManager
							.getConnection("jdbc:sqlite:" + database); //ㅅ생성자에 위치 나온다
					stmt = conn.createStatement(); //스테이트먼트를 데이터베이스에 보내
					String sql = "CREATE TABLE " + table //컬럼 5개
							+ "(c_id char(100) primary key, "
							+ "c_url char(100) not null,"
							+ "c_name char(100) not null);";
					stmt.executeUpdate(sql); //실행을 하는것 
					stmt.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
					
				System.out.println("Table created successfully");
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertCrawler(String table) throws Exception
	{
		lock.lock();
		try {
			Class.forName("org.sqlite.JDBC");
			
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"
					+ database);
			PreparedStatement prepared = conn
					.prepareStatement("insert into crawler (c_id, c_url, c_name) values (?1, ?2,?3);");//?=다이나믹하게 외부에서 정보를 준다.

			prepared.setString(1, "1"); //문자
			prepared.setString(2, "https://recruit.imbc.com/company/recruit/rnotice/list.aspx");
			prepared.setString(3, "MBC");
			prepared.addBatch();

			conn.setAutoCommit(false);
			prepared.executeBatch();
			
			prepared.close();
		    conn.commit();
		    conn.close();
		      
		} finally {
			lock.unlock();
		}
	}
	
	public void retrieveCrawler(String table)
	{
		    Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      Connection conn = DriverManager.getConnection("jdbc:sqlite:"
						+ database);
		      conn.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery( "select * from " + table + ";" );
		      while ( rs.next() ) {
		         String c_id=rs.getString("c_id");
		         String c_url=rs.getString("c_url");
		         String c_name = rs.getString("c_name");
		         System.out.println("c_id : "+c_id+"c_url :"+c_url+"c_name : "+c_name);
		      }
		      
		      rs.close();
		      stmt.close();
		      conn.close();
		    } catch ( Exception e ) {
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		        System.exit(0);
		    }
		      
		    System.out.println("Operation done successfully");
		    
	}
	
	
	
	
	
	
	
	
	

	public void createArticle(String table)
	{
		lock.lock();
		try {
			Class.forName("org.sqlite.JDBC"); //패키지 폴더 web-inf lib에 있다.

			// if database not exist
			//if (!(new File(database).isFile())) {
				Statement stmt = null;
				try {
					Connection conn = DriverManager
							.getConnection("jdbc:sqlite:" + database); //ㅅ생성자에 위치 나온다
					stmt = conn.createStatement(); //스테이트먼트를 데이터베이스에 보내
					String sql = "CREATE TABLE " + table //컬럼 5개
							+ "(a_id char(100) primary key, "
							+ "c_id char(100) references crawler(c_id),"
							+ "a_url char(100) not null,"
							+ "a_title char(100) not null,"
							+ "a_date char(100) not null);";
					stmt.executeUpdate(sql); //실행을 하는것 
					stmt.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
					
				System.out.println("Table created successfully");
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertArticle(String table) throws Exception
	{
		lock.lock();
		try {
			Class.forName("org.sqlite.JDBC");
			
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"
					+ database);
			PreparedStatement prepared = conn
					.prepareStatement("insert into article (a_id,c_id, a_url, a_title, a_date) values (?1, ?2,?3,?4 ,?5);");//?=다이나믹하게 외부에서 정보를 준다.

			prepared.setString(1, "10"); //문자
			prepared.setString(2, "1");
			prepared.setString(3, "sdasd");
			prepared.setString(4, "채용");
			prepared.setString(5, "2017.2");
			prepared.addBatch();

			conn.setAutoCommit(false);
			prepared.executeBatch();
			
			prepared.close();
		    conn.commit();
		    conn.close();
		      
		} finally {
			lock.unlock();
		}
	}
	
	public void retrieveArticle(String table)
	{
		    Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      Connection conn = DriverManager.getConnection("jdbc:sqlite:"
						+ database);
		      conn.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery( "select * from " + table + ";" );
		      while ( rs.next() ) {
		         String a_id=rs.getString("a_id");
		         String c_id=rs.getString("c_id");
		         String a_url = rs.getString("a_url");
		         String a_title = rs.getString("a_title");
		         String a_date = rs.getString("a_date");

		         System.out.println("a_id : "+a_id+" c_id "+c_id+" a_url : "+a_url+" a_title "+a_title+" a_date : "+a_date);
		      }
		      rs.close();
		      stmt.close();
		      conn.close();
		    } catch ( Exception e ) {
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		        System.exit(0);
		    }
		      
		    System.out.println("Operation done successfully");
		    
	}
	
	
	
	
	public void createUC_Setting(String table)
	{
		lock.lock();
		try {
			Class.forName("org.sqlite.JDBC"); //패키지 폴더 web-inf lib에 있다.

			// if database not exist
			//if (!(new File(database).isFile())) {
				Statement stmt = null;
				try {
					Connection conn = DriverManager
							.getConnection("jdbc:sqlite:" + database); //ㅅ생성자에 위치 나온다
					stmt = conn.createStatement(); //트를 데이터베이스에 보내
					String sql = "CREATE TABLE " + table //컬럼 5개
							+ "(u_email char(100) references user(u_email), "
							+ "c_id char(100) references crawler(c_id),"
							+ "uc_favorite char(100) not null,"
							+ "uc_keywords char(100) not null);";
					stmt.executeUpdate(sql); //실행을 하는것 
					stmt.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
					
				System.out.println("Table created successfully");
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertUC_Setting(String table) throws Exception
	{
		lock.lock();
		try {
			Class.forName("org.sqlite.JDBC");
			
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"
					+ database);
			PreparedStatement prepared = conn
					.prepareStatement("insert into UC_Setting (u_email, c_id, uc_favorite, uc_keywords) values (?1, ?2,?3,?4);");//?=다이나믹하게 외부에서 정보를 준다.

			prepared.setString(1, "sparrow_a1@naver.com"); //문자
			prepared.setString(2, "1");
			prepared.setString(3, "1");
			prepared.setString(4, "1");
			prepared.addBatch();

			conn.setAutoCommit(false);
			prepared.executeBatch();
			
			prepared.close();
		    conn.commit();
		    conn.close();
		      
		} finally {
			lock.unlock();
		}
	}
	
	public void retrieveUC_Setting(String table)
	{
		    Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      Connection conn = DriverManager.getConnection("jdbc:sqlite:"
						+ database);
		      conn.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery( "select * from " + table + ";" );
		      while ( rs.next() ) {
		    	 String u_email=rs.getString("u_email");
		    	 String c_id=rs.getString("c_id");
		         String uc_favorite=rs.getString("uc_favorite");
		         String uc_keywords = rs.getString("uc_keywords");
		         System.out.println("u_email : "+u_email+"c_id : "+c_id+"uc_favorite :"+uc_favorite+"uc_keywords : "+uc_keywords);
		      }
		      
		      rs.close();
		      stmt.close();
		      conn.close();
		    } catch ( Exception e ) {
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		        System.exit(0);
		    }
		      
		    System.out.println("Operation done successfully");
		    
	}
	
	
	
//	public void retrieve(String table,String param1)
//	{
//		
//		
//		    Statement stmt = null;
//		    PreparedStatement ps = null;
//			Connection conn = null;
//			ResultSet rs = null;
//		    try {
//			      Class.forName("org.sqlite.JDBC");
//			      conn = DriverManager.getConnection("jdbc:sqlite:"
//							+ database);
//			      conn.setAutoCommit(false);
//			      
//			      String query="select a_url, a_title, a_date from " + table + " where c_id  IN (select distinct c_id from UC_Setting where u_email = ?)";
//				
//			      ps = conn.prepareStatement( query );
//			      
//			      ps.setString(1, param1);
//			      
//			      rs = ps.executeQuery(); //select * from article where c_id IN (select distinct c_id from UC_Setting where  //parameter(u_email))  
//
//			      while (rs.next()) {
////			         String a_id=rs.getString("a_id");
////			         String c_id=rs.getString("c_id");
//			         String a_url = rs.getString("a_url");
//			         String a_title = rs.getString("a_title");
//			         String a_date = rs.getString("a_date");
//
//			         System.out.println(a_url +" , "+a_title+" , "+a_date+" , ");
//			      }
//			      rs.close();
//			      conn.close();
//			    } catch ( Exception e ) {
////			        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
////			        System.exit(0);
//			    }
//		      
//		    System.out.println("Operation done successfully");
//		    
//	}
	
	
	
	public static void main(String[] args) throws Exception
	{
		
		String table = "user";
		DatabaseManager dm = new DatabaseManager();
		
//		create tableLO
//		dm.createUser(table);
		
//		insert data
//		dm.insertUser(table);
		
		//retrieve data
//		dm.retrieveUser(table);
		
		

		
		
//create Crawler table;		
//		table="crawler";
//		dm.createCrawler(table);
		
//		dm.insertCrawler(table);
		
//		dm.retrieveCrawler("crawler");
		
		
		
		//create Article table;
		table="article";		
//		dm.retrieve(table, "sparrow_a1@naver.com");

		//		dm.createArticle(table);
		
//		dm.insertArticle(table);
		
//		dm.retrieveArticle("Article");

		
		
		//create UC_Setting table;
//		table="UC_Setting";		
		
//		dm.createUC_Setting(table);
		
//		dm.insertUC_Setting(table);
		
//		dm.retrieveUC_Setting(table);	
	}

}








