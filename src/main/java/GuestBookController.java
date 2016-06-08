import java.sql.SQLException;
import java.util.List;

/**
 * Created by anykey on 06.06.16.
 */
public interface GuestBookController extends AutoCloseable{

    void addRecord(String message) throws SQLException;

    List<Record> getRecords() throws SQLException; //Record {id, postDate, message}
}
