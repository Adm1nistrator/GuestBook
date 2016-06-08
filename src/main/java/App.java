import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by anykey on 08.06.16.
 */
public class App {

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection("jdbc:h2:~/GuestBook", "h2", "password!1")) {
            System.out.println(con);
            Statement stmt = con.createStatement();
            //удаляем табличку, если она уже есть
            //stmt.execute("DROP TABLE IF EXISTS POSTS;");
            //Создаем табличку
            stmt.execute("CREATE TABLE POSTS(id INT IDENTITY, postdate TIMESTAMP, message VARCHAR);");
            stmt.close();
            try (GuestBookController ctrl = new GuestBook(con)) {
                ctrl.addRecord("первый н@х!");
                ctrl.addRecord("вторая запись");
                ctrl.addRecord("еще один пост");

                List<Record> ls = ctrl.getRecords();
                for (Record r : ls) {
                    System.out.println( r.getId() + " : \"" + r.getMsg() + "\""+" в " + r.getTimestamp());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
