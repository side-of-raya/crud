package crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Before
    public void clearDatabase() throws Exception
    {
    
        Connection connection = DriverManager.getConnection
          ("jdbc:mysql://localhost:3306/walking", "god", "fser");

        Statement stmt = connection.createStatement();
        stmt.execute(
        "DELETE FROM men;"
        );

        stmt.execute("ALTER TABLE men AUTO_INCREMENT = 1;");
        stmt.close();
        connection.close();
    }
    @Test
    public void testCreateSub() throws Exception
    {
        App app = new App();
        assertEquals(1, (int)app.createSub("Josh", 23));
    }
    @Test
    public void testSelectSubs() throws Exception
    {
        App app = new App();
        app.createSub("Josh", 23);
        List<Sub> result = new LinkedList<Sub>();
        Sub sub = new Sub();
        sub.name = "Josh";
        sub.age = 23;
        result.add(sub);
        assertEquals(result.size(), 1);
    }
    @Test
    public void testUpdateSub() throws Exception
    {
        Client client = new Client();
        client.getAffected("INSERT INTO men (name, age) "
        + "VALUES ('TEST', 666)," +
        "('lol', 2)," +
        "('kek', 2000);");
        App app = new App();
        UpdateArgs[] args = new UpdateArgs[1];
        UpdateArgs arg = new UpdateArgs();
        arg.field = "name";
        arg.value = "anotherTEST";
        args[0] = arg;
        assertEquals(1, (int)app.updateSub(1, args));
    }
    @Test
    public void testDeleteSub() throws Exception
    {
        Client client = new Client();
        client.getAffected("INSERT INTO men (name, age) VALUES ('TEST', 666)");
        App app = new App();
        assertEquals(1, (int)app.deleteSub(1));
    }
}
