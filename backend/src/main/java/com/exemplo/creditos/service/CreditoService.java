package com.exemplo.creditos.service;

import com.exemplo.creditos.dto.CreditoDTO;
import com.exemplo.creditos.event.ConsultaCreditoEvent;
import com.exemplo.creditos.model.Credito;
import com.exemplo.creditos.repository.CreditoRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditoService {

    private final CreditoRepository creditoRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CreditoService(CreditoRepository creditoRepository, ApplicationEventPublisher eventPublisher) {
        this.creditoRepository = creditoRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<CreditoDTO> buscarCreditosPorNumero(String numero) {
        List<Credito> creditos = creditoRepository.findByNumeroCreditoOrNumeroNfse(numero);

        // Dispara o evento para o listener (ex: Kafka Publisher)
        eventPublisher.publishEvent(new ConsultaCreditoEvent(this, numero));

        return creditos.stream()
                .map(CreditoDTO::new)
                .toList();
    }
}
