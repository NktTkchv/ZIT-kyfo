package zit.kyfo.backend.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "flight_entity")
@Table(name = "flight")
@NoArgsConstructor
public class FlightEntity extends AbstractEntity<Integer> implements Serializable {

    private AirlinesEntity airlines;
    private String airplane;

}
