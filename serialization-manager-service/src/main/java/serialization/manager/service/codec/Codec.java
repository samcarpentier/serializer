package serialization.manager.service.codec;

import java.lang.reflect.Field;

import com.google.gson.*;

import serialization.manager.service.*;
import serialization.manager.service.exception.*;
import util.commons.*;

public class Codec {

  private ObjectFormatProcessor objectFormatProcessor;

  public Codec(ObjectFormatProcessor objectFormatProcessor) {
    this.objectFormatProcessor = objectFormatProcessor;
  }

  public JsonObject toJson(SerializableObject objectToSerialize) throws SerializationException {
    Gson gson = new Gson();
    JsonObject json = null;

    try {
      json = gson.toJsonTree(objectToSerialize).getAsJsonObject();
    } catch (Exception e) {
      throw new SerializationException(String.format("The provided object cannot be serialized (%s).", e.getMessage()));
    }

    Field idField = objectFormatProcessor.getIdField(objectToSerialize.getClass());
    json.addProperty(PersistenceConfig.ID_FIELD_IDENTIFIER, idField.getName());

    return json;
  }

  public SerializableObject fromJson(JsonObject objectToDeserialize, Class<? extends SerializableObject> clazz)
      throws DeserializationException {
    Gson gson = new Gson();

    try {
      objectToDeserialize.remove(PersistenceConfig.ID_FIELD_IDENTIFIER);
      return gson.fromJson(objectToDeserialize, clazz);
    } catch (Exception e) {
      throw new DeserializationException(
          String.format("The provided object cannot be deserialized (%s).", e.getMessage()));
    }
  }

}
