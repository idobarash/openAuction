package entity;

import javax.persistence.*;

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
    private Double bidSum;


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getBidSum() {
        return bidSum;
    }

    public void setBidSum(Double bidSum) {
        this.bidSum = bidSum;
    }

    public User getBiddingUser() {

        return biddingUser;
    }

    public void setBiddingUser(User biddingUser) {
        this.biddingUser = biddingUser;
    }
}
