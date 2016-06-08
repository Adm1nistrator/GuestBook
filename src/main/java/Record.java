import java.sql.Timestamp;

/**
 * Created by anykey on 08.06.16.
 */
public class Record {
    private long id;
    private String msg;
    private Timestamp time;

    public Record(int id, String msg, Timestamp date) {
        this.id = id;
        this.msg = msg;
        this.time = date;
    }

    public long getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public Timestamp getTimestamp() {
        return time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTimestamp(Timestamp t) {
        this.time = t;
    }
}
