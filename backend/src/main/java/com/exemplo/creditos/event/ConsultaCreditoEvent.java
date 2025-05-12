package com.exemplo.creditos.event;

import org.springframework.context.ApplicationEvent;

public class ConsultaCreditoEvent extends ApplicationEvent {

    private final String numero;

    public ConsultaCreditoEvent(Object source, String numero) {
        super(source);
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }
}
