package zit.kyfo.backend.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "airport_entity")
@Table(name = "airport")
@NoArgsConstructor
public class AirportsEntity extends AbstractEntity<Integer> implements Serializable {

    private String name;
    private String uniqueCode;
    private String town;
    private String address;

    public AirportsEntity(String name, String uniqueCode, String town, String address) {
        this.name = name;
        this.uniqueCode = uniqueCode;
        this.town = town;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public String getTown() {
        return town;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
