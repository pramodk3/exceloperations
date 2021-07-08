package org.arjun.exceloperations.services.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

@Getter
@JsonDeserialize(as = Data.class)
public class Data<T> {

    private T payload;

    public Data(@JsonProperty("payload") final T payload) {
        this.payload = payload;
    }

}

