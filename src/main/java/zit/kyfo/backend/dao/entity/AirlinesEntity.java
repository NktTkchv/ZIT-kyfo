package zit.kyfo.backend.dao.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "airlines_entity")
@Table(name = "airlines")
@NoArgsConstructor
public class AirlinesEntity extends AbstractEntity<Integer> implements Serializable {

    String name;
    String login;
    String passwordHash;
    List<FlightEntity> flight;

    public AirlinesEntity(String name, String login, String passwordHash) {
        setName(name);
        setLogin(login);
        setPasswordHash(passwordHash);
        this.flight = new ArrayList<>();
    }

    public AirlinesEntity(String name, String login, String passwordHash, List<FlightEntity> flight) {
        setName(name);
        setLogin(login);
        setPasswordHash(passwordHash);
        setFlight(flight);
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Column(name = "login", nullable = false, unique = true)
    public String getLogin() {
        return this.login;
    }

    public void setLogin(@NonNull String login) {
        this.login = login;
    }

    @Column(name = "password_hash", nullable = false)
    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(@NonNull String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @OneToMany(mappedBy = "airlines", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    public List<FlightEntity> getFlight() {
        return this.flight;
    }

    public void setFlight(@NonNull List<FlightEntity> flight) {
        this.flight = flight;
    }

    public boolean addFlight(FlightEntity flight) {
        if (flight == null) {
            throw new IllegalArgumentException("Flight cannot be null");
        }
        return this.flight.add(flight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirlinesEntity that = (AirlinesEntity) o;
        return Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

}
