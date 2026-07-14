package zit.kyfo.backend.dao.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity(name = "flight_entity")
@Table(name = "flight")
@NoArgsConstructor
public class FlightEntity extends AbstractEntity<Integer> implements Serializable {

    private AirlinesEntity airlines;
    private String airplane;
    private AirportsEntity airportFrom;
    private AirportsEntity airportTo;
    private ZonedDateTime timeOut;
    private ZonedDateTime timeIn;
    private Integer delayMinutes;
    private String reasonDelay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    public AirlinesEntity getAirlines() {
        return this.airlines;
    }

    @Column(name = "airplane", length = 100)
    public String getAirplane() {
        return airplane;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_from", nullable = false)
    public AirportsEntity getAirportFrom() {
        return airportFrom;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_to", nullable = false)
    public AirportsEntity getAirportTo() {
        return airportTo;
    }

    @Column(name = "time_out", nullable = false)
    public ZonedDateTime getTimeOut() {
        return timeOut;
    }

    @Column(name = "time_in", nullable = false)
    public ZonedDateTime getTimeIn() {
        return timeIn;
    }

    @Column(name = "delay_minutes", nullable = false)
    public Integer getDelayMinutes() {
        return delayMinutes;
    }

    @Column(name = "reason_delay")
    public String getReasonDelay() {
        return reasonDelay;
    }

    public void setAirlines(AirlinesEntity airlines) {
        this.airlines = airlines;
    }

    public void setAirplane(String airplane) {
        this.airplane = airplane;
    }

    public void setAirportFrom(AirportsEntity airportFrom) {
        this.airportFrom = airportFrom;
    }

    public void setAirportTo(AirportsEntity airportTo) {
        this.airportTo = airportTo;
    }

    public void setTimeOut(ZonedDateTime timeOut) {
        this.timeOut = timeOut;
    }

    public void setTimeIn(ZonedDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public void setDelayMinutes(Integer delayMinutes) {
        this.delayMinutes = delayMinutes;
    }

    public void setReasonDelay(String reasonDelay) {
        this.reasonDelay = reasonDelay;
    }
}

