package entity;

import javax.persistence.*;

/**
 * Bidding entity
 *
 * Author: Ido Barash
 */
@Entity
@Table(name = "biddings")
public class Bidding extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User biddingUser;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    @Column(name = "sum")
    private Integer bidSum;


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getBidSum() {
        return bidSum;
    }

    public void setBidSum(Integer bidSum) {
        this.bidSum = bidSum;
    }

    public User getBiddingUser() {

        return biddingUser;
    }

    public void setBiddingUser(User biddingUser) {
        this.biddingUser = biddingUser;
    }
}
