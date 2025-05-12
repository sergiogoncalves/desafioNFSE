package com.exemplo.creditos.controller;

import com.exemplo.creditos.dto.CreditoDTO;
import com.exemplo.creditos.service.CreditoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
@CrossOrigin(origins = "*")
public class CreditoController {

    private final CreditoService creditoService;

    public CreditoController(CreditoService creditoService) {
        this.creditoService = creditoService;
    }

    @GetMapping
    public ResponseEntity<List<CreditoDTO>> buscarCreditos(@RequestParam String numero) {
        List<CreditoDTO> creditos = creditoService.buscarCreditosPorNumero(numero);
        if (creditos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(creditos);
    }
}
