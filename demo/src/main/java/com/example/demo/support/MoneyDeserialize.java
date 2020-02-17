package com.example.demo.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@Slf4j
@JsonComponent
public class MoneyDeserialize extends StdDeserializer<Money> {

    protected  MoneyDeserialize(){
        super(Money.class);
    }

    @Override
    public Money deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        log.info("====================p====deserialize========={}",p);
        return Money.of(CurrencyUnit.of("CNY"),p.getDecimalValue());
    }
}
