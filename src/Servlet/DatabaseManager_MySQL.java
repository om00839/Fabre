package src.Servlet;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import src.Bean.ArticleBean;
import src.Bean.CrawlerBean;
import src.Bean.UC_SettingBean;
import src.Bean.UserBean;

public class DatabaseManager_MySQL {
	
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    private UserBean user;
	private CrawlerBean crawler;
	private UC_SettingBean uc_setting;
	private ArticleBean article;

	private static final Lock lock = new ReentrantLock();
    
    private String database = "jdbc:mysql://DESKTOP-E6CHFR4:3306/database";
    //jdbc:mysql://[host1][:port1][/[database]
    
    DatabaseManager_MySQL() throws ClassNotFoundException, SQLException {
    	
    	UserBean user = new UserBean();
    	CrawlerBean crawler = new CrawlerBean();
    	UC_SettingBean uc_setting = new UC_SettingBean();
    	ArticleBean article = new ArticleBean();
    	
    	 // This will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = (Connection) DriverManager
                .getConnection(database);
    	
    }
    
    public void insertUser(UserBean user){
    	this.user = user;
    }
    
    public void insertCrawler(CrawlerBean crawler){
    	this.crawler=crawler;
    	
    }
    
    public void insertArticle(ArticleBean article){
    	this.article=article;
    	
    }
    
    public UserBean retrieveUser(){
		return user; 
    	
    }
    
    public CrawlerBean retrieveCrawler(){
		return crawler;
    }
    
    public void updateUser(UserBean user, String[] attributes){
    	
    	this.user = user;
    	
    }
    
    public void updateCrawler(CrawlerBean crawler, String[] attributes){
    	this.crawler = crawler;
    }
    
    public void updateUC_Setting(UC_SettingBean uc_setting, String[] attributes){
    	this.uc_setting = uc_setting;
    }
    
    public void updateArticle(ArticleBean article, String[] attributes){
    	this.article = article;
    }

    public void readDataBase() throws Exception {
        try {
           
        	
            // Statements allow to issue SQL queries to the database
            statement = (Statement) connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from feedback.comments");
            writeResultSet(resultSet);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = (PreparedStatement) connect
                    .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1
            preparedStatement.setString(1, "Test");
            preparedStatement.setString(2, "TestEmail");
            preparedStatement.setString(3, "TestWebpage");
            preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
            preparedStatement.setString(5, "TestSummary");
            preparedStatement.setString(6, "TestComment");
            preparedStatement.executeUpdate();

            preparedStatement = (PreparedStatement) connect
                    .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            resultSet = preparedStatement.executeQuery();
            writeResultSet(resultSet);

            // Remove again the insert comment
            preparedStatement = (PreparedStatement) connect
            .prepareStatement("delete from feedback.comments where myuser= ? ; ");
            preparedStatement.setString(1, "Test");
            preparedStatement.executeUpdate();

            resultSet = statement
            .executeQuery("select * from feedback.comments");
            writeMetaData(resultSet);

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String user = resultSet.getString("myuser");
            String website = resultSet.getString("webpage");
            String summary = resultSet.getString("summary");
            Date date = resultSet.getDate("datum");
            String comment = resultSet.getString("comments");
            System.out.println("User: " + user);
            System.out.println("Website: " + website);
            System.out.println("summary: " + summary);
            System.out.println("Date: " + date);
            System.out.println("Comment: " + comment);
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }


}
