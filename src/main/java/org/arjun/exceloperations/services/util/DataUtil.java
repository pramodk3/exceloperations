package org.arjun.exceloperations.services.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataUtil {

    protected static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JodaModule());
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static <T> T convertValue(final Object data, final Class<T> eventDataClass) {
        return getObjectMapper().convertValue(data, eventDataClass);
    }

    public static <T> T readValue(final Object data, final Class<T> eventDataClass) throws JsonProcessingException {
        return getObjectMapper().readValue(data.toString(), eventDataClass);
    }

}
