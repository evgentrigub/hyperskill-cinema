package cinema.services;

import cinema.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class SeatService {

    private final List<Seat> seats;

    public StatsInfo stats;

    SeatService() {
        this.seats = getSeats(9, 9);
        this.stats = getStatsInfo();
    }

    public SeatsInfoDto getInfoSeats(int totalRows, int totalColumns){
        SeatsInfoDto infoDto = new SeatsInfoDto();
        List<SeatDto> seats = this.transformSeats(this.seats);
        infoDto.setAvailable_seats(seats);
        infoDto.setTotal_columns(totalColumns);
        infoDto.setTotal_rows(totalRows);
        return infoDto;
    }

    public PurchaseSeatDtoResponse getPurchasedSeat(int row, int column) {
            var token = UUID.randomUUID().toString();
            var seat = this.getSeatFromCinema(row, column);
            seat.setBooked(true);
            seat.setBookedToken(token);
            // TODO add stats
            this.stats.setNumber_of_available_seats(this.stats.getNumber_of_available_seats() - 1);
            this.stats.setNumber_of_purchased_tickets(this.stats.getNumber_of_purchased_tickets() + 1);
            this.stats.setCurrent_income(this.stats.getCurrent_income() + seat.getPrice());

            var ticket = new SeatDto(seat.getRow(), seat.getColumn(), seat.getPrice());
            return new PurchaseSeatDtoResponse(token, ticket);
    }

    public Seat getSeatFromCinema(int row, int column) {
        var seatOptional = this.seats.stream().filter(el -> el.getRow() == row && el.getColumn() == column).findFirst();
        if (seatOptional.isPresent()) {
            var seat = seatOptional.get();
            if(seat.isBooked()){
                throw new Error("The ticket has been already purchased!");
            }
            return seat;
        } else {
            throw new Error("The number of a row or a column is out of bounds!");
        }
    }

    public Seat getSeatFromCinema(String token) {
        var seatOptional = this.seats.stream().filter(el -> Objects.equals(el.getBookedToken(), token)).findFirst();
        if (seatOptional.isPresent()) {
            return seatOptional.get();
        } else {
            throw new Error("Wrong token!");
        }
    }

    public ReturnSeatDtoResponse getReturnedTicket(String token) {
        var seat = getSeatFromCinema(token);
        seat.setBooked(false);
        seat.setBookedToken("");

        this.stats.setNumber_of_available_seats(this.stats.getNumber_of_available_seats() + 1);
        this.stats.setNumber_of_purchased_tickets(this.stats.getNumber_of_purchased_tickets() - 1);
        this.stats.setCurrent_income(this.stats.getCurrent_income() - seat.getPrice());

        var ticket = new SeatDto(seat.getRow(), seat.getColumn(), seat.getPrice());
        return new ReturnSeatDtoResponse(ticket);
    }

    private StatsInfo getStatsInfo() {
        return new StatsInfo(0, 81, 0);
    }

    private List<Seat> getSeats(int totalRows, int totalColumns){
        List<Seat> seats = new ArrayList<>();
        for (int row = 1; row <= totalRows; row++) {
            for (int column = 1; column <= totalColumns; column++) {
                int price = (row <= 4) ? 10 : 8;
                Seat seat = new Seat(row, column, price);
                seats.add(seat);
            }
        }
        return seats;
    }

    private List<SeatDto> transformSeats(List<Seat> seats) {
        var seatsDtoList = new ArrayList<SeatDto>();
        for (Seat seat: seats) {
            var seatDto = new SeatDto(seat.getRow(), seat.getColumn(), seat.getPrice());
            seatsDtoList.add(seatDto);
        }
        return seatsDtoList;
    }

}
