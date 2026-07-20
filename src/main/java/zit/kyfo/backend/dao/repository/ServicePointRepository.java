package zit.kyfo.backend.dao.repository;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zit.kyfo.backend.dao.entity.ServicePointEntity;
import zit.kyfo.backend.dto.FinanceReporting.SalesByPointDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ServicePointRepository extends JpaRepository<ServicePointEntity, Integer> {

    List<ServicePointEntity> findByActiveTrue();

    List<ServicePointEntity> findByAirportId(Integer airportId);

    List<ServicePointEntity> findByAirportIdAndActiveTrue(Integer airportId);

    //Возвращает айди точки, название точки, сумму, которую потратили в этой точке и количество транзакций за период
    @Query("""
            SELECT NEW zit.kyfo.backend.dto.FinanceReporting.SalesByPointDto(
                 sp.id,
                 sp.name,
                 COALESCE(SUM(t.amount), 0),
                 CAST(COUNT(t.id) AS int)
            )
            FROM service_points_entity sp
            LEFT JOIN transaction_entity t ON t.servicePoint = sp
                AND t.type = 'purchase'
                AND t.createdAt BETWEEN :start AND :end
            GROUP BY sp.id, sp.name 
            ORDER BY sum(t.amount) DESC NULLS LAST   
            """)
    List<SalesByPointDto> moneyPeriod(@Param("start")LocalDateTime start, @Param("end") LocalDateTime end);
}
