package cinema.models;

public class ReturnSeatDtoRequest {
    String token;

    public ReturnSeatDtoRequest() {}

    public ReturnSeatDtoRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
