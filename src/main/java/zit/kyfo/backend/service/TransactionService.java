package zit.kyfo.backend.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zit.kyfo.backend.dao.entity.ServicePointEntity;
import zit.kyfo.backend.dao.entity.TicketEntity;
import zit.kyfo.backend.dao.entity.TransactionEntity;
import zit.kyfo.backend.dao.repository.ServicePointRepository;
import zit.kyfo.backend.dao.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ServicePointRepository servicePointRepository;

    @Transactional
    public TransactionEntity createTopUpTransaction(TicketEntity ticket, BigDecimal amount, int servicePointId) {
        ServicePointEntity servicePoint = servicePointRepository.findById(servicePointId)
                .orElseThrow(() -> new RuntimeException("Точка обслуживания не найдена"));

        TransactionEntity transaction = new TransactionEntity();
        transaction.setTicket(ticket);
        transaction.setAmount(amount);
        transaction.setServicePoint(servicePoint);
        transaction.setType(TransactionEntity.Type.topUp);
        transaction.setCreatedAt(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    @Transactional
    public TransactionEntity createPurchaseTransaction(TicketEntity ticket, BigDecimal amount, int servicePointId) {
        ServicePointEntity servicePoint = servicePointRepository.findById(servicePointId)
                .orElseThrow(() -> new RuntimeException("Точка обслуживания не найдена"));

        TransactionEntity transaction = new TransactionEntity();
        transaction.setTicket(ticket);
        transaction.setAmount(amount);
        transaction.setServicePoint(servicePoint);
        transaction.setType(TransactionEntity.Type.purchase);
        transaction.setCreatedAt(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }
}
