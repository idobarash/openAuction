package entity;

import javax.persistence.*;

@Entity
@Table(name = "item_categories")
public class ItemCategory extends AbstractEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemCategory category = (ItemCategory) o;

        return ((ItemCategory) o).getId() == this.getId();

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
