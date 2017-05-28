import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HTMLparsing {

	
	private static final Lock lock = new ReentrantLock();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void retrieveCrawler(String table)
	{
		String database="crawler";
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
	
	public void insertArticle(String table) throws Exception
	{
		String database="Article";
		lock.lock();
		try {
			Class.forName("org.sqlite.JDBC");
			
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"
					+ database);
			PreparedStatement prepared = conn
					.prepareStatement("insert into user (a_id, c_id, a_url, a_title, a_date) values (?1, ?2,?3,?4 ,?5);");//?=다이나믹하게 외부에서 정보를 준다.

			prepared.setString(1, "sparrow_a1@naver.com"); //문자
			prepared.setString(2, "Liver");
			prepared.setString(3, "123123");
			prepared.setString(3, "123123");
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
	
}
