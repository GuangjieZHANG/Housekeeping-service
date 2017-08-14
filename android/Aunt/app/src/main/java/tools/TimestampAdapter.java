package tools;

/**
 * Created by 张广洁 on 2017/4/3.
 */

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.sql.Timestamp;

/**
 * Gson TypeAdapter
 * 实现了 Timestamp 类的 json 化
 * @author linwei
 *
 */
public class TimestampAdapter implements JsonSerializer<Timestamp>, JsonDeserializer<Timestamp> {

    @Override
    public Timestamp deserialize(JsonElement json, Type typeOfT,
                                 JsonDeserializationContext context) throws JsonParseException {
        if(json == null){
            return null;
        } else {
            try {
                return new Timestamp(json.getAsLong());
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Override
    public JsonElement serialize(Timestamp src, Type typeOfSrc,
                                 JsonSerializationContext context) {
        String value = "";
        if(src != null){
            value = String.valueOf(src.getTime());
        }
        return new JsonPrimitive(value);
    }

}
