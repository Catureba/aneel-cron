package com.example.demo.controller

import com.example.demo.business.RelatorioService
import com.example.demo.repository.client.Empreendimento
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/relatorio")
@CrossOrigin(origins = ["http://localhost:3000", "https://aneel-app.vercel.app/"], maxAge = 3600)
class RelatorioRest(
    private val service: RelatorioService
) {

    @PostMapping("/atualizar")
    fun atualizarRelatorio(): ResponseEntity<String> {
        service.atualizarRelatorio()
        return ResponseEntity.ok("Relatório atualizado com sucesso!")
    }

    @DeleteMapping("/limpar")
    fun limparBanco(): ResponseEntity<String> {
        service.limparBanco()
        return ResponseEntity.ok("Banco de dados limpo com sucesso!")
    }

    @GetMapping("/top5")
    fun buscarTop5Geradores(): ResponseEntity<List<Empreendimento>> {
        return ResponseEntity.ok(service.buscarTop5GeradoresHoje())
    }
}
