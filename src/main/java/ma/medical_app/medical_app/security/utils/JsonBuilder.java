package ma.medical_app.medical_app.security.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonBuilder {
    private ObjectNode jsonObject;

    public JsonBuilder() {
        this.jsonObject = new ObjectMapper().createObjectNode();
    }

    public JsonBuilder put(String key, Object value) {
        jsonObject.put(key, String.valueOf(value));
        return this;
    }

    public String build() throws JsonProcessingException {
        return jsonObject.toString();
    }
}
