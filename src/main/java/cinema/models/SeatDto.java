package cinema.models;

public class SeatDto extends PurchaseSeatDtoRequest {

    int price;

    public SeatDto(int row, int column, int price) {
        super(row, column);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
