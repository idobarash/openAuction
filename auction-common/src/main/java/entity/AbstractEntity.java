package entity;

import javax.persistence.*;

/**
 * Abstract entity data
 * Contains shared properties for all the entities tables
 *
 * Author: Ido Barash
 */
@MappedSuperclass
public class AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
