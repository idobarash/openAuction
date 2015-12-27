package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Auction extends AbstractEntity {


    @Column(name = "publish_date")
    private Date publishDate;

    @Column(name = "final_date")
    private Date finalDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User seller;

    @OneToOne
    @JoinColumn(name = "auction_id", referencedColumnName = "id")
    private Item item;

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
