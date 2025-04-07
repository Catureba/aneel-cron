package com.example.demo.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.math.BigDecimal

class BigDecimalBRDeserializer : JsonDeserializer<BigDecimal>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): BigDecimal {
        val text = p.text.replace(",", ".")
        return text.toBigDecimalOrNull() ?: BigDecimal.ZERO
    }
}
