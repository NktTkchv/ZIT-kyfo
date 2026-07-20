package zit.kyfo.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zit.kyfo.backend.dao.repository.ServicePointRepository;
import zit.kyfo.backend.dto.FinanceReporting.SalesByPointDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final ServicePointRepository servicePointRepository;

    public List<SalesByPointDto> getSalesByPoints(LocalDateTime start, LocalDateTime end) {
        return servicePointRepository.moneyPeriod(start, end);
    }
}
