package com.exemplo.creditos.repository;

import com.exemplo.creditos.model.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {

    @Query("SELECT c FROM Credito c WHERE c.numeroCredito = :numero OR c.numeroNfse = :numero")
    List<Credito> findByNumeroCreditoOrNumeroNfse(@Param("numero") String numero);
}
