package crud;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class App
{
  public static void main(String[] args) throws Exception
  {
    System.out.println("startuem!!1!1!!");
  }
  public int createSub (String name, int age) throws Exception
  {
    Client client = new Client();
    List<Object> values = new ArrayList<Object>();
    values.add(name);
    values.add(age);
    int result = client.getAffected("INSERT INTO men (name, age) VALUES (?, ?)", values);
    return result;
  }
  public int deleteSub (Integer id) throws Exception
  {
    Client client = new Client();
    List<Object> values = new ArrayList<Object>();
    values.add(id);
    int result = client.getAffected("DELETE FROM men WHERE id = ?", values);
    return result;
  }
  public List<Sub> selectSubs() throws Exception
  {
    Client client = new Client();
    Sub sub = new Sub();
    List<Sub> result = new LinkedList<>();
    ResultSet rs = client.getRows("SELECT * FROM men");
    while (rs.next()) {
      sub.name = rs.getString("name");
      sub.age = rs.getInt("age");
      result.add(sub);
    }
    return result;
  }
  public int updateSub (int id, UpdateArgs[] args) throws Exception
  {
    if (args.length == 0) return -1;
    Client client = new Client();
    String query = "UPDATE men SET ";
    List<Object> values = new ArrayList<>();
    for (UpdateArgs arg: args) {
      query += arg.field + " = ?,";
      values.add(arg.value);
    }
    query = query.substring(0, query.length() - 1);
    query += " WHERE id = ?;";
    values.add(id);
    int result = client.getAffected(query, values);
    return result;
  }
}
