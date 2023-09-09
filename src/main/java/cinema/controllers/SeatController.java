package cinema.controllers;

import cinema.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class SeatController {

    private final cinema.services.SeatService seatService;

    @Autowired
    public SeatController(cinema.services.SeatService seatService){
        this.seatService = seatService;
    }

    @GetMapping("/seats")
    public SeatsInfoDto getSeats(){
        return this.seatService.getInfoSeats(9, 9);
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getInfo(@RequestParam(value = "password", required = false) String secret) {
        if (Objects.equals(secret, "super_secret")) {
            var stats = this.seatService.stats;
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } else {
            var errBody = new ErrorMessage("The password is wrong!");
            return new ResponseEntity<>(errBody, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> postPurchase(@RequestBody PurchaseSeatDtoRequest seatDto) {
        try {
            var response = this.seatService.getPurchasedSeat(seatDto.getRow(), seatDto.getColumn());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Error err){
            var errBody = new ErrorMessage(err.getMessage());
            return new ResponseEntity<>(errBody, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> postReturn(@RequestBody ReturnSeatDtoRequest seatDto) {
        try {
            var response = this.seatService.getReturnedTicket(seatDto.getToken());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Error err){
            var errBody = new ErrorMessage(err.getMessage());
            return new ResponseEntity<>(errBody, HttpStatus.BAD_REQUEST);
        }
    }
}
