package src.Servlet;

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
		database = "F:\\workspace\\Fabre\\web.db"; //패스 수정을 해줘야 한다.
		//database = "web.db" 디폴트 삭제;
	}
	
	public void createUserTable(String table)
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
							+ "(user_ID char(100) unique, "
							+ "Nick_Name char(100) not null,"
							+ "password char(100) not null,"
							+ "passwordCheck char(100) not null);";
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
	
	public void insertUserTable(String table) throws Exception
	{
		lock.lock();
		try {
			Class.forName("org.sqlite.JDBC");
			
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"
					+ database);
			PreparedStatement prepared = conn
					.prepareStatement("insert into user (user_ID,Nick_Name, password,passwordCheck) values (?1, ?2,?3,?4);");//?=다이나믹하게 외부에서 정보를 준다.

			prepared.setString(1, "sparrow_a1@naver.com"); //문자
			prepared.setString(2, "Liver");
			prepared.setString(3, "123123");
			prepared.setString(4, "123123");
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
		         String user_ID=rs.getString("user_ID");
		         String Nick_Name=rs.getString("Nick_Name");
		         String password = rs.getString("password");
		         String passwordCheck = rs.getString("passwordCheck");
		         System.out.println("User Id : "+user_ID+"Nick_Name : "+Nick_Name+" password : "+password+"passwordCheck : "+passwordCheck);
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
	
	public void createHomepageTable()
	{
		lock.lock();
		try {
			Class.forName("org.sqlite.JDBC");

			// if database not exist
			//if (!(new File(database).isFile())) {
				Statement stmt = null;
				try {
					Connection conn = DriverManager
							.getConnection("jdbc:sqlite:" + database);
					stmt = conn.createStatement();
					String sql = "CREATE TABLE product" 
							+ "( product_id integer primary key autoincrement, "
							+ "description char(300) default null, "
							+ "name char(100) default null, "
							+ "image char(100) default null,"
							+ "price float default null );";
					stmt.executeUpdate(sql);
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
	
	public void insertHomepageTable() throws Exception
	{
		lock.lock();
		try {
			Class.forName("org.sqlite.JDBC");
			
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"+database);
			PreparedStatement prepared = conn
					.prepareStatement("insert into product (description, name, "
							+ "image, price) values (?1, ?2, ?3, ?4);");

			prepared.setString(1, "pinn");
			prepared.setString(2, "Jake");
			prepared.setString(3, "images/image2.jpg");
			prepared.setFloat(4, (float) 4.15);
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
	
	public void retrieveHomepage(String table)
	{
		    Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      Connection conn = DriverManager.getConnection("jdbc:sqlite:"+database);
		      conn.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery( "select * from " + table + ";" );
		      while ( rs.next() ) {
		         int id = rs.getInt("product_id");
		         String description = rs.getString("description");
		         String name = rs.getString("name");
		         String image = rs.getString("image");
		         float price = rs.getFloat("price");
		         
		         System.out.println("Desc " + description + " Name " + name);
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
	public static void main(String[] args) throws Exception
	{
		
		String table = "user";
		DatabaseManager dm = new DatabaseManager();
		
//		create table
//		dm.createUserTable(table);
		
//		insert data
//		dm.insertUserTable(table);
		
		//retrieve data
		dm.retrieveUser(table);
		
		//create prodcut table;
//		dm.createProductTable();
		
//		dm.insertProductTable();
		
//		dm.retrieveProduct("product");
	}

}








