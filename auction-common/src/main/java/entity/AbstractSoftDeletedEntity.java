package entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Soft delete entity is an entity which will never be
 * deleted from the DB. It will become inavtice.
 *
 * Author: Ido Barash
 */
@MappedSuperclass
public class AbstractSoftDeletedEntity extends AbstractEntity {

    @Column(name = "active")
    protected Boolean isActive;

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        isActive = isActive;
    }
}
