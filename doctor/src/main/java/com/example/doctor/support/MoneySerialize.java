package com.example.doctor.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@Slf4j
@JsonComponent
public class MoneySerialize extends StdSerializer<Money> {
        protected MoneySerialize(){
            super(Money.class);
        }

    @Override
    public void serialize(Money money, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        log.info("====================money====serialize========={}",money);
        jsonGenerator.writeNumber(money.getAmount());
    }
}
