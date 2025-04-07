package com.example.demo.business


import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class RelatorioServiceCron(
    private val service: RelatorioService
) {
    private val log =
        LoggerFactory.getLogger(this::class.java) //em java tenho costume de usar a sl4j pra criar logs e ferar observabilidade mas em kotlin acredito que existam opções melhores

    // Eu faria rodar uma vez por mês para ter os dados sendo atualizados mensalmente ou até com um intervalo maior dependendo da necessidade
    // já fiz projetos que rodavam apenas no 5 dia util de cada mês ou apenas em finais de semana
    @Scheduled(cron = "0 0 3 * * *")
    fun executarAtualizacaoDiaria() {
        log.info("[RELATORIO-SERVICE-CRON] -> Iniciando Cron job de atualização do relatório")
        service.atualizarRelatorio()
    }
}
