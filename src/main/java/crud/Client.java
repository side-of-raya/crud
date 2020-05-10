package crud;

import java.sql.*;
import java.util.List;
public class Client {
  public Client() throws ClassNotFoundException, SQLException {
    Class.forName("org.mariadb.jdbc.Driver");
  }
  private PreparedStatement getPreparedStatement(String sql, List<Object> values, Connection con) throws Exception {
    PreparedStatement statement = con.prepareStatement(sql);
    int valueCount = 0;
		for( Object value: values ) {
      valueCount++;
			if( value instanceof Integer) {
        statement.setInt(valueCount, (int)value);
			} else if( value instanceof String) {
        statement.setString(valueCount, (String)value);
			} 			
    }
    return statement;
  }
  public int getAffected(String sql, List<Object> values) throws Exception
  {
    try (
      Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/walking", "god", "fser")
    ) {
        try (
          PreparedStatement pstmt = getPreparedStatement(sql, values, con)
        ) {
          int result = pstmt.executeUpdate();
          return result;
        } catch (Exception e) {
          System.out.println(e);
          return -1;
        }
    }
  }
  public int getAffected(String sql) throws Exception
  {
    try (
      Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/walking", "god", "fser")
    ) {
        try (
          Statement stmt = con.createStatement();
        ) {
          int result = stmt.executeUpdate(sql);
          return result;        
        } catch (Exception e) {
          System.out.println(e);
          return -1;
        }
      
    }
  }
  public ResultSet getRows(String sql, List<Object> values) throws Exception
  {
    try (
      Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/walking", "god", "fser")
    ) {
      try (
        PreparedStatement pstmt = getPreparedStatement(sql, values, con)
      ) {
        ResultSet result = pstmt.executeQuery();
        return result;
      } catch (Exception e) {
        System.out.println(e);
        return null;
      } 
      
    }
  }
  public ResultSet getRows(String sql) throws Exception
  {
    try (
      Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/walking", "god", "fser")
    ) {
      try (
        Statement stmt = con.createStatement();
      ) {
        ResultSet result = stmt.executeQuery(sql);
        return result;        
      } catch (Exception e) {
        System.out.println(e);
        return null;
      }
    }
  }
}