package CreatePost;

import java.util.HashMap;
import java.util.Map;
import android.service.quicksettings.Tile;
public class Postclass {
    public String Title;
    public String Ingredients;
    public String Instruction;
    public Postclass(){

    }
    public Postclass(String a,String b,String c){
        this.Title=a;
        this.Ingredients=b;
        this.Instruction=c;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Title", Title);
        result.put("Ingredients", Ingredients);
        result.put("Instruction", Instruction);
        return result;
    }
}
