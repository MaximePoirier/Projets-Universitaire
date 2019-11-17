package services.exceptions;

public class BadTokenException extends Exception {
    private String authUri;

    public BadTokenException(String authUri){
        this.authUri = authUri;
    }

    public String getAuthUri() {
        return authUri;
    }
}
