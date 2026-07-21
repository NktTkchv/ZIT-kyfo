package zit.kyfo.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zit.kyfo.backend.dao.entity.AirlinesEntity;
import zit.kyfo.backend.dao.entity.FlightEntity;
import zit.kyfo.backend.dao.repository.FlightRepository;
import zit.kyfo.backend.dto.flights.FlightDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightsService {

    private final AirlinesService airlinesService;

    private final FlightRepository flightRepository;

    public List<FlightDto> findAll(boolean delayedOnly) {
        List<FlightEntity> flightEntities = delayedOnly
                ? flightRepository.findByDelayMinutesGreaterThanEqualOrderByDelayMinutesDesc(
                        FlightDelayPolicy.COMPENSATION_THRESHOLD_MINUTES)
                : flightRepository.findAll();

        if (!delayedOnly) {
            flightEntities.sort(delayedFirstComparator());
        }

        return flightEntities.stream()
                .map(this::mapToFlightDto)
                .collect(Collectors.toList());
    }

    public List<FlightDto> findAll() {
        return findAll(false);
    }

    public FlightDto findById(int id) {
        FlightEntity entity = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Рейс с id " + id + " не найдена"));
        return mapToFlightDto(entity);
    }

    public List<FlightDto> getFlightsByAirlineId(int airlineId) {
        AirlinesEntity airline = airlinesService.findEntityById(airlineId);
        List<FlightEntity> flights = flightRepository.findByAirline(airline);
        return flights.stream()
                .map(this::mapToFlightDto)
                .collect(Collectors.toList());
    }

    public FlightEntity findEntityById(int id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Авиакомпания с id " + id + " не найдена"));
    }

    private FlightDto mapToFlightDto(FlightEntity entity) {
        FlightDto dto = new FlightDto();
        dto.setId(entity.getId());
        dto.setAirlineName(entity.getAirline().getName());
        dto.setAirplane(entity.getAirplane());
        dto.setAirportFrom(entity.getAirportFrom().getUniqueCode());
        dto.setAirportTo(entity.getAirportTo().getUniqueCode());
        dto.setTimeOut(entity.getTimeOut());
        dto.setTimeIn(entity.getTimeIn());
        dto.setDelayMinutes(entity.getDelayMinutes());
        dto.setReasonDelay(entity.getReasonDelay());
        dto.setDelayed(FlightDelayPolicy.isDelayed(entity.getDelayMinutes()));
        return dto;
    }

    private Comparator<FlightEntity> delayedFirstComparator() {
        return Comparator
                .comparing((FlightEntity f) -> FlightDelayPolicy.isDelayed(f.getDelayMinutes())).reversed()
                .thenComparing(FlightEntity::getDelayMinutes, Comparator.reverseOrder());
    }
}
