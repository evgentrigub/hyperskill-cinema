package cinema.models;

public class ReturnSeatDtoResponse {
    SeatDto returned_ticket;

    public ReturnSeatDtoResponse() {}

    public ReturnSeatDtoResponse(SeatDto returned_ticket) {
        this.returned_ticket = returned_ticket;
    }

    public SeatDto getReturned_ticket() {
        return returned_ticket;
    }

    public void setReturned_ticket(SeatDto returned_ticket) {
        this.returned_ticket = returned_ticket;
    }
}
