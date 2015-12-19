package model;

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
}
