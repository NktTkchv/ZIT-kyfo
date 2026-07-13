package zit.kyfo.backend.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Column(name = "login", nullable = false, unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(@NonNull String login) {
        this.login = login;
    }

    @Column(name = "password_hash", nullable = false)
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(@NonNull String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @OneToMany
    public List<FlightEntity> getFlight() {
        return flight;
    }

    public void setFlight(@NonNull List<FlightEntity> flight) {
        this.flight = flight;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirlinesEntity that = (AirlinesEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(login, that.login) && Objects.equals(passwordHash, that.passwordHash) && Objects.equals(flight, that.flight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, passwordHash, flight);
    }


}
