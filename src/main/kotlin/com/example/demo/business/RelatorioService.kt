package com.example.demo.business

import com.example.demo.repository.RelatorioRepository
import com.example.demo.repository.client.Empreendimento
import org.springframework.stereotype.Service
import org.slf4j.LoggerFactory


@Service
class RelatorioService(
    private val repository: RelatorioRepository,
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun atualizarRelatorio() {
        try {
            log.info("[RELATORIO-SERVICE] -> Iniciando atualização relatório")
            repository.atualizarRelatorio()

        } catch (e: Exception) {
            log.error("[RELATORIO-SERVICE] -> Erro ao atualizar relatório {} {}",e.message, e)
        }
    }

    fun limparBanco() {
        try {
            log.info("[RELATORIO-SERVICE] -> Iniciando limpeza de registros")
            repository.limparBanco()
        } catch (e: Exception) {
            log.error("[RELATORIO-SERVICE] -> Erro ao realizar limpeza de registros {} {}",e.message, e)
        }
    }

    fun buscarTop5GeradoresHoje(): List<Empreendimento> {
        try {
            log.info("[RELATORIO-SERVICE] -> Listagem de empreendimentos")
            val top5Geradores: List<Empreendimento> = repository.buscarTop5GeradoresHoje()
            return top5Geradores;
        } catch (e: Exception) {
            log.error("[RELATORIO-SERVICE] -> Erro ao realizar listagem de empreendimentos {} {}",e.message, e)
            return  listOf()
        }
    }
}