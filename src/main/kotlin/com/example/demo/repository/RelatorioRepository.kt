package com.example.demo.repository

import com.example.demo.repository.client.RelatorioClient
import com.example.demo.repository.dao.EmpreendimentosDao
import com.example.demo.repository.client.Empreendimento
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime

@Component
class RelatorioRepository(
    private val client: RelatorioClient,
    private val dao: EmpreendimentosDao
) {

    fun atualizarRelatorio() {
        val lote = mutableListOf<Empreendimento>()
        val tamanhoLote = 100
        val agora = LocalDateTime.now()

        client.baixarRelatorioStreaming { emp ->
            lote.add(emp.copy(createdAt = agora))

            if (lote.size >= tamanhoLote) {
                dao.saveAll(lote)
                lote.clear()
            }
        }

        if (lote.isNotEmpty()) {
            dao.saveAll(lote)
        }
    }


    fun buscarTop5GeradoresHoje(): List<Empreendimento> {
        val hoje = LocalDate.now()
        return dao.findTop5ByCreatedAtBetweenOrderByMdaPotenciaOutorgadaKwDesc(
            hoje.atStartOfDay(),
            hoje.plusDays(1).atStartOfDay()
        )
    }

    fun limparBanco() {
        dao.deleteAll()
    }
}