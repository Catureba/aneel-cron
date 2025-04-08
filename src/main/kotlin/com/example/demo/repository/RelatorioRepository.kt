package com.example.demo.repository

import com.example.demo.repository.client.RelatorioClient
import com.example.demo.repository.dao.EmpreendimentosDao
import com.example.demo.repository.client.Empreendimento
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import org.slf4j.LoggerFactory

@Component
class RelatorioRepository(
    private val client: RelatorioClient,
    private val dao: EmpreendimentosDao
) {
    private val log = LoggerFactory.getLogger(this::class.java)
    fun atualizarRelatorio() {
        val lote = mutableListOf<Empreendimento>()
        val tamanhoLote = 100
        val agora = LocalDateTime.now()

        client.baixarRelatorioStreaming { emp ->
            lote.add(emp.copy(createdAt = agora))

            if (lote.size >= tamanhoLote) {
                try {
                    log.info("[RELATORIO-REPOSITORY] -> Salvando lote")
                    dao.saveAll(lote)
                    lote.clear()
                } catch (e: Exception) {
                    log.error("[RELATORIO-REPOSITORY] -> Erro ao atualizar relatório {}", e.message)
                }
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