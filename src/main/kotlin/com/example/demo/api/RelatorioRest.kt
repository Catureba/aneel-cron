package com.example.demo.controller

import com.example.demo.business.RelatorioService
import com.example.demo.repository.client.Empreendimento
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/relatorio")
@CrossOrigin(origins = ["http://localhost:5173", "https://aneel-app.vercel.app/"], maxAge = 3600)
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

    @GetMapping("/maiores-geradores")
    fun buscarMaioresGeradores(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "5") size: Int
    ): ResponseEntity<List<Empreendimento>> {
        return ResponseEntity.ok(service.buscarMaioresGeradores(page, size))
    }

}
