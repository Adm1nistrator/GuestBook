import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anykey on 07.06.16.
 */


public class GuestBook implements GuestBookController {
    PreparedStatement insert;
    PreparedStatement selectAll;

    public GuestBook(Connection c) throws SQLException {
        insert = c.prepareStatement("insert into posts (postdate, message) values(?, ?)");
        selectAll = c.prepareStatement("select * from posts");
    }

    public void addRecord(String message) throws SQLException {
        Timestamp t = new Timestamp(System.currentTimeMillis());
      //  Date date = new Date(System.currentTimeMillis());
        insert.setTimestamp(1, t);
       // insert.setDate(1, date);
        insert.setString(2, message);
        insert.execute();
    }

    public List<Record> getRecords() throws SQLException {
        List<Record> list = new ArrayList<>();
        try(ResultSet rs = selectAll.executeQuery()){
            while(rs.next()){
                Record r = new Record(
                        rs.getInt("id"),
                        rs.getString("message"),
                        rs.getTimestamp("postdate")
                );
                list.add(r);
            }
        }
        return list;
    }

    public void close() throws IOException {
        try {
            insert.close();
            selectAll.close();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }


}
