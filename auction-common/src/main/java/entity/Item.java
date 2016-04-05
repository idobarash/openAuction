package entity;

import enums.ItemCondition;

import javax.persistence.*;
import java.util.Date;

/**
 * Item entity
 *
 * Author: Ido Barash
 */
@Entity
@Table(name = "items")
public class Item extends AbstractEntity {

    @Column(name ="creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name ="end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    private String name;

    private String description;

    @Column(name = "start_price")
    private Integer startPrice;

    @Column(name = "current_bid")
    private Integer currentBid = 0;

    @Column(name = "bids_counter")
    private Integer bidsCounter = 0;

    @Column(name = "is_sold")
    private Boolean isSold;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    private ItemCategory category;

    @Column(name = "item_condition")
    @Enumerated(EnumType.STRING)
    private ItemCondition condition;

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

    public Integer getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Integer startPrice) {
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

    public ItemCondition getCondition() {
        return condition;
    }

    public void setCondition(ItemCondition condition) {
        this.condition = condition;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(Integer currentBid) {
        this.currentBid = currentBid;
    }

    public Integer getBidsCounter() {
        return bidsCounter;
    }

    public void setBidsCounter(Integer bidsCounter) {
        this.bidsCounter = bidsCounter;
    }
}
