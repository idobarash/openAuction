package dto;

import entity.User;

public class FinishedAuctionDataDto {

    private User owner;

    private User winner;

    public FinishedAuctionDataDto() {}

    public FinishedAuctionDataDto(User owner, User winner) {
        this.owner = owner;
        this.winner = winner;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }
}
