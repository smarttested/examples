package com.smarttested.didemo.guava.collections;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Gson Serializer/Deserializer for {@link com.smarttested.didemo.guava.collections.AirportType}
 *
 * @author avarabyeu
 */
public class EnumTypeDeserializer implements JsonDeserializer<AirportType>, JsonSerializer<AirportType> {

    @Override
    public AirportType deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return AirportType.fromName(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(AirportType airportType, Type type, JsonSerializationContext jsonSerializationContext) {
        return null == airportType ? null : jsonSerializationContext.serialize(airportType.getName());
    }
}
