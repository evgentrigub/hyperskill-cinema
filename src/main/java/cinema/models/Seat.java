package cinema.models;

public class Seat {
    int row;

    int column;

    int price;

    boolean booked;

    String bookedToken;

    public Seat(){}

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.booked = false;
        this.bookedToken = "";
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public String getBookedToken() {
        return bookedToken;
    }

    public void setBookedToken(String bookedToken) {
        this.bookedToken = bookedToken;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", column=" + column +
                ", price=" + price +
                ", booked=" + booked +
                '}';
    }
}
