package manhinhtrangchu;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class User {
    public String Name;
    public String Date;
    public String Phone;
    public  User(){

    }
    public  User(String a, String b, String c){
        Name=a;
        Date=b;
        Phone=c;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Name",Name);
        result.put("Date", Date);
        result.put("Phone", Phone);
        return result;
    }
}
