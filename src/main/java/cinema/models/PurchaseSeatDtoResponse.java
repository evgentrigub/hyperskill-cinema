package cinema.models;

public class PurchaseSeatDtoResponse {
    String token;

    SeatDto ticket;

    public PurchaseSeatDtoResponse() {}

    public PurchaseSeatDtoResponse(String token, SeatDto ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SeatDto getTicket() {
        return ticket;
    }

    public void setTicket(SeatDto ticket) {
        this.ticket = ticket;
    }
}
