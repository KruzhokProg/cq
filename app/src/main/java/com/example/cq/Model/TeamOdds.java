package com.example.cq.Model;

public class TeamOdds {
    private String odd;
    private String bookmaker;

    public TeamOdds(String odd, String bookmaker) {
        this.odd = odd;
        this.bookmaker = bookmaker;
    }

    public String getOdd() {
        return odd;
    }

    public void setOdd(String odd) {
        this.odd = odd;
    }

    public String getBookmaker() {
        return bookmaker;
    }

    public void setBookmaker(String bookmaker) {
        this.bookmaker = bookmaker;
    }
}
