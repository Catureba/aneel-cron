package com.example.demo.repository.client

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.stereotype.Component
import java.net.URL
import java.nio.charset.Charset
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule


@Component
class RelatorioClient {

    fun baixarRelatorioStreaming(processador: (Empreendimento) -> Unit) {
        val url = URL("https://dadosabertos.aneel.gov.br/dataset/57e4b8b5-a5db-40e6-9901-27ca629d0477/resource/4a615df8-4c25-48fa-bbea-873a36a79518/download/ralie-usina.csv")

        val mapper = CsvMapper().apply {
            registerModule(kotlinModule())
            registerModule(JavaTimeModule())
        }

        val schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';')

        url.openStream().bufferedReader(Charset.forName("Windows-1252")).use { reader ->
            val iterator = mapper.readerFor(Empreendimento::class.java)
                .with(schema)
                .readValues<Empreendimento>(reader)

            while (iterator.hasNext()) {
                val emp = iterator.next()
                processador(emp)
            }
        }
    }
}

