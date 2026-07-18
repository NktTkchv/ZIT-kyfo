package zit.kyfo.backend.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import java.io.Serializable;

@MappedSuperclass
@Getter
public abstract class AbstractEntity<ID extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected ID id;

    public void setId(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        this.id = id;
    }
}
