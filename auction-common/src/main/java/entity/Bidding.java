package entity;

import javax.persistence.*;

@Entity
@Table(name = "biddings")
public class Bidding extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User biddingUser;

    @ManyToOne
    @JoinColumn(name = "auction_id", referencedColumnName = "id")
    private Auction auction;

    @Column(name = "sum")
    private Double bidSum;


    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
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
