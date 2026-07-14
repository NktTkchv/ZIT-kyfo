package zit.kyfo.backend.dao.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "airports_entity")
@Table(name = "airports")
@NoArgsConstructor
public class AirportsEntity extends AbstractEntity<Integer> implements Serializable {

    private String name;
    private String uniqueCode;
    private String town;
    private String address;
    private List<FlightEntity> fromAirports;
    private List<FlightEntity> toAirports;

    public AirportsEntity(String name, String uniqueCode, String town, String address) {
        setName(name);
        setUniqueCode(uniqueCode);
        setTown(town);
        setAddress(address);
        this.fromAirports = new ArrayList<>();
        this.toAirports = new ArrayList<>();
    }

    public AirportsEntity(String name, String uniqueCode, String town, String address, List<FlightEntity> fromAirports, List<FlightEntity> toAirports) {
        setName(name);
        setUniqueCode(uniqueCode);
        setTown(town);
        setAddress(address);
        setFromAirports(fromAirports);
        setToAirports(toAirports);
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    @Column(name = "unique_code", nullable = false, unique = true)
    public String getUniqueCode() {
        return this.uniqueCode;
    }

    @Column(name = "town")
    public String getTown() {
        return this.town;
    }

    @Column(name = "address")
    public String getAddress() {
        return this.address;
    }

    @OneToMany(mappedBy = "airportFrom", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    public List<FlightEntity> getFromAirports() {
        return fromAirports;
    }

    @OneToMany(mappedBy = "airportTo", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    public List<FlightEntity> getToAirports() {
        return toAirports;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setUniqueCode(@NonNull String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public void setTown(@NonNull String town) {
        this.town = town;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    public void setFromAirports(@NonNull List<FlightEntity> fromAirports) {
        this.fromAirports = fromAirports;
    }

    public void setToAirports(@NonNull List<FlightEntity> toAirports) {
        this.toAirports = toAirports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirportsEntity that = (AirportsEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(uniqueCode, that.uniqueCode) && Objects.equals(town, that.town) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, uniqueCode, town, address);
    }
}
