package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "items")
public class Item extends AbstractEntity {

    @Column(name ="creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    private String name;

    private String description;

    @Column(name = "start_price")
    private Double startPrice;

    @Column(name = "is_sold")
    private Boolean isSold;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    private ItemCategory category;

    public Boolean getIsSold() {
        return isSold;
    }

    public void setIsSold(Boolean isSold) {
        this.isSold = isSold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getSold() {
        return isSold;
    }

    public void setSold(Boolean sold) {
        isSold = sold;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }
}
